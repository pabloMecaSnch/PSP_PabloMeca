package ej6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class CuentaVocal extends Thread {

	private char vocal;
	private FileWriter fw;
	private ContadorGlobal var;
	private BufferedReader br;
	private FileReader fr;

	public CuentaVocal(char vocal) {
		this.vocal = vocal;
	}

	@Override
	public void run() {
		String linea;
		/*try {
			fw = new FileWriter("./src/archivo.txt");
			fw.write("aeiou");
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
*/
		try {

			File f = new File("./src/archivo.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
//		leerArchivo();
		try {
			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					if (linea.charAt(i) == this.vocal) {
						sumaVocal();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*
 * al intentar invocar este metodo dentro del metod run() del hilo me da fallo
	private void leeArchivo()  {
		String linea;
		try {
			while ((linea = br.readLine()) != null) {
				for (int i = 0; i <= linea.length(); i++) {
					if (linea.charAt(i) == this.vocal) {
						sumaVocal();
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
*/
	private synchronized void sumaVocal() {
		var.totalVocales++;
	}

}
