package com.adService.dto;
import lombok.*;
import java.util.ArrayList;

@Getter
@Setter
@Builder
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
    private ArrayList<String> imageNames;

    public CarDTO(String model, String brand, String fuel, String gearShift, String carClass, int traveledKms, int childSeats, int limitKms, ArrayList<String> imageNames) {
        this.model = model;
        this.brand = brand;
        this.fuel = fuel;
        this.gearShift = gearShift;
        this.carClass = carClass;
        this.traveledKms = traveledKms;
        this.childSeats = childSeats;
        this.limitKms = limitKms;
        this.imageNames = imageNames;
    }
}

