package ej12Camiones;

import ej12.Plaza;
import ej12.Parking;

public class ParkingCamiones extends Parking{

	
	public ParkingCamiones(int numPlazas) {
		super(numPlazas);
		// TODO Auto-generated constructor stub
	}
	public int buscaPlaza() {
		int nPlaza = -1;
		boolean enc = false;
		for (int i = 0; i < Parking.plazas.length && enc == false; i++) {
			if (Parking.plazas[i].getVehiculo() == null && Parking.plazas[i + 1].getVehiculo() == null && (i+1)<Parking.plazas.length) {
				nPlaza = i;
				enc = true;
			}
		}
		return nPlaza;
	}
	public synchronized void aparca(Camion c) {
		try {
			while( c.getPlaza() == null) {
				int nPlaza = buscaPlaza();
				while ( nPlaza == -1) {
					wait();
					nPlaza = estaLLeno();
				}
				if(c.turno == ParkingCamiones.siguiente) {
					plazas[nPlaza].setCamion(c);
					plazas[nPlaza + 1].setCamion(c);
					c.setPlaza(plazas[nPlaza], plazas[nPlaza + 1]);
					System.out.println("El camion "+c.getIdCamion()+" ha entrado en la plaza "+nPlaza);
					plazasTotales-=2;
					muestraParking();
					Parking.siguiente++;
					notifyAll();
				}else {
					wait();
				}
			}
			
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void sale(Camion c) {
		System.out.println("El camion "+c.getIdCamion()+" sale del parking");
		int pos = c.getPlaza().getPos();
//		c.getPlaza().setCamion(null);
		plazas[pos].saleCoche();
		plazas[pos + 1].saleCoche();
		plazasTotales+=2;
		notifyAll();
	}
}
