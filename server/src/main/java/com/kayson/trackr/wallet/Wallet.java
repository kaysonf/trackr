package com.kayson.trackr.wallet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kayson.trackr.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"user"})
@Table(
        name = "wallets",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "name"})
        })
@Getter
@Setter
@NoArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private float balance;

    public Wallet(User user, String name) {
        this.user = user;
        this.balance = 0;
        this.name = name;
    }
}
