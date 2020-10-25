package entregable1PSP;

import java.io.IOException;
import java.io.InputStream;

import java.io.OutputStream;

public class Padre {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//En estas dos primeras líneas ejecuto la clase hija, la primera línea se utiliza para compilar la clase, y con la segunda la ejecuto
		Process proceso = Runtime.getRuntime().exec("javac -cp src src/entregable1PSP/Hija.java");
		Process pr = Runtime.getRuntime().exec("java -cp src entregable1PSP/Hija");
		//Aqui programo el output de la clase Hija para que envíe los datos escritos en la clase Hija a la clase Padre
		InputStream is = pr.getInputStream();
		//Aqui programo el input de la clase  para que los datos que se escriban en la consola de la clase Padre vayan al input de la clase Hija
		OutputStream os = pr.getOutputStream();
		//Compruebo que la clase Hija esta en ejecucion
		while (pr.isAlive()) {
			//Creacion de un buffer para comunicar las dos clases
			byte[] buffer = new byte[4000];
			//int in = System.in.available();
			//Compruebo si hay datos de la clase Hija por imprimir
			//bucle para imprimir los mensajes de la clase hija
			int no = is.available();
			if(no>0) {
				int n = is.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0,n));
			}
			//Compruebo si hay informacion por enviar a la clase Hija
			int ni = System.in.available();
			if (ni > 0) {
				//si existe información se envía al proceso hijo
				int n = System.in.read(buffer, 0, Math.min(ni, buffer.length));
				os.write(buffer, 0, n);
				os.flush();
			}
			try {
				Thread.sleep(10); //se introduce un retardo de 10 milisegundos
			}
			catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(pr.exitValue());
	}
}

