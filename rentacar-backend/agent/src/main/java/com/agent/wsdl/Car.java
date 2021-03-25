//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.07.11 at 01:55:38 AM CEST 
//


package com.agent.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Car complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Car"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="brand" type="{http://adService.com/comment}Brand" minOccurs="0"/&gt;
 *         &lt;element name="model" type="{http://adService.com/comment}Model" minOccurs="0"/&gt;
 *         &lt;element name="fuel" type="{http://adService.com/comment}Fuel" minOccurs="0"/&gt;
 *         &lt;element name="gearShift" type="{http://adService.com/comment}GearShift" minOccurs="0"/&gt;
 *         &lt;element name="carClass" type="{http://adService.com/comment}CarClass" minOccurs="0"/&gt;
 *         &lt;element name="traveledKms" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="limitKms" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="childSeats" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ad" type="{http://adService.com/comment}Ad" minOccurs="0"/&gt;
 *         &lt;element name="image" type="{http://www.w3.org/2001/XMLSchema}byte" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="imageNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Car", propOrder = {
    "id",
    "brand",
    "model",
    "fuel",
    "gearShift",
    "carClass",
    "traveledKms",
    "limitKms",
    "childSeats",
    "ad",
    "image",
    "imageNames"
})
public class Car {

    protected Long id;
    protected Brand brand;
    protected Model model;
    protected Fuel fuel;
    protected GearShift gearShift;
    protected CarClass carClass;
    protected int traveledKms;
    protected int limitKms;
    protected int childSeats;
    protected Ad ad;
    @XmlElement(nillable = true)
    protected List<Byte> image;
    @XmlElement(nillable = true)
    protected List<String> imageNames;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link Brand }
     *     
     */
    public Brand getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Brand }
     *     
     */
    public void setBrand(Brand value) {
        this.brand = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link Model }
     *     
     */
    public Model getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link Model }
     *     
     */
    public void setModel(Model value) {
        this.model = value;
    }

    /**
     * Gets the value of the fuel property.
     * 
     * @return
     *     possible object is
     *     {@link Fuel }
     *     
     */
    public Fuel getFuel() {
        return fuel;
    }

    /**
     * Sets the value of the fuel property.
     * 
     * @param value
     *     allowed object is
     *     {@link Fuel }
     *     
     */
    public void setFuel(Fuel value) {
        this.fuel = value;
    }

    /**
     * Gets the value of the gearShift property.
     * 
     * @return
     *     possible object is
     *     {@link GearShift }
     *     
     */
    public GearShift getGearShift() {
        return gearShift;
    }

    /**
     * Sets the value of the gearShift property.
     * 
     * @param value
     *     allowed object is
     *     {@link GearShift }
     *     
     */
    public void setGearShift(GearShift value) {
        this.gearShift = value;
    }

    /**
     * Gets the value of the carClass property.
     * 
     * @return
     *     possible object is
     *     {@link CarClass }
     *     
     */
    public CarClass getCarClass() {
        return carClass;
    }

    /**
     * Sets the value of the carClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link CarClass }
     *     
     */
    public void setCarClass(CarClass value) {
        this.carClass = value;
    }

    /**
     * Gets the value of the traveledKms property.
     * 
     */
    public int getTraveledKms() {
        return traveledKms;
    }

    /**
     * Sets the value of the traveledKms property.
     * 
     */
    public void setTraveledKms(int value) {
        this.traveledKms = value;
    }

    /**
     * Gets the value of the limitKms property.
     * 
     */
    public int getLimitKms() {
        return limitKms;
    }

    /**
     * Sets the value of the limitKms property.
     * 
     */
    public void setLimitKms(int value) {
        this.limitKms = value;
    }

    /**
     * Gets the value of the childSeats property.
     * 
     */
    public int getChildSeats() {
        return childSeats;
    }

    /**
     * Sets the value of the childSeats property.
     * 
     */
    public void setChildSeats(int value) {
        this.childSeats = value;
    }

    /**
     * Gets the value of the ad property.
     * 
     * @return
     *     possible object is
     *     {@link Ad }
     *     
     */
    public Ad getAd() {
        return ad;
    }

    /**
     * Sets the value of the ad property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ad }
     *     
     */
    public void setAd(Ad value) {
        this.ad = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Byte }
     * 
     * 
     */
    public List<Byte> getImage() {
        if (image == null) {
            image = new ArrayList<Byte>();
        }
        return this.image;
    }

    /**
     * Gets the value of the imageNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the imageNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImageNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getImageNames() {
        if (imageNames == null) {
            imageNames = new ArrayList<String>();
        }
        return this.imageNames;
    }

}