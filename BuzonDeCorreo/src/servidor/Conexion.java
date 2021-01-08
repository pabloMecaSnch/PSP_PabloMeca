package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import buzon.Buzon;
import buzon.Mensaje;

public class Conexion extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private byte[] buffer;
	private String usuario;
	private final int _TAM_BUFFER=80;

	public Conexion(Socket soket) {
		this.socket = soket;
		try {
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.buffer = new byte[15];
	}

	public void run() {
		try {
			Thread.sleep(100);
			output.write(new String("Log in:").getBytes());
			input.read(this.buffer);
			this.usuario = new String(this.buffer);
			output.write(new String("Bienvenido " + this.usuario).getBytes());
			printOpciones();
			// output.write(new String("Bienvenido "+this.usuario).getBytes());
			// input.read(this.buffer);

			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void comprobarCorreo() {
		boolean enc = false;
		for (Mensaje m : Buzon.mensajes) {
			if (m.getPara().equals(this.usuario) && enc==false) {
				leerMensaje(m);
				enc = true;
			}
		}
		if(!enc) {
		try {
			output.write(new String("Correos no encontrados").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		}
		
	}

	private void leerMensaje(Mensaje m) {
		String contenido = m.getMensaje();
		if(contenido.length()>_TAM_BUFFER) {
			try {
				output.write(new String(contenido.substring(1, _TAM_BUFFER)).trim().getBytes());
				output.write(new String(contenido.substring(80, _TAM_BUFFER*2)).trim().getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		String linea = contenido.substring(1, 80);
		
	}
	private void gestionMensaje(String mensaje) {
		try {
			int opcion = Integer.parseInt(mensaje);
			switch (opcion) {
			case 1:
				// Comprobar correo
				break;
			case 2:
				// escribir correo
				break;
			case 3:
				// Salir

			default:
				output.write(new String("Opción no contemplada").getBytes());
			}
		} catch (NumberFormatException e) {
			try {
				output.write(new String("No entendí el mensaje").getBytes());
			} catch (IOException f) {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printOpciones() {
		try {
			output.write(new String("1) Comprobar correo\n").getBytes());
			output.write(new String("2) Escribir correo\n").getBytes());
			output.write(new String("3) Salir\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
