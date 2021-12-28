package com.kayson.trackr.user.dto;

import com.kayson.trackr.validators.Password;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateUserDTO {

    @NotEmpty
    private String handle;

    @Email(message = "provided email is invalid")
    private String email;

    @Password
    private String password;
}
