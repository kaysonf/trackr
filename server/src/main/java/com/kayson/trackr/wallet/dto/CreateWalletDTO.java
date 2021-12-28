package com.kayson.trackr.wallet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CreateWalletDTO {
    @NotEmpty
    private String name;
}
