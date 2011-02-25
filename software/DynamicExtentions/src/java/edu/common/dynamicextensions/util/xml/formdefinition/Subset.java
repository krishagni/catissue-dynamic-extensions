//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0-b26-ea3 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.02.17 at 12:38:09 PM GMT+05:30 
//


package edu.common.dynamicextensions.util.xml.formdefinition;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.AccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import edu.common.dynamicextensions.util.xml.formdefinition.PermissibleValue;
import edu.common.dynamicextensions.util.xml.formdefinition.Subset;


/**
 * <p>Java class for Subset element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="Subset">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element ref="{}permissibleValue" maxOccurs="unbounded"/>
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
    "permissibleValue"
})
@XmlRootElement(name = "Subset")
public class Subset {

    protected List<PermissibleValue> permissibleValue;

    /**
     * Gets the value of the permissibleValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the permissibleValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPermissibleValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PermissibleValue }
     * 
     * 
     */
    public List<PermissibleValue> getPermissibleValue() {
        if (permissibleValue == null) {
            permissibleValue = new ArrayList<PermissibleValue>();
        }
        return this.permissibleValue;
    }

}
