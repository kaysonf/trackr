package com.kayson.trackr.category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class CategoryConfig {
    @Bean
    CommandLineRunner commandLineRunner(CategoryService categoryService) {
        return args -> {
            Set<String> basicCategories = Set.of(
                    "transport",
                    "groceries",
                    "entertainment",
                    "food",
                    "others"
            );
            basicCategories.forEach(categoryService::createCategory);
        };
    }
}
