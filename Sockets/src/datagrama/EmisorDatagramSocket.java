package datagrama;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class EmisorDatagramSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			System.out.println("Creando socket datagram");
			DatagramSocket socket = new DatagramSocket();
			String message = "Mensaje dedsde el emisor";
			
			InetAddress addr = InetAddress.getByName("localhost");
			DatagramPacket datagram = new DatagramPacket(message.getBytes(),message.getBytes().length,addr,5555);
			socket.send(datagram);
			System.out.println("Mensaje enviado");
			
			System.out.println("Cerrando socket datagram");
			socket.close();
			
			System.out.println("Terminado");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
