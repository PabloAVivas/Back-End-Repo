package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Product;
import com.food.BackEndRepo.entity.dto.product.ProductCreate;
import com.food.BackEndRepo.entity.dto.product.ProductDto;
import com.food.BackEndRepo.entity.dto.product.ProductEdit;
import com.food.BackEndRepo.entity.mapper.ProductMapper;
import com.food.BackEndRepo.repository.CategoryRepository;
import com.food.BackEndRepo.repository.ProductRepository;
import com.food.BackEndRepo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public ProductDto save(ProductCreate productCreate) {
        Product product = productMapper.toEntity(productCreate);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto edit(ProductEdit productEdit, Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id));
        product.setName(productEdit.getName());
        product.setDescription(productEdit.getDescription());
        product.setPrice(productEdit.getPrice());
        product.setStock(productEdit.getStock());
        product.setCategory(categoryRepository.findById(productEdit.getCategoryId()).get());
        product.setUrlImg(productEdit.getUrlImg());
        product.setAvailableProduct(productEdit.isAvailableProduct());
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id)));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }
    @Override
    public List<ProductDto> findAllByAvailableProductTrue() {
        return productRepository.findAllByAvailableProductTrue().stream().map(productMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deletedBoolean(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id));
        product.setDeleted(!product.isDeleted());
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> findByCategoryName(String name) {
            return productRepository.findByCategoryName(name).stream().map(productMapper::toDto).toList();
    }
}
