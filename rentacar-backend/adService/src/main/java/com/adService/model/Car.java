package com.adService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Model model;

    @ManyToOne
    private Fuel fuel;

    @ManyToOne
    private GearShift gearShift;

    @ManyToOne
    private CarClass carClass;

    @Column
    private int traveledKms;

    @Column
    private int limitKms;

    @Column
    private int childSeats;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Ad ad;

    @Column
    @Lob
    private Byte[] image;

    @Column
    private ArrayList<String> imageNames= new ArrayList<String>();


}
