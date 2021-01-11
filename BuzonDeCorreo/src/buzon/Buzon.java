package buzon;

import java.util.ArrayList;

public class Buzon {

	static public ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
	
	
	public static Mensaje buscaMensaje(String detinatario) {
		
		for(Mensaje m : mensajes) {
			if(m.getPara().equals(detinatario.trim())) {
				return m;
			}
		}
		return null;
	}
	public static void anadirMensaje(Mensaje m) {
		mensajes.add(m);
	}
	public static void borrarMensaje(Mensaje m) {
		mensajes.remove(m);
	}
}
