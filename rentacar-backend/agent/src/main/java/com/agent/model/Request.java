package com.agent.model;

import com.agent.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private RequestStatus status;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date startDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endDate;

    @Column
    private Long userID;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date reservedDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endPaid;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RentACar> rentACars = new HashSet<RentACar>(); //oglasi sa start i end date
}
