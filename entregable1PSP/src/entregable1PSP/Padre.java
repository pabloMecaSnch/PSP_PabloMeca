package entregable1PSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;

public class Padre {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Process proceso = Runtime.getRuntime().exec("javac -cp src src/entregable1PSP/Hija.java");
		Process pr = Runtime.getRuntime().exec("java -cp src entregable1PSP/Hija");
		InputStream is = pr.getInputStream();
		OutputStream os = pr.getOutputStream();
		String line;
		while (pr.isAlive()) {
			//System.out.println("inicio bucle");
			byte[] buffer = new byte[4000];
			int in = System.in.available();
			//Compruebo si hay datos en la consola
			//bucle para imprimir los mensajes de la clase hija
			int no = is.available();
			if(no>0) {
				int n = is.read(buffer, 0, Math.min(no, buffer.length));
				System.out.println(new String(buffer, 0,n));
			}
			
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
