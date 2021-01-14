package servidor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import buzon.Buzon;

public class Servidor {

	public static void main(String[] args) {
		boolean exec = true;
		Buzon b = new Buzon();
		try {
			System.out.println("Creando socket servidor");
			ServerSocket server = new ServerSocket();

			System.out.println("Realizando blind");
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
			server.bind(addr);
			while (exec) {
				
				System.out.println("Aceptando conexiones");
				Conexion c = new Conexion(server.accept());
				c.start();
				System.out.println("Conexión recibida");

			}
			server.close();
			System.out.println("Cerrando el socket servidor");

			System.out.println("Terminado");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
