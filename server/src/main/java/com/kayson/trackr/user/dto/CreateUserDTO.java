package com.kayson.trackr.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CreateUserDTO {

    @NotEmpty
    private String handle;

    @Email(message = "provided email is invalid")
    private String email;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
