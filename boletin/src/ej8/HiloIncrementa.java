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
		try {
			Thread.sleep(10);
			b.incrementa();
			incrementa();
		}catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}
}
