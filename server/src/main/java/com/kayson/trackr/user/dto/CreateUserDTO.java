package com.kayson.trackr.user.dto;

import lombok.Getter;
import lombok.Setter;

public class CreateUserDTO {
    @Getter @Setter private String handle;
    @Getter @Setter private String email;
}
