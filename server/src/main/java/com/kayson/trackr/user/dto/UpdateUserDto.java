package com.kayson.trackr.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

public class UpdateUserDto {

    @com.kayson.trackr.validators.UUID(message = "no such user id")
    private String id;

    @NotEmpty
    private String handle;

    @Email(message = "provided email is invalid")
    private String email;

    public UUID getId() {
        return UUID.fromString(id);
    }

    public String getHandle() {
        return handle;
    }

    public String getEmail() {
        return email;
    }

}
