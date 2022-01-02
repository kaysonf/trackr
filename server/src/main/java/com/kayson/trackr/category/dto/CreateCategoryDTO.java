package com.kayson.trackr.category.dto;

import com.kayson.trackr.validators.CategoryName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCategoryDTO {
    @CategoryName
    private String name;

    public CreateCategoryDTO(String name) {
        this.name = name;
    }
}
