//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.17 at 12:38:09 PM GMT+05:30 
//


package edu.common.dynamicextensions.util.xml.formdefinition;

import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.common.dynamicextensions.util.xml.formdefinition.ControllingAttribute;
import edu.common.dynamicextensions.util.xml.formdefinition.SkipLogic;


/**
 * <p>Java class for SkipLogic element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="SkipLogic">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}ControllingAttribute"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(AccessType.FIELD)
@XmlType(name = "", propOrder = {
    "controllingAttribute"
})
@XmlRootElement(name = "SkipLogic")
public class SkipLogic {

    @XmlElement(name = "ControllingAttribute")
    protected ControllingAttribute controllingAttribute;

    /**
     * Gets the value of the controllingAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link ControllingAttribute }
     *     
     */
    public ControllingAttribute getControllingAttribute() {
        return controllingAttribute;
    }

    /**
     * Sets the value of the controllingAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link ControllingAttribute }
     *     
     */
    public void setControllingAttribute(ControllingAttribute value) {
        this.controllingAttribute = value;
    }

}
