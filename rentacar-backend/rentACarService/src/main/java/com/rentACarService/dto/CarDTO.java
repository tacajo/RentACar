package com.rentACarService.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class CarDTO {

    private String model;
    private String brand;
    private String fuel;
    private String gearShift;
    private String carClass;
    private int traveledKms;
    private int childSeats;
    private int limitKms;
    private MultipartFile image;
    private String imageName;
}
