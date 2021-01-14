package buzon;

public class Mensaje {

	private String de;
	private String para;
	private String mensaje;

	public Mensaje() {

	}

	/**
	 * Método contructor de la clase
	 * 
	 * @param de      Persona que escribe el mensaje
	 * @param para    Persona que tiene que recibir el mensaje
	 * @param mensaje El mensaje en sí
	 */
	public Mensaje(String de, String para, String mensaje) {
		this.de = de;
		this.para = para;
		this.mensaje = mensaje;
	}

	/**
	 * Método que devuelve el autor del mensaje
	 * 
	 * @return de
	 */
	public String getDe() {
		return de;
	}

	/**
	 * Método que modifica el campo del autor del mensaje
	 * 
	 * @param de Nuevo autor
	 */
	public void setDe(String de) {
		this.de = de;
	}

	/**
	 * Método que devuelve el destinatario del mensaje
	 * 
	 * @return para
	 */
	public String getPara() {
		return para;
	}

	/**
	 * Método que modifica el destinatario del mensaje
	 * 
	 * @param para Nuevo destinatario
	 */
	public void setPara(String para) {
		this.para = para;
	}

	/**
	 * Método que devuelve el contenido del mensaje
	 * 
	 * @return mensaje
	 */
	public String getMensaje() {
		return this.mensaje;
	}

	/**
	 * Método que modifica el mensaje
	 * 
	 * @param mensaje Nuevo mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Método utilizado para definir los valores a medir para saber si un mensaje es igual a otro
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		} else {
			return this.de.equals(((Mensaje) o).de) && this.para.equals(((Mensaje) o).para)
					&& this.mensaje.equals(((Mensaje) o).mensaje);
		}
	}

}
