package ej12;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int nCoches = 10;
		Parking parking = new Parking(5);
		Coche[] hilos = new Coche[nCoches];
		
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Coche(i,parking);
			hilos[i].start();
		}
		
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
