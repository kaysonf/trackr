package com.kayson.trackr.category;

import com.kayson.trackr.validators.CategoryName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, updatable = false, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    @CategoryName
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
