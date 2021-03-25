package com.agent.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(cascade = CascadeType.REFRESH)
    private Set<Car> cars = new HashSet<>();

    @ManyToOne
    private CodeBook codeBook;

    public Brand(String name) {
        this.name = name;
    }
}