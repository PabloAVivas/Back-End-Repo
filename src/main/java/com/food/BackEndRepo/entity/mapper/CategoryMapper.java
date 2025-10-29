package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Category;
import com.food.BackEndRepo.entity.dto.category.CategoryCreate;
import com.food.BackEndRepo.entity.dto.category.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity (CategoryCreate categoryC){
        Category category = new Category();
        category.setName(categoryC.name());
        category.setDescription(categoryC.description());
        category.setUrl(categoryC.url());
        return category;
    }

    public CategoryDto toDto (Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getUrl()
        );
    }
}
