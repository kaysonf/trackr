package com.kayson.trackr.transactions;

import com.kayson.trackr.exceptions.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(String name) {
        Optional<Category> category = categoryRepository.findCategoryByName(name);

        if (category.isPresent()) {
            throw new AlreadyExistsException(String.format("%s category already exists", name));
        }

        Category newCategory = new Category(name);

        return categoryRepository.save(newCategory);
    }
}
