package com.agent.dto;
import lombok.*;
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
