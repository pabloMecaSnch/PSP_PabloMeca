//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.09 a las 02:01:37 PM CET 
//


package com.entregableRecu.xml.moviles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para Caracteristicas complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="Caracteristicas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tamano" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
@XmlType(name = "Caracteristicas", propOrder = {
    "tamano",
    "camara"
})
public class Caracteristicas {

    protected int tamano;
    @XmlElement(required = true)
    protected String camara;

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
