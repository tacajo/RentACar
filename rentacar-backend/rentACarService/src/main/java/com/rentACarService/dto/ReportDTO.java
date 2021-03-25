package com.rentACarService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class ReportDTO {

    private Long id;

    private double kms;

    private String additionalInfo;

    private Long AdID;

    private Long rentACarId;
}
