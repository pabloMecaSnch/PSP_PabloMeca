package cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) {
		boolean fin = false;
		try {
			System.out.println("Creando socket cliente");
			Socket clienteSocket = new Socket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			Scanner entrada = new Scanner(System.in);
			String mensaje;

			clienteSocket.connect(addr);
			byte[] buffer = new byte[30];
			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			is.read(buffer);
			System.out.println(new String(buffer));
			buffer = new byte[80];
			
			while (!fin) {
//				el primer ciclo del bucle se usa para realizar el log in
				// "loggear" == introducir nombre				
				mensaje = entrada.nextLine();
				os.write(mensaje.getBytes());
				int tamBuffer = 0;
				while ((tamBuffer =is.available())>0) {
					buffer = new byte[tamBuffer];
					is.read(buffer);
					String respuesta = new String(buffer);
					System.out.println(new String(buffer));
					if (respuesta.trim().equals("Adiós.")) {
						fin = true;
					}
					
				}
			}
			System.out.println("Conexion finalizada");
			clienteSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
