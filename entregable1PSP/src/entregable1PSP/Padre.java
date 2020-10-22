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
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		OutputStream os = pr.getOutputStream();
		String line;
		while (pr.isAlive()) {
			byte[] buffer = new byte[4000];
			int in = System.in.available();
			try {
				Thread.sleep(5000); //se introduce un retardo de 10 milisegundos
			}catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			if(in > 0) {
				int n = System.in.read(buffer, 0, Math.min(in, buffer.length));
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
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}
		System.out.println(pr.exitValue());
	}

}
