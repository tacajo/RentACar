package com.rentACarService.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private double kms;

    @Column
    private String additionalInfo;

    @Column
    private Long AdID;

    @Column
    private Long rentACarId;
}