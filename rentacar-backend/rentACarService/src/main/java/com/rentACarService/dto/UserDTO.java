package com.rentACarService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;
}
