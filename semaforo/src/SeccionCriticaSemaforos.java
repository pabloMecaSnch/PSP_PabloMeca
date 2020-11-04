import java.util.concurrent.Semaphore;

public class SeccionCriticaSemaforos {
	private static Sumador sumadores[];
	private static Semaphore semaphore= new Semaphore(1);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nSum = Integer.parseInt(args[0]);
		sumadores = new Sumador[nSum];
		for(int i=0; i<nSum; i++) {
			sumadores[i] = new Sumador(1000, i, semaphore);
			sumadores[i].start();
		}
		for(int i=0; i<nSum; i++) {
			try {
				sumadores[i].join();
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Acumulador "+ Acumula.acumulador);
	}

}
