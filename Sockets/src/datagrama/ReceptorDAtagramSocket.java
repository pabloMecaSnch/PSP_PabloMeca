package datagrama;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ReceptorDAtagramSocket {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Creando socket datagram");
			InetSocketAddress addr = new InetSocketAddress("localhost",5555);
			DatagramSocket socket = new DatagramSocket(addr);
			
			byte[] message = new byte[25];
			DatagramPacket datagram1 = new DatagramPacket(message, 25);
			socket.receive(datagram1);
			System.out.println("Mensaje recibido: "+ new String(message));
			
			System.out.println("Enviando mensaje");
			InetAddress addr2 = InetAddress.getByName("localhost");
			DatagramPacket datagram2 = new DatagramPacket(message, message.length,addr2,5555);
			socket.send(datagram2);
			
			System.out.println("Mensaje enviado");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
