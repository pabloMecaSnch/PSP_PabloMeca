package com.entregableRecu.webservice;


import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import com.entregableRecu.xml.moviles.*;
 
@Component
public class MovilRepository {
    private static final Map<String, Movil> moviles = new HashMap<>();
 
    @PostConstruct
    public void initData() {
         
        Movil movil = new Movil();
        movil.setNombre("Redmi note");
        movil.setTamano(5);
        movil.setMarca("Xiaomi");
        movil.setCamara("13 Mp");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Iphone 12");
        movil.setTamano(12);
        movil.setMarca("Iphone");
        movil.setCamara("15 Mp");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Galaxy s3");
        movil.setTamano(6);
        movil.setMarca("Samsung");
        movil.setCamara("12 Mp");
        moviles.put(movil.getNombre(), movil);
         
        movil = new Movil();
        movil.setNombre("Pixel 4a");
        movil.setTamano(6);
        movil.setMarca("Google");
        movil.setCamara("14 Mp");
        moviles.put(movil.getNombre(), movil);
    }
 
    public Movil findMovil(String name) {
        Assert.notNull(name, "The Movil's name must not be null");
        return moviles.get(name);
    }
    public Caracteristicas findCaracteristicas(String name) {
        Assert.notNull(name, "The Movil's name must not be null");
        Caracteristicas c = new Caracteristicas();
        c.setCamara(moviles.get(name).getCamara());
        c.setTamano(moviles.get(name).getTamano());
        return c;
    }
}
