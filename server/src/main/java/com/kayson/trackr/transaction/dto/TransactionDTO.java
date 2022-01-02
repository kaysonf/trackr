package com.kayson.trackr.transaction.dto;

import com.kayson.trackr.transaction.Transaction;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDTO {
    private String category;
    private Date date;
    private Float amount;

    public TransactionDTO(Transaction transaction) {
        this.category = transaction.getCategoryName();
        this.date = transaction.getDate();
        this.amount = transaction.getAmount();
    }
}
