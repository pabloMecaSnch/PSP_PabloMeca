package boletín.ej1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ProgramaB {
	//static String ruta = "C:\\Users\\Pablo\\git\\PSP_PabloMeca\\Sockets\\src\\boletín\\ej1\\ArchivoRrecibido";
	static String ruta =".\\src\\boletín\\ej1\\ArchivoRrecibido";
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

			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();

			FileWriter fw = new FileWriter(ruta);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			
			byte[] message = new byte[40];
			
			while (is.read(message) > -1) {
				System.out.println("Mensaje recibido: " + new String(message));
				out.println(new String(message));
			}
			out.close();
			bw.close();
			fw.close();
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
