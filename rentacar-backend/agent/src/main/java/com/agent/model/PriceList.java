package com.agent.model;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class PriceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private double price;

    @Column
    private double collisionDamageWaiver;

    @Column
    private double pricePerKm;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<Ad> ads = new HashSet<>();

    public PriceList(Date startDate, Date endDate, double price, double collisionDamageWaiver, double pricePerKm) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.collisionDamageWaiver = collisionDamageWaiver;
        this.pricePerKm = pricePerKm;
    }
}
