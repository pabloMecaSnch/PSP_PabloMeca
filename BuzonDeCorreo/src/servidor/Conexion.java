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
	private final int _TAM_BUFFER = 80;
	private boolean salida = false;

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
			output.write(new String("Bienvenido " + this.usuario + "\n").getBytes());
			printOpciones();
			this.buffer = new byte[1];
			while (!salida) {
				output.flush();
				input.read(this.buffer);
				gestionMensaje(new String(this.buffer));
				this.buffer = new byte[1];
				printOpciones();
			}
			// output.write(new String("Bienvenido "+this.usuario).getBytes());
			// input.read(this.buffer);

			// socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void comprobarCorreo() {
		Mensaje m = Buzon.buscaMensaje(usuario);
		if (m != null) {
			leerMensaje(m);
		} else {
			try {
				output.write(new String("No hay correos por leer").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void leerMensaje(Mensaje m) {
		String contenido = m.getMensaje();
		if (contenido.length() > _TAM_BUFFER) {
			try {
				output.write(new String(contenido.substring(1, _TAM_BUFFER)).trim().getBytes());
				output.write(new String(contenido.substring(_TAM_BUFFER, _TAM_BUFFER * 2)).trim().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void gestionMensaje(String mensaje) {
		try {
			int opcion = Integer.parseInt(mensaje);
			switch (opcion) {
			case 1:
				// Comprobar correo
				comprobarCorreo();
				break;
			case 2:
				// Escribir correo
				escribirCorreo(usuario);
				break;
			case 3:
				// Salir
				try {
					output.write(new String("Adi�s.").getBytes());
					this.salida = true;
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			default:
				output.write(new String("Opci�n no contemplada").getBytes());
			}
		} catch (NumberFormatException e) {
			try {
				output.write(new String("No entend� el mensaje").getBytes());
			} catch (IOException f) {

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void escribirCorreo(String usuario) {
		String destinataio;
		String mensaje;
		int tamBuffer;
		try {
			this.buffer = new byte[_TAM_BUFFER];
			output.write(new String("Destinatario:").getBytes());
			input.read(this.buffer);
			destinataio = new String(this.buffer);
			output.write(new String("Mensaje:").getBytes());

			while ((tamBuffer = input.available()) == 0) {
				//espera a que haya un mensaje que leer
			}
			buffer = new byte[tamBuffer];
			// System.out.println(is.read(buffer));
			input.read(this.buffer);

			this.buffer = new byte[input.available()];
			input.read(this.buffer);
			mensaje = new String(this.buffer);
			Buzon.anadirMensaje(new Mensaje(this.usuario, destinataio, mensaje));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void printOpciones() {
		try {
			output.write(new String("\n1) Comprobar correo\n").getBytes());
			output.write(new String("2) Escribir correo\n").getBytes());
			output.write(new String("3) Salir\n").getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}