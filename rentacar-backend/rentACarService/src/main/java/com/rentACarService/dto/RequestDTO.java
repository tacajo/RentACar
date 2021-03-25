package com.rentACarService.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RequestDTO {

    private List<Long> rentACars;

    private boolean bundle;
}
