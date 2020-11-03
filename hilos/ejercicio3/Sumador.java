package ejercicio3;

public class Sumador extends Thread{
	private int cuenta;
	private Semaphore sem;
	
	public Sumador(int hasta, int id, Semaphore sem) {
		this.cuenta = hasta;
		this.sem = sem;
	}
	
	public void sumar() {
		Acumula.acumulador++;
	}
	public void run() {
		for(int i=0; i<cuenta; i++) {
			try {
				sem.acquire();
			}catch(InterruptefEsception e) {
				e.printStackTrace();
			}
			sumar();
			sem.release();
		}
	}
}
