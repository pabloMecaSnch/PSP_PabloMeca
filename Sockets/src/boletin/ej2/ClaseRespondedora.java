package boletin.ej2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ClaseRespondedora {

	static String ruta = ".\\src\\boletín\\ej2\\ClaseRespondedora";

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
//				if (is.read(message) > -1) {
					int z = is.read(message);
					String linea = new String(message);
					System.out.println(linea.length()+"."+z);
					for (int i = 0; i < linea.length(); i++) {
//						String v = " ";
//						os.write(v.getBytes());
						if (linea.charAt(i) == '?') {

							System.out.println("Pregunta finalizada");
							System.out.println(pregunta);
							i = linea.length();
							String vuelta;
							String preguntaBuena = pregunta.trim();
							os.flush();
							switch (preguntaBuena) {
							case "¿Cómo te llamas":
								vuelta = "Me llamo Ejercicio 2".trim();
								os.write(vuelta.getBytes());
								break;
							case "¿Cuántas líneas de código tienes":
								vuelta = "pfff muchas no se";
								os.write(vuelta.getBytes());
								break;
							default:
								System.out.println(preguntaBuena+".");
								vuelta = "No entendi";
								os.write(vuelta.getBytes());
								
							}
							pregunta = "";
						} else {
							if (pregunta.trim().equalsIgnoreCase("salir")) {
								salida = true;
							} else {
								pregunta = pregunta.concat(linea.charAt(i) + "");
								System.out.println(pregunta);
							}
						}
					}
					os.write(0);
//				}else {
//					salida = true;
//				}

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
