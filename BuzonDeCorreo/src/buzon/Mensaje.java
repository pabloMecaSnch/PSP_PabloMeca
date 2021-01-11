package buzon;

public class Mensaje {

	private String de;
	private String para;
	private String mensaje;
	
	
	public Mensaje() {
		
	}
	
	public Mensaje(String de, String para, String mensaje) {
		this.de = de;
		this.para = para;
		this.mensaje = mensaje;
	}
	
	public String getDe() {
		return de;
	}
	public void setDe(String de) {
		this.de = de;
	}
	public String getPara() {
		return para;
	}
	public void setPara(String para) {
		this.para = para;
	}
	public String getMensaje() {
		return this.mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	@Override
	public boolean equals(Object o) {
		return this.de.equals(((Mensaje)o).de) 
				&& this.para.equals(((Mensaje)o).para)
				&& this.mensaje.equals(((Mensaje)o).mensaje);
		
	}
	@Override
	public String toString() {
		return this.mensaje;
	}
	
	
}
