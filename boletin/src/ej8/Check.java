package ej8;

public class Check {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClaseBuffer buffer = new ClaseBuffer();
		HiloIncrementa h1 = new HiloIncrementa(buffer);
		HiloLector h2 = new HiloLector(buffer);
		
		
		buffer.muestra();
		System.out.println("--------------------------------------");
		h1.start();
		h2.start();
		try {
			h1.join();
			h2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		buffer.muestra();
		
		
	}

}
