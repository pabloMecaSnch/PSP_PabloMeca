package ej8;


public class HiloIncrementa extends Thread{

	
	ClaseBuffer b;
	
	public HiloIncrementa(ClaseBuffer buffer) {
		this.b=buffer;
	}
	public synchronized void incrementa() {
		
			for (int i = 0; i < ClaseBuffer.buffer.length; i++) {
				ClaseBuffer.buffer[i]++;
		}
		
	}

	
	@Override
	public void run() {
			b.incrementa();
			incrementa();
	}
}
