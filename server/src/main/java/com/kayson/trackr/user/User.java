package com.kayson.trackr.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kayson.trackr.validators.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;


@Entity
@JsonIgnoreProperties({"password"})
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
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

    @Column(nullable = false)
    @Password
    private String password;

    public User(String handle, String email, String password) {
        this.handle = handle;
        this.email = email;
        this.password = password;
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
