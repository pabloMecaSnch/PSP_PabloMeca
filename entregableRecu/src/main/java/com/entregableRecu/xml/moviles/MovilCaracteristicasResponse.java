//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2021.02.17 a las 02:04:00 PM CET 
//


package com.entregablerecu.xml.moviles;

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
 *         &lt;element name="Caracteristicas" type="{http://www.entregableRecu.com/xml/moviles}Caracteristicas"/>
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
    "caracteristicas"
})
@XmlRootElement(name = "MovilCaracteristicasResponse")
public class MovilCaracteristicasResponse {

    @XmlElement(name = "Caracteristicas", required = true)
    protected Caracteristicas caracteristicas;

    /**
     * Obtiene el valor de la propiedad caracteristicas.
     * 
     * @return
     *     possible object is
     *     {@link Caracteristicas }
     *     
     */
    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * Define el valor de la propiedad caracteristicas.
     * 
     * @param value
     *     allowed object is
     *     {@link Caracteristicas }
     *     
     */
    public void setCaracteristicas(Caracteristicas value) {
        this.caracteristicas = value;
    }

}
