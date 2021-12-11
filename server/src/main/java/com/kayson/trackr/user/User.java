package com.kayson.trackr.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;


@Entity
@JsonIgnoreProperties({"id"})
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(unique = true, updatable = false, nullable = false)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String handle;

    @Column(unique = true, nullable = false)
    @Email
    private String email;

    public UUID getId() {
        return id;
    }

    public String getHandle() {
        return handle;
    }

    public String getEmail() {
        return email;
    }

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
