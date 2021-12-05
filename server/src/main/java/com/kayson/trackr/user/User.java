package com.kayson.trackr.user;

import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(unique = true, updatable = false, nullable = false)
    @Getter private UUID id;

    @Column(unique = true, nullable = false)
    @Getter private String handle;

    @Column(unique = true, nullable = false)
    @Getter private String email;

    public User() {}

    public User(String handle, String email) {
        this.handle = handle;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id.toString() + '\'' +
                ", handle='" + handle + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
