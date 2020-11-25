package entregable2;

public class Ordenador {

	public static boolean[] Tarjetas = {true,true,true,true,true};
	
	public synchronized void usaOrdenador(Persona p) {
		if(p.tarjetaIzqd && p.tarjetaDrch) {
			System.out.println("La persona "+p.getIdPersona()+" está usando el ordenador");
			try {
				Persona.sleep((long)Math.random()*5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.sueltaTarjetas();
		}
	}
}
