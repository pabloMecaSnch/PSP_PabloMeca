package ej12Camiones;

import ej12.Parking;
import ej12.Plaza;

public class Camion extends Thread{

	ParkingCamiones p;
	
	private Plaza p1;
	private Plaza p2;
	private int  id;
	
	public int turno;
	
	public Camion(ParkingCamiones p, int i) {
		this.p = p;
		this.id = i;
		this.turno = i;
	}
	
	public Plaza getPlaza() {
		return this.p1;
	}
	public void setPlaza(Plaza p, Plaza p2) {
		this.p1 = p;
		this.p2 = p2;
	}
	public int getIdCamion() {
		return this.id;
	}
	@Override
	public void run() {
		p.aparca(this);
		try {
			Thread.sleep((long)Math.random()*10000);
		}catch (Exception e) {
			// TODO: handle exception
		}
		p.sale(this);
	}
}
