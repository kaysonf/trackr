package com.kayson.trackr.transaction.dto;

import com.kayson.trackr.validators.CategoryName;
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
    @CategoryName
    private String categoryName;

    @NotNull
    private Date date;

    @NotNull
    private Float amount;
}
