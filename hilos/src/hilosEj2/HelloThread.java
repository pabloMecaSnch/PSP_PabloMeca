package hilosEj2;

public class HelloThread implements Runnable{
	Thread t;
	HelloThread () {
	 t=new Thread(this,"Nuevo thread");	
	 System.out.println("Creado hilo: "+t);
	 t.start();
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hola desde el hilo creado");
		System.out.println("hilo finalizado");
	}

}
