package com.entregableWeb2.webservice;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.entregableWeb2.xml.moviles.*;
 
@Component
public class MovilRepository {
    private static final Map<String, Movil> moviles = new HashMap<>();
 
    @PostConstruct
    public void initData() {
         
        Movil movil = new Movil();
        movil.setNombre("Redmi note");
        movil.setTamano(5);
        movil.setMarca("Xiaomi");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Iphone 12");
        movil.setTamano(12);
        movil.setMarca("Iphone");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Galaxy s3");
        movil.setTamano(6);
        movil.setMarca("Samsung");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Pixel 4a");
        movil.setTamano(6);
        movil.setMarca("Google");
        moviles.put(movil.getNombre(), movil);
    }
 
    public Movil findMovil(String name) {
        Assert.notNull(name, "The Movil's name must not be null");
        return moviles.get(name);
    }
}