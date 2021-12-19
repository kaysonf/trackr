package com.kayson.trackr.auth;

import com.kayson.trackr.validators.Password;

import javax.validation.constraints.NotEmpty;

public class AuthDTO {
    @NotEmpty
    private String handle;
    @Password
    private String password;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
