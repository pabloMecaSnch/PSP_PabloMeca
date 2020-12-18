package boletín.ej1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ProgramaA {

	static String ruta ="C:\\Users\\Usuario\\git\\PSP_PabloMeca\\Sockets\\src\\boletín\\ej1\\ArchivoEmisor";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			File file = new File(ruta);
			
			FileReader fr;
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			Socket clienteSocket = new Socket();
			InetSocketAddress addr = new InetSocketAddress("localhost", 5555);

			clienteSocket.connect(addr);
			
			InputStream is = clienteSocket.getInputStream();
			OutputStream os = clienteSocket.getOutputStream();
			System.out.println("Enviando mensaje");
			
			
			while(br.ready()) {
				String linea = br.readLine();
				System.out.println(linea);
				os.write(linea.getBytes());
			}
			System.out.println("Mensaje enviado");
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
