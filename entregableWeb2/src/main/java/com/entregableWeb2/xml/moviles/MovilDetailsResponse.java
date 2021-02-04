//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.04 a las 04:29:55 PM CET 
//


package com.entregableWeb2.xml.moviles;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Movil" type="{http://www.entregableWeb2.com/xml/moviles}Movil"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "movil"
})
@XmlRootElement(name = "MovilDetailsResponse")
public class MovilDetailsResponse {

    @XmlElement(name = "Movil", required = true)
    protected Movil movil;

    /**
     * Obtiene el valor de la propiedad movil.
     * 
     * @return
     *     possible object is
     *     {@link Movil }
     *     
     */
    public Movil getMovil() {
        return movil;
    }

    /**
     * Define el valor de la propiedad movil.
     * 
     * @param value
     *     allowed object is
     *     {@link Movil }
     *     
     */
    public void setMovil(Movil value) {
        this.movil = value;
    }

}
