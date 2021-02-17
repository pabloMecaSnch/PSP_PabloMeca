package com.entregableREST.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profesor {

	private String nombre;
	private String id;
	private ArrayList<Materia> materias;
	
	
	public ArrayList<Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(ArrayList<Materia> materias) {
		this.materias = materias;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String Id) {
		this.id = Id;
	}
	
}
