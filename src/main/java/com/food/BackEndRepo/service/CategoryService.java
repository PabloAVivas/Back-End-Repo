package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.category.CategoryDto;
import com.food.BackEndRepo.entity.dto.category.CategoryCreate;
import com.food.BackEndRepo.entity.dto.category.CategoryEdit;

import java.util.List;

public interface CategoryService {
    public CategoryDto save (CategoryCreate categoryCreate);
    public CategoryDto edit (CategoryEdit categoryEdit, Long id);
    public CategoryDto findById (Long id);
    public List<CategoryDto> findAll();
    public void delete (Long id);
}
