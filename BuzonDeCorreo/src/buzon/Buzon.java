package buzon;

import java.util.ArrayList;
import java.util.Collections;

public class Buzon {

	private static ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	public static ArrayList<Mensaje> buscaMensaje(String detinatario) {

		ArrayList<Mensaje> coleccionMensajes = new ArrayList<>();
		for (Mensaje m : mensajes) {
			if (m.getPara().equals(detinatario.trim())) {
				coleccionMensajes.add(m);
			}
		}
		// devolver array de mensajes
		return coleccionMensajes;
	}

	public static void anadirMensaje(Mensaje m) {
		mensajes.add(m);
	}

	public static void borrarMensaje(Mensaje m) {
		mensajes.remove(m);
	}
}
