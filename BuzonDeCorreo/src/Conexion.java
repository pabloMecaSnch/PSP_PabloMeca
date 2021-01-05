import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Conexion extends Thread {

	private Socket socket;
	private InputStream input;
	private OutputStream output;
	private byte[] buffer;
	private String usuario;
	public Conexion(Socket soket) {
		this.socket = soket;
		try {
			this.input = socket.getInputStream();
			this.output = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.buffer = new byte[15];
	}

	public void run() {
		try {
			Thread.sleep(100);
			output.write(new String("Log in:").getBytes());
			input.read(this.buffer);
			this.usuario= new String(this.buffer);
			output.write(new String("Adiós.").getBytes());
			//output.write(new String("Bienvenido "+this.usuario).getBytes());
			//input.read(this.buffer);
			
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * private String gestionMensaje(String mensaje) {
		mensaje = mensaje.toLowerCase();
		switch (mensaje) {
		case ":
			
			break type;

		default:
			throw new IllegalArgumentException("Unexpected value: " + mensaje);
		}
		return "";
		
	}
	*/
}
