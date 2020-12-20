package boletin.ej2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClaseRespondedora {

	public static void main(String[] args) {
		boolean salida = false;
		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			System.out.println("Realizando blind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);

			System.out.println("Aceptando conexiones");
			Socket newSocket = server.accept();

			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			byte[] message = new byte[35];
			String pregunta = "";
			while (!salida) {
				if (is.read(message) > -1) {
					String linea = new String(message);
					for (int i = 0 ; i < linea.length(); i++) {
						if (linea.charAt(i) == '?') {
							System.out.println("Pregunta finalizada");
							System.out.println(pregunta);
							salida = true;
							i = linea.length();
							switch (pregunta) {
							case "hola":
								String vuelta = "Buenas tardes";
								os.write(vuelta.getBytes());
								break;

							default:
								throw new IllegalArgumentException("Unexpected value: " + pregunta);
							}
						} else {
							pregunta = pregunta.concat(linea.charAt(i)+"");
							System.out.println(pregunta);
						}
					}
					
				}

			}
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();

			System.out.println("Cerrando el socket servidor");
			server.close();
			System.out.println("Terminado");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
