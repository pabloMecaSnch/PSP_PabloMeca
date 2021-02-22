//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.17 a las 02:04:00 PM CET 
//


package com.demo.xml.moviles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Movil complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Movil">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tamano" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="marca" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="camara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Movil", propOrder = {
    "nombre",
    "tamano",
    "marca",
    "camara"
})
public class Movil {

    @XmlElement(required = true)
    protected String nombre;
    protected int tamano;
    @XmlElement(required = true)
    protected String marca;
    @XmlElement(required = true)
    protected String camara;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad tamano.
     * 
     */
    public int getTamano() {
        return tamano;
    }

    /**
     * Define el valor de la propiedad tamano.
     * 
     */
    public void setTamano(int value) {
        this.tamano = value;
    }

    /**
     * Obtiene el valor de la propiedad marca.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define el valor de la propiedad marca.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarca(String value) {
        this.marca = value;
    }

    /**
     * Obtiene el valor de la propiedad camara.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCamara() {
        return camara;
    }

    /**
     * Define el valor de la propiedad camara.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCamara(String value) {
        this.camara = value;
    }

}
