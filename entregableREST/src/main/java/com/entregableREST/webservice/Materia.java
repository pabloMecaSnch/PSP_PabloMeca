package com.entregableREST.webservice;

public class Materia {

	private String id;
	private String nombre;
	
	/**
	 * Constructor de Materia
	 * @param id id de la Materia
	 * @param nombre nombre de la Materia
	 */
	public Materia(String id, String nombre) {
		this.id = id;
		this.nombre= nombre;
	}
	/**
	 * Método para obtener el id de la Materia
	 * @return id de la materia
	 */
	public String getId() {
		return id;
	}
	/**
	 * Método para setear el id de la Materia
	 * @param id id de la materia
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Método para obtener el nombre de la Materia
	 * @return nombre de la materia
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Método para setear el nombre de la Materia
	 * @param nombre nombre de la materia
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
