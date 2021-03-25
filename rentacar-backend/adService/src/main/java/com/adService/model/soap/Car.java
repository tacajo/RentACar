package com.adService.model.soap;

import lombok.*;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;

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
}, namespace = "http://adService.com/comment")
public class Car {

    private Long id;

    private com.adService.model.soap.Brand brand;

    private com.adService.model.soap.Model model;

    private com.adService.model.soap.Fuel fuel;

    private com.adService.model.soap.GearShift gearShift;

    private com.adService.model.soap.CarClass carClass;

    private int traveledKms;

    private int limitKms;

    private int childSeats;

    private com.adService.model.soap.Ad ad;

    private Byte[] image;

    private ArrayList<String> imageNames = new ArrayList<String>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.adService.model.soap.Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public com.adService.model.soap.Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public com.adService.model.soap.Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public com.adService.model.soap.GearShift getGearShift() {
        return gearShift;
    }

    public void setGearShift(GearShift gearShift) {
        this.gearShift = gearShift;
    }

    public com.adService.model.soap.CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public int getTraveledKms() {
        return traveledKms;
    }

    public void setTraveledKms(int traveledKms) {
        this.traveledKms = traveledKms;
    }

    public int getLimitKms() {
        return limitKms;
    }

    public void setLimitKms(int limitKms) {
        this.limitKms = limitKms;
    }

    public int getChildSeats() {
        return childSeats;
    }

    public void setChildSeats(int childSeats) {
        this.childSeats = childSeats;
    }

    public com.adService.model.soap.Ad getAd() {
        return ad;
    }

    public void setAd(Ad ad) {
        this.ad = ad;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public ArrayList<String> getImageNames() {
        return imageNames;
    }

    public void setImageNames(ArrayList<String> imageNames) {
        this.imageNames = imageNames;
    }
}
