package com.agent.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class CartDTO {

    private Long id;

    private Date startDate;

    private Date endDate;

    private Long adId;

    private AdDTO ad;

}
