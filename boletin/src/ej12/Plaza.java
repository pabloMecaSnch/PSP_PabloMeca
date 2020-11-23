package ej12;

import ej12Camiones.Camion;

public class Plaza {

	private Object ocupado;
	private int pos;
	
	public Plaza(int i) {
		this.ocupado = null;
		this.pos = i;
	}
	
	public Plaza(Coche c,int i) {
		this.ocupado = c;
		this.pos = i;
	}
	public Plaza(Camion c, int i) {
		this.ocupado = c;
		this.pos = i;
	}
	
	public Coche getCoche(){
		
		return (Coche)this.ocupado;
	}
	public Object getVehiculo() {
		return this.ocupado;
	}
	
	public void setCoche(Coche c) {
		this.ocupado = c;
	}
	public void setCamion(Camion c) {
		this.ocupado = c;
	}
	
	public void saleCoche() {
		this.ocupado = null;
	}
	
	public int getPos() {
		return this.pos;
	}
}
