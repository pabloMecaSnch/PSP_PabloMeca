package servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import buzon.Buzon;
import buzon.Mensaje;

public class Conexion extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private byte[] buffer;
	private String usuario;
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
//			Thread.sleep(100);
			output.write(new String("Log in:").getBytes());
			input.read(this.buffer);
			this.usuario = new String(this.buffer);
			output.write(new String("Bienvenido " + this.usuario + "\n").getBytes());
			// El tamaño del buffer es 1 porque el usuario eligirá entre 3 opciones
			// introduciendo un número, entonces no es necesario un buffer de mayor tamaño
			this.buffer = new byte[1];
			while (!salida) {

				printOpciones();
				output.flush();
				input.read(this.buffer);
				gestionOpcion(new String(this.buffer));
				this.buffer = new byte[1];

			}
		} catch (IOException e) {
			e.printStackTrace();
		} /*
			 * catch (InterruptedException e) { e.printStackTrace(); }
			 */

	}

	private void comprobarCorreo() {
		ArrayList<Mensaje> m = Buzon.buscaMensaje(usuario);
		if (!m.isEmpty()) {
			gestionMensajes(m);
		} else {
			try {
				output.write(new String("No hay correos por leer").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void gestionMensajes(ArrayList<Mensaje> m) {

		for (int i = 0; i < m.size(); i++) {
			Mensaje mens = m.get(i);
			try {
				output.write(new String("\nQuedan: " + m.size() + " mensajes por leer" + i).getBytes());
				printOpcionesMensaje();
				this.buffer = new byte[1];
				input.read(this.buffer);

				int opcion = Integer.parseInt(new String(this.buffer));
				switch (opcion) {
				case 1:
					leerMensaje(mens);
					Buzon.borrarMensaje(mens);
					m.remove(mens);
					break;

				default:
					output.write(new String("Opción no válida").getBytes());
					i--;
					break;
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				try {
					output.write(new String("illooooo fallo" + i).getBytes());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void gestOpcionMensaje(String mensaje) {

	}

	private void printOpcionesMensaje() {
		try {
			output.write(new String("\n1)leer mensaje").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void leerMensaje(Mensaje m) {
		String contenido = "";
		contenido = "Mensaje de : " + m.getDe() + "\n" + m.getMensaje();
		try {
			output.write(contenido.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void gestionOpcion(String mensaje) {
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
					output.write(new String("Adiós.").getBytes());
					this.salida = true;
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				output.write(new String("Opción no contemplada").getBytes());
			}
		} catch (NumberFormatException e) {
			try {
				output.write(new String("No entendí el mensaje").getBytes());
			} catch (IOException f) {

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void escribirCorreo(String usuario) {
		String destinataio = "";
		String mensaje = "";
		int tamBuffer = 0;
		try {
			// this.buffer = new byte[_TAM_BUFFER];
			output.write(new String("Destinatario:").getBytes());
			while ((tamBuffer = input.available()) == 0) {
				// espera a que haya un mensaje que leer
			}
			this.buffer = new byte[tamBuffer];
			input.read(this.buffer);
			destinataio = new String(this.buffer).trim();
			output.write(new String("Mensaje:").getBytes());

			while ((tamBuffer = input.available()) == 0) {
				// espera a que haya un mensaje que leer
			}
			buffer = new byte[tamBuffer];
			input.read(this.buffer);
			mensaje = new String(this.buffer).trim();
			Buzon.anadirMensaje(new Mensaje(this.usuario, destinataio, mensaje));
			output.write(new String("Mensaje enviado").getBytes());
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
