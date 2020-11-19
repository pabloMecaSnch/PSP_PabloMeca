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

	public synchronized int estaLLeno() {
		int nPlaza = -1;
		boolean enc = false;
		for (int i = 0; i < plazas.length && enc == false; i++) {
			if (plazas[i].getCoche() == null) {
				//lleno = false;
				nPlaza = i;
				enc = true;
			} else {
//				lleno = true;
			}
		}
		return nPlaza;
	}

	public synchronized void aparca(Coche c) {
		int nPlaza = estaLLeno();
		if (nPlaza != -1) {
			plazas[nPlaza] = new Plaza(c,nPlaza);
			c.setPlaza(plazas[nPlaza]);
			System.out.println("El coche "+ c.getIdCoche()+ "ha entrado en la plaza "+nPlaza);
			plazasTotales--;
			System.out.println(plazasTotales);
			muestraParking();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void sale(Coche c) {
//		lleno = false;
		System.out.println("El coche "+c.getIdCoche()+" sale del parking de la plaza "+c.getPlaza().getPos());
		int pos = c.getPlaza().getPos();
		plazas[pos]= new Plaza(pos);
		c.getPlaza().setCoche(null);;
		c.setPlaza(null);
		plazasTotales++;
		System.out.println("Plazas totales: "+plazasTotales);
		muestraParking();
		notify();
	}
	
	public void muestraParking() {
		for (int i = 0; i < plazas.length; i++) {
			int x=1;
			if(plazas[i].getCoche() == null)
				x=0;
			System.out.print("["+x+"]  ");
		}
	}

}
