package com.agent.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LightSearchDTO {

    private List<Long> ads = new ArrayList<>();

    private String startDate;

    private String endDate;
}
