package com.kayson.trackr.transactions;

import com.kayson.trackr.wallet.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_name")
    private Category category;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Float amount;

    public Transaction(Wallet wallet, Date date, Float amount, Category category) {
        this.wallet = wallet;
        this.date = date;
        this.amount = amount;
        this.category = category;
    }
}
