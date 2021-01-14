package buzon;

import java.util.ArrayList;
import java.util.Collections;

public class Buzon {

	private static ArrayList<Mensaje> mensajes = new ArrayList<Mensaje>();

	/**
	 * Método que se encarga de buscar los mensajes de un usuario en concreto
	 * 
	 * @param detinatario Persona a la que van destinados los mensajes
	 * @return ArrayList de los mensajes que tiene por leer
	 */
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

	/**
	 * Método que se encarga de guardar en el ArrayList de mensajes un nuevo mensaje
	 * 
	 * @param m El mensaje que se a guardar
	 */
	public static void anadirMensaje(Mensaje m) {
		mensajes.add(m);
	}
/**
 * Método que se encarga de borrar un mensaje del ArrayList
 * @param m El mensaje que se quiere borrar
 */
	public static void borrarMensaje(Mensaje m) {
		mensajes.remove(m);
	}
}
