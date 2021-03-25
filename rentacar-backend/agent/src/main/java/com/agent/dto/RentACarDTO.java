package com.agent.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RentACarDTO {

    private Date startDate;

    private Date endDate;

    private Long adId;

    private AdDTO adDTO;

    private Long userID;
}
