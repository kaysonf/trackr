package com.kayson.trackr.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UpdateUserDto {

    @NotEmpty
    private String handle;

    @Email(message = "provided email is invalid")
    private String email;

}
