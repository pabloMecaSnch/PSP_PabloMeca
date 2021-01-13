package buzon;

import java.util.ArrayList;

public class Buzon {

	private static  ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();
	
	
	public static Mensaje buscaMensaje(String detinatario) {
		
		for(Mensaje m : mensajes) {
			if(m.getPara().equals(detinatario.trim())) {
				return m;
			}
		}
		//devolver array de mensajes
		return null;
	}
	public static void anadirMensaje(Mensaje m) {
		mensajes.add(m);
	}
	public static void borrarMensaje(Mensaje m) {
		mensajes.remove(m);
	}
}
