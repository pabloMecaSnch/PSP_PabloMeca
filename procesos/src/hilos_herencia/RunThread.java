package hilos_herencia;

public class RunThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new HelloThread().start();
		try {
			Thread.sleep(10);
		}catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Hola desde el hilo principal");
		System.out.println("Proceso acabando");
	}

}
