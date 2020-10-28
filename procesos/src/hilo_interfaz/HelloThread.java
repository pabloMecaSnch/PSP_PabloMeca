package hilo_interfaz;

public class HelloThread implements Runnable{
	Thread t;
	HelloThread(){
		t = new Thread(this,"nuevo thread");
		System.out.println("creado hilo "+t);
			t.start();
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Hola desde el nuevo hilo creado");
		System.out.println("Hilo finalizado");
	}

}
