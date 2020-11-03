package sentencias_sincronizadas;

public class MutualExclusion {

	public static void main(String[] args) throws InterruptedException{
		// TODO Auto-generated method stub
		int n = Integer.parseInt(args[0]);
		TwoMutex hilos[];
		System.out.println("Creando "+ n + " hilos");
		
		hilos = new TwoMutex[n];
		for (int i = 0; i < n; i++) {
			hilos[i] = new TwoMutex();
			hilos[i].start();
		}
		for (int i = 0; i < n; i++) {
			hilos[i].join();
		}
		System.out.println("C1 = "+ GlobalVar.c1);
		System.out.println("C2 = "+ GlobalVar.c2);
		
	}

}
