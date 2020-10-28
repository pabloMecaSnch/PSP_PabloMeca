package ejHilo2;

public class Hilo1 extends Thread{

	@Override
	public void run() {
		try {
			Thread.sleep(10);
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("hola soy el hilo 1");
	}
}
