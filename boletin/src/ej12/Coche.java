package ej12;


public class Coche extends Thread {
	
	Parking p;
	
	private int idCoche;
	private Plaza plaza;
	
	public Coche(int id) {
		this.idCoche = id;
		this.plaza = null;
	}

	public Coche() {
		this.plaza = null;
	}


	public int getIdCoche() {
		return this.idCoche;
	}
	
	public void setPlaza(Plaza p) {
		this.plaza = p;
	}
	
	public Plaza getPlaza() {
		return this.plaza;
	}
	
	@Override
	public void run() {
		double time = Math.random()*1000;
		p.aparca(this);
		try {
			wait((long) time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.sale(this);
	}
	
	
}
