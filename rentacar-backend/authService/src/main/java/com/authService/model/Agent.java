package com.authService.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@DiscriminatorValue("agent")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Agent extends User {

    @Column
    private String address;

    @Column
    private String businessNumber;


}
