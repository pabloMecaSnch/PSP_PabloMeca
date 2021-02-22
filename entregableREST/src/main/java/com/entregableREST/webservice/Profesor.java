package com.entregableREST.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Profesor {

	private String nombre;
	private String id;
	private Map<String,Materia> materias;
	
	
	public Map<String, Materia> getMaterias() {
		return materias;
	}
	public void setMaterias(Map<String, Materia> materias) {
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
	public void addMateria(Materia... materias) {
		if(this.materias==null) {
			this.materias= new HashMap<>();
		}
		for(int i = 0; i< materias.length ;i++) {
			this.materias.put(materias[i].getId(), materias[i]);
		}
		
	}
}
