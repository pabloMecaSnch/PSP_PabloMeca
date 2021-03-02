package com.entregableREST.webservice;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alumno {

	private String id;
	private String nombre;
	private Map<String,Materia> materias;
	
	/**
	 * Método para obtener el id del Alumno
	 * @return id del alumno
	 */
	public String getId() {
		return id;
	}
	/**
	 * Método para setear el  del Alumno
	 * @param id id del alumno
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Método para obtener el nombre del Alumno
	 * @return nombre del Alumno
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Método para setear el nombre del Alumno
	 * @param nombre nombre del alumno
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Método para obtener el Map<String,Materia> del Alumno
	 * @return lista de materias del alumno
	 */
	public Map<String, Materia> getMaterias() {
		return materias;
	}
	/**
	 * Método para setear el Map<String,Materia> del Alumno
	 * @param materias lista de materias del alumno
	 */
	public void setMaterias(Map<String, Materia> materias) {
		this.materias = materias;
	}
	/**
	 * Método para añadir materias al alumno
	 * @param materias materias del alumno, de 1 a N materias
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
