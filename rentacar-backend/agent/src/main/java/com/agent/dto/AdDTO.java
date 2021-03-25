package com.agent.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class AdDTO {
    private Long id;
    private CarDTO car;
    private String city;
    private boolean collisionDamageWaiver;
    private Long userID;
    private Date startDate;
    private Date endDate;
    private double rating;
    private Long priceListID;
    private double discount;

    public AdDTO(Long id, CarDTO car, String city, boolean collisionDamageWaiver, Long userID, Date startDate, Date endDate, double rating) {
        this.id = id;
        this.car = car;
        this.city = city;
        this.collisionDamageWaiver = collisionDamageWaiver;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
    }
}
