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
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			OutputStream os = clienteSocket.getOutputStream();
			// BufferedReader in = new BufferedReader(new
			// InputStreamReader(clienteSocket.getInputStream()));
			is.read(buffer);
			System.out.println(new String(buffer));
			buffer = new byte[80];
			
			while (!fin) {
				// entrada = new Scanner(System.in);
				mensaje = entrada.nextLine();
				os.write(mensaje.getBytes());
				int tamBuffer = 0;
				while ((tamBuffer =is.available())>0) {
					buffer = new byte[tamBuffer];
					//System.out.println(is.read(buffer));
					is.read(buffer);
					String respuesta = new String(buffer);
					System.out.println(new String(buffer));
					if (respuesta.trim().equals("Adiós.")) {
						fin = true;
					}
					
				}
			}
			System.out.println("Conexion finalizada");
			// "loggear" == introducir nombre
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
