package com.adService.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Car car;

    @Column
    private String city;

    @Column
    private boolean collisionDamageWaiver;

    @Column
    private Long userID;

    @Column
    private double rating;

    @OneToMany(mappedBy = "ad", cascade = CascadeType.REFRESH)
    private Set<Comment> comments = new HashSet<>();

    @JsonBackReference
    @ManyToOne
    private PriceList priceList;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column(nullable = true)
    private double discount;
}
