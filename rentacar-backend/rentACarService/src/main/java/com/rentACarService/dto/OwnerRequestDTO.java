package com.rentACarService.dto;

import com.rentACarService.model.RentACar;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class OwnerRequestDTO {

    private Long id;

    private Set<RentACar> rentACars;

    private UserDTO user;

    private Date startDate;

    private String status;

    private Date paidDate;
}
