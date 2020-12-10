package entregable2;

public class Coworking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona[] personas = new Persona[5];
		Tarjeta[] tarjetas = new Tarjeta[5];
		
		for (int i = 0; i < tarjetas.length; i++) {
			tarjetas[i] = new Tarjeta(i);
		}
		Mesa o = new Mesa(tarjetas);
		
		for (int i = 0; i < personas.length; i++) {
			personas[i] = new Persona(i , o);
			personas[i].start();
		}
		for (int i = 0; i < personas.length; i++) {
			try {
				personas[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
