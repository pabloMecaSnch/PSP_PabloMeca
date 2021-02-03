package com.entregableWeb.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.entregableWeb.xml.moviles.*;
 
@Component
public class MovilRepository {
    private static final Map<String, Movil> moviles = new HashMap<>();
 
    @PostConstruct
    public void initData() {
         
        Movil movil = new Movil();
        movil.setNombre("Sajal");
        movil.setTamano(5);
        movil.setMarca("Pune");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Kajal");
        movil.setTamano(5);
        movil.setMarca("Chicago");
        students.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Lokesh");
        movil.setTamano(6);
        movil.setMarca("Delhi");
        students.put(movil.getNombre(), movil);
         
        movil = new Student();
        movil.setNombre("Sukesh");
        movil.setTamano(7);
        movil.setMarca("Noida");
        students.put(movil.getNombre(), movil);
    }
 
    public Student findStudent(String name) {
        Assert.notNull(name, "The Student's name must not be null");
        return students.get(name);
    }
}