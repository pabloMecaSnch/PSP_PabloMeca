package ejemplo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStream {

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
			String mensaje = "Mensaje desde el cliente";
			os.write(mensaje.getBytes());
			System.out.println("Mensaje enviado");
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
