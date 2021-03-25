package com.adService.model.soap;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name = "PriceList", propOrder = {
        "id",
        "startDate",
        "endDate",
        "price",
        "collisionDamageWaiver",
        "pricePerKm"
}, namespace = "http://adService.com/comment")
public class PriceList {

    private Long id;

    private Date startDate;

    private Date endDate;

    private double price;

    private double collisionDamageWaiver;

    private double pricePerKm;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCollisionDamageWaiver() {
        return collisionDamageWaiver;
    }

    public void setCollisionDamageWaiver(double collisionDamageWaiver) {
        this.collisionDamageWaiver = collisionDamageWaiver;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }
}
