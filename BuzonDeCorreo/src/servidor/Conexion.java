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
	private ArrayList<Mensaje> mensajes;

	/**
	 * Método constructor de la clase. En este recojo por parámetro el socket del
	 * cliente para realizar la conexión e inicializo el array de mensajes del
	 * cliente al igual que el buffer, que inicializo con un tamaño de 15, ya que lo
	 * primero que pido es el nombre de usuario
	 * 
	 * @param soket El socket del cliente que realice la petición al servidor
	 */
	public Conexion(Socket soket) {
		this.socket = soket;
		this.mensajes = new ArrayList<Mensaje>();
		try {
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.buffer = new byte[15];
	}

	/**
	 * La ejecución del hilo, en la que primero pide el usuario y después entra un
	 * bucle en el que gestiona las peticiones del cliente
	 */
	public void run() {
		try {
			output.write(new String("Log in:").getBytes());
			input.read(this.buffer);
			this.usuario = new String(this.buffer);
			output.write(new String("Bienvenido " + this.usuario + "\n").getBytes());
			// El tamaño del buffer es 1 porque el usuario eligirá entre 3 opciones
			// introduciendo un número, entonces no es necesario un buffer de mayor tamaño
			this.buffer = new byte[1];
			while (!salida) {

				printOpciones();
				input.read(this.buffer);
				gestionOpcion(new String(this.buffer));
				this.buffer = new byte[1];

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que realiza la petición al buzón y recoge los mensajes del clientes,
	 * que se guardan en la variable: mensajes. Si el array de mensajes no está
	 * vacío, inicia el método utilizado para leer estos mensajes.
	 * 
	 */
	private void comprobarCorreo() {
		this.mensajes = Buzon.buscaMensaje(usuario);
		if (!mensajes.isEmpty()) {
			gestionMensajes(mensajes);
		} else {
			try {
				output.write(new String("No hay correos por leer").getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Método que se encarga de recorrer la lista de mensajes disponibles.
	 * 
	 * @param m La lista de mensajes que tiene el cliente disponible para leer
	 */
	private void gestionMensajes(ArrayList<Mensaje> m) {

		while (!m.isEmpty()) {
			Mensaje mens = m.get(0);
			try {
				output.write(new String("\nQuedan: " + m.size() + " mensajes por leer").getBytes());
				printOpcionesMensaje();
				this.buffer = new byte[1];
				input.read(this.buffer);
				gestOpcionMensaje(new String(this.buffer), mens);
			} catch (NumberFormatException e) {
				try {
					output.write(new String("opción no válida").getBytes());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			output.write(new String("\nYa no hay más mensajes").getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Método utilizado para gestionar las posibles opciones que el cliente tiene
	 * para usar frente a un mensaje.
	 * 
	 * @param mensaje El texto que el cliente a introducido definiendo su opción
	 * @param mens    El mensaje que recibirá la acción seleccionada por el cliente
	 */
	private void gestOpcionMensaje(String mensaje, Mensaje mens) {
		try {
			int opcion = Integer.parseInt(new String(this.buffer));

			switch (opcion) {
			case 1:
				leerMensaje(mens);
				Buzon.borrarMensaje(mens);
				this.mensajes.remove(mens);
				break;

			default:
				output.write(new String("\nOpción no válida").getBytes());
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método que le muestra al cliente las opciones posibles frente a un mensaje
	 */
	private void printOpcionesMensaje() {
		try {
			output.write(new String("\n1)leer mensaje").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método encargado de mostrar al cliente el contenido de un mensaje
	 * 
	 * @param m Mensaje que se leerá
	 */
	private void leerMensaje(Mensaje m) {
		String contenido = "";
		contenido = "Mensaje de : " + m.getDe() + "\n" + m.getMensaje();
		try {
			output.write(contenido.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método utilizado para gestionar las diferentes opciones que el cliente tiene
	 * al loggear
	 * <ul>
	 * <li>1: Comprobar correo</li>
	 * <li>2: Escribir correo</li>
	 * <li>3: Salir</li>
	 * <li>default: mandará un mensaje al cliente indicando que no entiende su
	 * respuesta</li>
	 * </ul>
	 * 
	 * @param mensaje El mensaje que el cliente a introducido para feinir la opción
	 *                deseada
	 * 
	 * 
	 */
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
				escribirCorreo();
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

	/**
	 * Método que sirve para gestionar la escitura de un mensaje.
	 */
	private void escribirCorreo() {
		String destinataio = "";
		String mensaje = "";
		int tamBuffer = 0;
		try {
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
/**
 * Método que muestra la cliente las opciones que tiene una vez a loggeado
 */
	private void printOpciones() {
		try {
			output.write(new String("\n1) Comprobar correo\n").getBytes());
			output.write(new String("2) Escribir correo\n").getBytes());
			output.write(new String("3) Salir\n").getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
