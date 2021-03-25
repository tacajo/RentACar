package com.rentACarService.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rentACarService.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.*;


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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date reservedDate;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date endPaid;

    @Column
    private Long userID;

    @JsonIgnore
    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<RentACar> rentACars = new HashSet<RentACar>(); //oglasi sa start i end date
}
