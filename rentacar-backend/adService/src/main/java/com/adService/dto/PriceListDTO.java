package com.adService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PriceListDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private double price;
    private double collisionDamageWaiver;
    private double pricePerKm;

}
