package com.entregableREST.webservice;

import java.awt.List;
import java.util.ArrayList;
import java.util.Map;

public class Alumno {

	private String id;
	private String nombre;
	private ArrayList<Materia> materias;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
	}
	
	
}
