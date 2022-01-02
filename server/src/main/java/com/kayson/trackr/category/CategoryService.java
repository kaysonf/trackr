package com.kayson.trackr.category;

import com.kayson.trackr.category.dto.CreateCategoryDTO;
import com.kayson.trackr.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(@Valid CreateCategoryDTO dto) {
        String name = dto.getName();

        Optional<Category> category = categoryRepository.findCategoryByName(name);

        if (category.isPresent()) {
            throw new AlreadyExistsException(String.format("%s category already exists", name));
        }

        Category newCategory = new Category(name);

        return categoryRepository.save(newCategory);
    }

    public Category createCategory(String name) {
        return this.createCategory(new CreateCategoryDTO(name));
    }
}
