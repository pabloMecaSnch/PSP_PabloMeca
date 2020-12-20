package boletin.ej2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import java.util.Scanner;

public class ClasePeguntona {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			System.out.println("Creando socket cliente");
			Socket clienteSocket = new Socket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

			clienteSocket.connect(addr);

			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			byte[] buffer = new byte[35];

			String mensaje = "";
			System.out.println(mensaje);
			while (true) {
				Scanner tec = new Scanner(System.in);
				mensaje = tec.nextLine();
				os.write(mensaje.getBytes());

				System.out.println("Mensaje enviado");

				try {
					Thread.sleep(1000);

				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if(is.read(buffer)>-1) {
						System.out.println(new String(buffer));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
