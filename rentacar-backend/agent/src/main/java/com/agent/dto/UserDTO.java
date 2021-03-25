package com.agent.dto;

import com.github.rkpunjal.sqlsafe.SQLInjectionSafe;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Username can not be empty")
    @Size(min = 1, max = 128, message = "The maximum length of username is 128 characters")
    private @SQLInjectionSafe String username;

    @NotBlank(message = "Password can not be empty")
    @Size(min = 8, max = 128, message = "The maximum length of password is 128 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", message = "Minimum eight characters, at least one letter and one number")
    private @SQLInjectionSafe  String password;

    @Pattern(regexp = "[a-zA-Z0-9\\s\\-]{1,50}")
    private @SQLInjectionSafe String firstName;

    @Pattern(regexp = "[a-zA-Z0-9\\s\\-]{1,50}")
    private @SQLInjectionSafe String lastName;

    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;


}
