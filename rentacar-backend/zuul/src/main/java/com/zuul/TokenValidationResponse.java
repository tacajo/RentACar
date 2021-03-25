package com.zuul;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TokenValidationResponse {
    private Boolean isValid = false;
    private Integer userId;
    private String username;
    private String authorities;
}