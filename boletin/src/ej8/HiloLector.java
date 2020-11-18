package ej8;

public class HiloLector extends Thread{
	ClaseBuffer b;
	public HiloLector(ClaseBuffer buffer) {
		this.b=buffer;
	}
	public synchronized void lee() {
			b.lee();
			int n =ClaseBuffer.buffer[0];//guarda el primer dígito del buffer
			int aux;
			boolean error=false;
			for (int i = 1; i < ClaseBuffer.buffer.length; i++) {
				aux=ClaseBuffer.buffer[i];
				if(n!=aux) {
					error=true;
				}
			}
			if(error) {
				System.out.println("ERROR");
			}else {
				System.out.println("correcto");
			}
			b.leido();
		}
	
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lee();
	}
}
