package com.rentACarService.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
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

}
