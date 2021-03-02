package com.entregableREST.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profesor {

	private String nombre;
	private String id;
	private Map<String,Materia> materias;
	
	/**
	 * Método que devuelve el Map de materias del Profesor
	 * @return Map<String,Materia>
	 */
	public Map<String, Materia> getMaterias() {
		return materias;
	}
	/**
	 * Método para setear el valor del Map de profesores
	 * @param materias Mapa de materias<String,Materia>
	 */
	public void setMaterias(Map<String, Materia> materias) {
		this.materias = materias;
	}
	/**
	 * Método para obtener el nombre del profesor
	 * @return nombre del profesor
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Método para setear el nombre del Profesor
	 * @param nombre nombre del profesor
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método para obtener el Id del profesor
	 * @return id del profesor
	 */
	public String getId() {
		return id;
	}
	/**
	 *Método para setear el profesor
	 * @param Id Id del profesor
	 */
	public void setId(String Id) {
		this.id = Id;
	}
	/**
	 * Método para añadir materias a la lista Map del Profesor
	 * @param materias materias que se añadirán a la lista de Materias, de 1 a N
	 */
	public void addMateria(Materia... materias) {
		if(this.materias==null) {
			this.materias= new HashMap<>();
		}
		for(int i = 0; i< materias.length ;i++) {
			this.materias.put(materias[i].getId(), materias[i]);
		}
		
	}
}
