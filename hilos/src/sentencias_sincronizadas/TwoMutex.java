package sentencias_sincronizadas;

public class TwoMutex extends Thread{

	private Object mutex1 = new Object();
	private Object mutex2 = new Object();
	
	public void inc1() {
		synchronized (mutex1) {
			GlobalVar.c1++;
		}
	}
	public void inc2() {
		synchronized (mutex2) {
			GlobalVar.c2++;
		}
	}
	@Override
	public void run() {
		inc1();
		inc2();
	}
	
}
