package hilosEh1;

public class HelloThread extends Thread{

	@Override
	public void run() {
		System.out.println("Hola desde el hilo creado");
	}
}
