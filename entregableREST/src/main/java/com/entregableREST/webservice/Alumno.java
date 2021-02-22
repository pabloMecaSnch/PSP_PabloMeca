package com.entregableREST.webservice;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Alumno {

	private String id;
	private String nombre;
	private Map<String,Materia> materias;
	
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
	public Map<String, Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(Map<String, Materia> materias) {
		this.materias = materias;
	}
	public void addMateria(Materia... materias) {
		if(this.materias==null) {
			this.materias= new HashMap<>();
		}
		for(int i = 0; i< materias.length ;i++) {
			this.materias.put(materias[i].getId(), materias[i]);
		}
		
	}
	
	
}
