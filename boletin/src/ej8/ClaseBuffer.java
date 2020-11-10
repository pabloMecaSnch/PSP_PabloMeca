package ej8;

import java.nio.Buffer;

public class ClaseBuffer {
	
	static int buffer[] = new int[10000];
	boolean leyendo=false;
	
	public void incrementa() {
		try {
			while(leyendo==true) {
				wait();
			}
		}catch(InterruptedException ex) {
			ex.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	public synchronized void lee() {
		this.leyendo=true;
	}
	public synchronized void leido() {
		this.leyendo=false;
		notify();
	}
	
	public synchronized void muestra() {
		int n;
		for (int i = 1; i < buffer.length; i++) {
			n=ClaseBuffer.buffer[i];
			System.out.println(n);
		}
	}
	
}
