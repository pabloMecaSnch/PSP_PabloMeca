package com.entregableREST.webservice;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	private static Map<String, Profesor> profesorRepo = new HashMap<>();
	private static Map<String, Alumno> alumnoRepo = new HashMap<>();
	private static Map<String, Materia> materiaRepo = new HashMap<>();
	static {

		// Materias
		materiaRepo.put("1", new Materia("1", "Mates"));
		materiaRepo.put("2", new Materia("2", "Lengua"));
		materiaRepo.put("3", new Materia("3", "Ciencia"));
		materiaRepo.put("4", new Materia("4", "Tecnología"));
		materiaRepo.put("5", new Materia("5", "Historia"));
		// Profesor1
		Profesor p1 = new Profesor();
		p1.setNombre("Alfonso");
		p1.setId("1");
		Materia[] materiasP1 = new Materia[2];
		materiasP1[0] = materiaRepo.get("1");
		materiasP1[1] = materiaRepo.get("2");
		p1.addMateria();
		profesorRepo.put(p1.getId(), p1);

		// Profesor2
		Profesor almond = new Profesor();
		almond.setId("2");
		almond.setNombre("Almond");
		Materia[] materiasP2 = new Materia[2];
		materiasP2[0] = (materiaRepo.get("3"));
		materiasP2[1] = (materiaRepo.get("5"));
		almond.addMateria(materiasP2);
		profesorRepo.put(almond.getId(), almond);

		// Alumno1
		Alumno al1 = new Alumno();
		al1.setId("1");
		al1.setNombre("Pedro");
		al1.addMateria(materiasP2);

		alumnoRepo.put("1", al1);
		// Alumno2
		Alumno al2 = new Alumno();
		al2.setId("2");
		al2.setNombre("Sandra");
		al2.addMateria(materiasP2);
		alumnoRepo.put("2", al2);
	}

	// --------CRUD profesor--------
	/**
	 * Petición que devuelve un profesor según su id
	 * 
	 * @param id id del profesor
	 * @return JSON del profesor seleccionado
	 */
	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProfesor(@PathVariable("id") String id) {
		return new ResponseEntity<>(profesorRepo.get(id), HttpStatus.OK);
	}

	/**
	 * Petición que obtiene las asignaturas de un Profesor
	 * 
	 * @param id id del profesor
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/profesores/{id}/materias", method = RequestMethod.GET)
	public ResponseEntity<Object> getProfesorMaterias(@PathVariable("id") String id) {
		return new ResponseEntity<>(profesorRepo.get(id).getMaterias(), HttpStatus.OK);
	}

	/**
	 * Petición que devuelve los datos de una materia concreta dentro de los
	 * profesores
	 * 
	 * @param id        id del profesor
	 * @param idMateria id de la materia a mostrar
	 * @return ResponseEntity<>
	 */
	@RequestMapping(value = "/profesores/{id}/materias/{idMateria}", method = RequestMethod.GET)
	public ResponseEntity<Object> getProfesorMaterias(@PathVariable("id") String id,
			@PathVariable("idMateria") String idMateria) {
		return new ResponseEntity<>(profesorRepo.get(id).getMaterias().get(idMateria), HttpStatus.OK);
	}

	/**
	 * Peticion para crear materias dentro de profesor
	 * 
	 * @param idProf  id del profesor
	 * @param materia materia nueva
	 * @return
	 */
	@RequestMapping(value = "/profesores/{idProf}/materias", method = RequestMethod.POST)
	public ResponseEntity<Object> createMateriaProf(@PathVariable("idProf") String idProf,
			@RequestBody Materia materia) {

		materiaRepo.put(materia.getNombre(), materia);
		profesorRepo.get(idProf).getMaterias().put(materia.getId(), materia);
		return new ResponseEntity<>("meteria created", HttpStatus.OK);
	}

	/**
	 * Petición para eliminar una materia desde profesor
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/profesores/{idProf}/materias/{idMat}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMatProf(@PathVariable("idProf") String idProf,
			@PathVariable("idMat") String idMat) {
		profesorRepo.get(idProf).getMaterias().remove(idMat);
		materiaRepo.remove(idMat);
		alumnoRepo.forEach((key, alumno) -> {
			alumno.getMaterias().remove(idMat);
		});
		return new ResponseEntity<>("Materia is deleted successsfully", HttpStatus.OK);
	}

	/**
	 * Petición para actuaalizar los datos de una materia en el profesor
	 * 
	 * @param id      id del profesor
	 * @param materia
	 * @return
	 */
	@RequestMapping(value = "/profesores/{id}/materias/{idMat}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMateriaProf(@PathVariable("id") String id, @PathVariable("idMat") String idMat,
			@RequestBody Materia materia) {
		int idMateria = Integer.parseInt(idMat);
		boolean updateable = true;

		// compruebo si el id nuevo es distinto al anterior
		if (idMateria != Integer.parseInt(materia.getId())) {
			Materia materiaBase = materiaRepo.get(materia.getId());
//			en caso de ser distinto, compruebo que no haya otro id con el mismo valor y cambio sus datos
			if (materiaBase == null) {
				updateable = true;
			} else {
				updateable = false;
			}
		} else {
			updateable = true;
		}
		if (updateable) {
//			reemplazo los datos de la materia en los registros de los profesores
			profesorRepo.forEach((key, profesor) -> {
				profesor.getMaterias().replace(idMat, materia);
			});
//      reemplazo los datos de la materia en los registros de los alumnos
			alumnoRepo.forEach((key, alumno) -> {
				alumno.getMaterias().replace(idMat, materia);
			});
			materiaRepo.replace(idMat, materiaRepo.get(idMat), materia);
			return new ResponseEntity<>("Materia is updated successsfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id utilizado ya está en uso", HttpStatus.CONFLICT);
		}

	}

	/**
	 * Petición para introducir un Profesor
	 * 
	 * @param profesor El profesor a introducir
	 * 
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/profesores", method = RequestMethod.POST)
	public ResponseEntity<Object> createProfesor(@RequestBody Profesor profesor) {
		profesorRepo.put(profesor.getId(), profesor);
		return new ResponseEntity<>("Professor is created successfully", HttpStatus.CREATED);
	}

	/**
	 * Petición que devuelve a todos los profesores
	 * 
	 * @return JSON de todos los profesores
	 */
	@RequestMapping(value = "/profesores")
	public ResponseEntity<Object> getProfesor() {
		return new ResponseEntity<>(profesorRepo.values(), HttpStatus.OK);
	}

	/**
	 * Petición para borrar a un profesor
	 * 
	 * @param id id del profesor a borrar
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteProfesor(@PathVariable("id") String id) {
		profesorRepo.remove(id);
		return new ResponseEntity<>("Professor is deleted successsfully", HttpStatus.OK);
	}

	/**
	 * Petición que modifica un profesor
	 * 
	 * @param id       id del porfesor a editar
	 * @param profesor Los nuevos datos del profesor
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/profesores/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProfesor(@PathVariable("id") String id, @RequestBody Profesor profesor) {
		int idProfe = Integer.parseInt(id);
		boolean updateable=false;
		// compruebo si el id nuevo es distinto al anterior
				if (idProfe != Integer.parseInt(profesor.getId())) {
					Profesor profesorBase = profesorRepo.get(profesor.getId());
//					en caso de ser distinto, compruebo que no haya otro id con el mismo valor y cambio sus datos
					if (profesorBase == null) {
						updateable = true;
					} else {
						updateable = false;
					}
				} else {
					updateable = true;
				}
				if (updateable) {
					profesorRepo.replace(id, profesor);
					return new ResponseEntity<>("Materia is updated successsfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("El id utilizado ya está en uso", HttpStatus.CONFLICT);
				}
	}

	// --------CRUD Alumnnos--------

	/**
	 * Petición que devuelve un alumno según su id
	 * 
	 * @param id id del alumno
	 * @return JSON del alumno seleccionado
	 */
	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAlumno(@PathVariable("id") String id) {
		return new ResponseEntity<>(alumnoRepo.get(id), HttpStatus.OK);
	}

	/**
	 * Petición que obtiene las asignaturas de un alumno
	 * 
	 * @param id id del alumno
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/alumnos/{id}/materias", method = RequestMethod.GET)
	public ResponseEntity<Object> getAlumnoMaterias(@PathVariable("id") String id) {
		return new ResponseEntity<>(alumnoRepo.get(id).getMaterias(), HttpStatus.OK);
	}

	/**
	 * Petición para introducir un alumno
	 * 
	 * @param profesor El profesor a introducir
	 * 
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/alumnos", method = RequestMethod.POST)
	public ResponseEntity<Object> createAlumno(@RequestBody Alumno alumno) {
		alumnoRepo.put(alumno.getId(), alumno);
		return new ResponseEntity<>("alumno is created successfully", HttpStatus.CREATED);
	}

	/**
	 * Petición que devuelve a todos los alumnos
	 * 
	 * @return JSON de todos los alumnos
	 */
	@RequestMapping(value = "/alumnos")
	public ResponseEntity<Object> getAlumno() {
		return new ResponseEntity<>(alumnoRepo.values(), HttpStatus.OK);
	}

	/**
	 * Petición para borrar a un alumno
	 * 
	 * @param id id del alumno a borrar
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAlumno(@PathVariable("id") String id) {
		alumnoRepo.remove(id);
		return new ResponseEntity<>("alumno is deleted successsfully", HttpStatus.OK);
	}

	/**
	 * Petición que modifica un alumno
	 * 
	 * @param id       id del alumno a editar
	 * @param profesor Los nuevos datos del alumno
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/alumnos/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateAlumno(@PathVariable("id") String id, @RequestBody Alumno alumno) {
		int idAlu = Integer.parseInt(id);
		boolean updateable=false;
		// compruebo si el id nuevo es distinto al anterior
				if (idAlu != Integer.parseInt(alumno.getId())) {
					Alumno alumnoBase = alumnoRepo.get(alumno.getId());
//					en caso de ser distinto, compruebo que no haya otro id con el mismo valor y cambio sus datos
					if (alumnoBase == null) {
						updateable = true;
					} else {
						updateable = false;
					}
				} else {
					updateable = true;
				}
				if (updateable) {
					alumnoRepo.replace(id, alumno);
					return new ResponseEntity<>("Materia is updated successsfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("El id utilizado ya está en uso", HttpStatus.CONFLICT);
				}
	}
	// --------CRUD Materias--------

	/**
	 * Petición que devuelve una materia según su id
	 * 
	 * @param id id de la materia
	 * @return JSON de la materia seleccionada
	 */
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMateria(@PathVariable("id") String id) {
		return new ResponseEntity<>(materiaRepo.get(id), HttpStatus.OK);
	}

	/**
	 * Petición para introducir una materia
	 * 
	 * @param materia La materia a introducir
	 * 
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/materias", method = RequestMethod.POST)
	public ResponseEntity<Object> createAlumno(@RequestBody Materia materia) {
		materiaRepo.put(materia.getId(), materia);
		return new ResponseEntity<>("materia is created successfully", HttpStatus.CREATED);
	}

	/**
	 * Petición que devuelve todas las materias
	 * 
	 * @return JSON de todos los alumnos
	 */
	@RequestMapping(value = "/materias")
	public ResponseEntity<Object> getMateria() {
		return new ResponseEntity<>(materiaRepo.values(), HttpStatus.OK);
	}

	/**
	 * Petición para borrar una materia
	 * 
	 * @param id id de la materia a borrar
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteMateria(@PathVariable("id") String id) {
//				   borrado de las materias en los registros de profesores para mantener la integridad de los datos
		profesorRepo.forEach((key, profesor) -> {
			profesor.getMaterias().remove(id);
		});
//				   borrado de las materias en los registros de alumnos para mantener la integridad de los datos
		alumnoRepo.forEach((key, alumno) -> {
			alumno.getMaterias().remove(id);
		});
		materiaRepo.remove(id);
		return new ResponseEntity<>("materia is deleted successsfully", HttpStatus.OK);
	}

	/**
	 * Petición que modifica una materia
	 * 
	 * @param id       id de la materia a editar
	 * @param profesor Los nuevos datos de la materia
	 * @return ResponseEntity<>()
	 */
	@RequestMapping(value = "/materias/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateMateria(@PathVariable("id") String id, @RequestBody Materia materia) {
		int idMateria = Integer.parseInt(id);
		boolean updateable;
		// compruebo si el id nuevo es distinto al anterior
		if (idMateria != Integer.parseInt(materia.getId())) {
			Materia materiaBase = materiaRepo.get(materia.getId());
//					en caso de ser distinto, compruebo que no haya otro id con el mismo valor y cambio sus datos
			if (materiaBase == null) {
				updateable = true;
			} else {
				updateable = false;
			}
		} else {
			updateable = true;
		}
		if (updateable) {
//					reemplazo los datos de la materia en los registros de los profesores
			profesorRepo.forEach((key, profesor) -> {
				profesor.getMaterias().replace(id, materia);
			});
//		      reemplazo los datos de la materia en los registros de los alumnos
			alumnoRepo.forEach((key, alumno) -> {
				alumno.getMaterias().replace(id, materia);
			});
			materiaRepo.replace(id, materia);
			return new ResponseEntity<>("Materia is updated successsfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("El id utilizado ya está en uso", HttpStatus.CONFLICT);
		}
	}
}
