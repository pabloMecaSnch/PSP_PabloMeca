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
	private ContadorGlobal var;
	private BufferedReader br;
	private FileReader fr;
	private Object sincronizador = new Object();
	public CuentaVocal(char vocal) {
		this.vocal = vocal;
		try {

			File f = new File("./src/ej6/archivo.txt");
			fr = new FileReader(f);
			br = new BufferedReader(fr);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {		
		leeArchivo();
	}
	private void leeArchivo()  {
		String linea;
		try {
			while ((linea = br.readLine()) != null) {
				for (int i = 0; i < linea.length(); i++) {
					linea=linea.toLowerCase();
					if (linea.charAt(i) == this.vocal) {
						synchronized (sincronizador) {
							var.totalVocales++;
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*private synchronized void sumaVocal() {
		var.totalVocales++;
	}*/

}
