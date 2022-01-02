package com.kayson.trackr.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class AddTransactionDTO {
    @NotEmpty
    private String walletName;

    @NotEmpty
    private String categoryName;

    @NotNull
    private Date date;

    @NotNull
    private Float amount;
}
