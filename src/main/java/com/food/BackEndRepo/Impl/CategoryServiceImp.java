package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Category;
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

    //Recibe parametros para crear una categoria y lo guarda en la base de datos
    @Override
    public CategoryDto save(CategoryCreate categoryCreate) {
        Category category = categoryMapper.toEntity(categoryCreate);
        category = categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    //Recibe parametros y el id de una categoria para modificar los datos de la categoria existente
    @Override
    public CategoryDto edit(CategoryEdit categoryEdit, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("Category with id not found " + id));
        category.setName(categoryEdit.getName());
        category.setDescription(categoryEdit.getDescription());
        category.setUrl(categoryEdit.getUrl());
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    //Recibe un id para buscar una categoria con ese id
    @Override
    public CategoryDto findById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("Category with id not found " + id)));
    }

    //Busca todas las categorias existentes
    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).toList();
    }

    //Recibe un id de una categoria para eliminarla de la base de datos
    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    //Recibe un id de una categoria para "eliminarla" pero no de la base de datos
    @Override
    public void deletedBoolean(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new NullPointerException("The category with the id was not found " + id));
        category.setDeleted(!category.isDeleted());
        categoryRepository.save(category);
    }
}
