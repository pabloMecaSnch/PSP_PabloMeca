package entregable1PSP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Hija {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int n = 1;
		int n2 = 0;
		String entrada;
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("hola");
		while (n > n2) {
			if (br.ready()) {
				entrada = br.readLine();
				if (entrada.equals("a")) {
					System.out.println("has escrito una a");
				}
			}
		}
	}
}
