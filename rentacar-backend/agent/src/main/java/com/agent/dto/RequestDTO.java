package com.agent.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class RequestDTO {

    private List<Long> rentACars;

    private boolean bundle;
}
