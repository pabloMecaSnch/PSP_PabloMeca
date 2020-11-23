package ej12;

public class Parking {

	public static Plaza[] plazas;
	public int plazasTotales;
	public static int siguiente;

	public Parking(int numPlazas) {
		Parking.siguiente = 0;
		this.plazasTotales = numPlazas;
		Parking.plazas = new Plaza[numPlazas];
		for (int i = 0; i < numPlazas; i++) {
			Parking.plazas[i] = new Plaza(i);
		}
	}

	public synchronized int estaLLeno() {
		int nPlaza = -1;
		boolean enc = false;
		for (int i = 0; i < plazas.length && enc == false; i++) {
			if (plazas[i].getVehiculo() == null) {
				nPlaza = i;
				enc = true;
			}
		}
		return nPlaza;
	}

	public synchronized void aparca(Coche c) {
		try {

			while (c.getPlaza() == null) {
				int nPlaza = estaLLeno();
				while (nPlaza == -1) {
					wait();
					nPlaza = estaLLeno();

				}
				if (c.turno == Parking.siguiente) {
					plazas[nPlaza].setCoche(c);
					c.setPlaza(plazas[nPlaza]);
					System.out.println("El coche " + c.getIdCoche() + " ha entrado en la plaza " + nPlaza);
					plazasTotales--;
					System.out.println("Quedan : " + plazasTotales);
					muestraParking();
					Parking.siguiente++;
					notifyAll();
				} else {
					wait();
				}
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized void sale(Coche c) {
		System.out.println("El coche " + c.getIdCoche() + " sale del parking de la plaza " + c.getPlaza().getPos());
		int pos = c.getPlaza().getPos();
		c.getPlaza().setCoche(null);
		plazas[pos].saleCoche();
		plazasTotales++;
		System.out.println("Plazas totales: " + plazasTotales);
		muestraParking();
		notifyAll();
	}

	public void muestraParking() {
		for (int i = 0; i < plazas.length; i++) {
			int x = 1;
			if (plazas[i].getVehiculo() == null)
				x = 0;
			System.out.print("[" + x + "]  ");
		}
	}

}
