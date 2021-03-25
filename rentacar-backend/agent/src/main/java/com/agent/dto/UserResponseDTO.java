package com.agent.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserResponseDTO {

    private Long id;

    private  String firstName;

    private String lastName;

}
