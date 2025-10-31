package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Category;
import com.food.BackEndRepo.entity.Users;
import com.food.BackEndRepo.entity.dto.category.CategoryCreate;
import com.food.BackEndRepo.entity.dto.category.CategoryDto;
import com.food.BackEndRepo.entity.dto.category.CategoryEdit;
import com.food.BackEndRepo.entity.mapper.CategoryMapper;
import com.food.BackEndRepo.repository.CategoryRepository;
import com.food.BackEndRepo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CategoryCreate categoryCreate) {
        Category category = categoryMapper.toEntity(categoryCreate);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto edit(CategoryEdit categoryEdit, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("Category with id not found " + id));
        category.setName(categoryEdit.getName());
        category.setDescription(categoryEdit.getDescription());
        category.setUrl(categoryEdit.getUrl());
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDto findById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("Category with id not found " + id)));
    }

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void deletedBoolean(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("The category with the id was not found " + id));
        category.setDeleted(!category.isDeleted());
        categoryRepository.save(category);
    }
}
