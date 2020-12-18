package ejemplo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();
			
			System.out.println("Realizando blind");
			InetSocketAddress addr = new InetSocketAddress("localhost",5555);
			server.bind(addr);
			
			System.out.println("Aceptando conexiones");
			Socket newSocket = server.accept();
			
			System.out.println("Conexi�n recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			byte[] message = new byte[25];
			is.read(message);
			System.out.println("Mensaje recibido: "+ new String(message));
			
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
