package com.agent.dto;

import com.agent.model.RentACar;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
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
