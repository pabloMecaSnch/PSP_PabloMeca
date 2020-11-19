package ej12;


public class Coche extends Thread {
	
	Parking p;
	
	private int idCoche;
	private Plaza plaza;
	
	public Coche(int id,Parking p) {
		this.idCoche = id;
		this.plaza = null;
		this.p = p;
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
		System.out.println("Empieza hilo coche "+this.idCoche);
		double time = Math.random()*10000;
		p.aparca(this);
		try {
			Thread.sleep((long)time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.sale(this);
	}
	
	
}
