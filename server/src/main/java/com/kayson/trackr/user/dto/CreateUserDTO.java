package com.kayson.trackr.user.dto;

import com.kayson.trackr.validators.Password;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CreateUserDTO {

    @NotEmpty
    private String handle;

    @Email(message = "provided email is invalid")
    private String email;

    @Password
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
