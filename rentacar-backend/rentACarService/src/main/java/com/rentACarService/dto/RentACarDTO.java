package com.rentACarService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class RentACarDTO {

    private Date startDate;

    private Date endDate;

    private Long adId;

    private AdDTO adDTO;

    private Long userID;
}
