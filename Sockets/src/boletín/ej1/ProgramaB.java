package bolet�n.ej1;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ProgramaB {
	static String ruta ="C:\\Users\\Usuario\\git\\PSP_PabloMeca\\Sockets\\src\\bolet�n\\ej1\\ArchivoRrecibido";
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();

			System.out.println("Realizando blind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);

			System.out.println("Aceptando conexiones");
			Socket newSocket = server.accept();

			System.out.println("Conexi�n recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			
			FileWriter fw = new FileWriter(ruta);
			
			byte[] message = new byte[40];
			while (is.available()>0) {
				is.read(message);
				System.out.println("Mensaje recibido: " + new String(message));
				fw.write(new String(message));
			}

			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
