package com.sjryu.boardback.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignUpEmailCheckDto {

    @NotBlank @Email
    private String email;
}
