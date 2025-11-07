package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.product.ProductCreate;
import com.food.BackEndRepo.entity.dto.product.ProductDto;
import com.food.BackEndRepo.entity.dto.product.ProductEdit;

import java.util.List;

public interface ProductService {
    public ProductDto save (ProductCreate productCreate);
    public ProductDto edit (ProductEdit productEdit, Long id);
    public ProductDto findById (Long id);
    public List<ProductDto> findAllByDeletedFalse();
    public List<ProductDto> findAllByAvailableProductTrueAndDeletedFalse();
    public void delete (Long id);
    public List<ProductDto> findByCategoryName(String name);
    public boolean checkStock (Long id, int amount);
    public void subtractStock (Long id, int amount);
}
