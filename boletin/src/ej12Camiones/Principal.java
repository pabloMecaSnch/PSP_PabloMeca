package ej12Camiones;
import ej12.Coche;
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int turno=0;
		Coche[] coches = new Coche[5];
		Camion[] camiones = new Camion[5];
		ParkingCamiones p = new ParkingCamiones(8);
		
		for (int i = 0; i < camiones.length; i++) {
			camiones[i] = new Camion(p,turno);
			camiones[i].start();
			turno++;
		}
		for (int i = 0; i < coches.length; i++) {
			coches[i] = new Coche(turno,p);
			coches[i].start();
			turno++;
		}
	}

}
