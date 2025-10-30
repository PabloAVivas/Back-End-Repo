package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Product;
import com.food.BackEndRepo.entity.dto.product.ProductCreate;
import com.food.BackEndRepo.entity.dto.product.ProductDto;
import com.food.BackEndRepo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;
    public Product toEntity (ProductCreate productC){
        Product product = new Product();
        product.setName(productC.name());
        product.setDescription(productC.description());
        product.setPrice(productC.price());
        product.setStock(productC.stock());
        product.setCategory(categoryRepository.findById(productC.categoryId()).get());
        product.setUrlImg(productC.urlImg());
        product.setAvailableProduct(true);
        return product;
    }

    public ProductDto toDto (Product product){
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                categoryMapper.toDto(product.getCategory()),
                product.getUrlImg(),
                product.isAvailableProduct()
        );
    }
}
