package com.kayson.trackr.user.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class GetUserDto {

    @NotNull
    @NotBlank
    private String handle;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }
}
