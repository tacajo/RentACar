package com.adService.model.soap;

import lombok.*;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Ad", propOrder = {
        "id",
        "car",
        "city",
        "collisionDamageWaiver",
        "userID",
        "rating",
        "priceList"
}, namespace = "http://adService.com/comment")
public class Ad {
    private Long id;

    private com.adService.model.soap.Car car;

    private String city;

    private boolean collisionDamageWaiver;

    private Long userID;

    private double rating = 0;

    private Long priceList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isCollisionDamageWaiver() {
        return collisionDamageWaiver;
    }

    public void setCollisionDamageWaiver(boolean collisionDamageWaiver) {
        this.collisionDamageWaiver = collisionDamageWaiver;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Long getPriceList() {
        return priceList;
    }

    public void setPriceList(Long priceList) {
        this.priceList = priceList;
    }
}
