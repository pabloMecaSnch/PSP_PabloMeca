package ej12;

public class Plaza {

	private Coche ocupado;
	private int pos;
	
	public Plaza(int i) {
		this.ocupado = null;
		this.pos = i;
	}
	
	public Plaza(Coche c,int i) {
		this.ocupado = c;
		this.pos = i;
	}
	
	public Coche getCoche(){
		
		return this.ocupado;
	}
	
	public void setCoche(Coche c) {
		this.ocupado = c;
	}
	
	public void saleCoche() {
		this.ocupado = null;
	}
	
	public int getPos() {
		return this.pos;
	}
}
