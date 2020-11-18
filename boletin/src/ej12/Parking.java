package ej12;

public class Parking {

	public static Plaza[] plazas;
	public static boolean lleno = false;
	private int plazasTotales;
	public Parking(int numPlazas) {
		this.plazasTotales = numPlazas;
		this.plazas = new Plaza[numPlazas];
		for (int i = 0; i < numPlazas; i++) {
			this.plazas[i] = new Plaza(i);
		}
	}

	public int estaLLeno() {
		int nPlaza = -1;
		for (int i = 0; i < plazas.length; i++) {
			if (plazas[i] == null) {
				lleno = false;
				nPlaza = i;
			} else {
				lleno = true;
			}
		}
		return nPlaza;
	}

	public void aparca(Coche c) {
		int nPlaza = estaLLeno();
		if (nPlaza != -1) {
			plazas[nPlaza] = new Plaza(c,nPlaza);
			c.setPlaza(plazas[nPlaza]);
			System.out.println("El coche "+ c.getIdCoche()+ "ha entrado en la plaza "+nPlaza);
			plazasTotales--;
			System.out.println(plazasTotales);
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sale(Coche c) {
		lleno = false;
		c.getPlaza().setCoche(null);;
		c.setPlaza(null);
		plazasTotales++;
		notifyAll();
	}

}
