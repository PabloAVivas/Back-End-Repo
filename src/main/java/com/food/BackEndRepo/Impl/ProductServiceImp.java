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

    //Recibe los parametros para crear un producto y lo guarda en la base de datos
    @Override
    public ProductDto save(ProductCreate productCreate) {
        Product product = productMapper.toEntity(productCreate);
        product = productRepository.save(product);
        return productMapper.toDto(product);
    }

    //Recibe los parametos y un id de un producto para modificar los datos de un producto ya existente
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

    //Recibe un id para buscar un producto con ese id
    @Override
    public ProductDto findById(Long id) {
        return productMapper.toDto(productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id)));
    }

    //Busca todos los productos existentes
    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    //Busca todos los productos existentes que esten disponibles
    @Override
    public List<ProductDto> findAllByAvailableProductTrue() {
        return productRepository.findAllByAvailableProductTrue().stream().map(productMapper::toDto).toList();
    }

    //Recibe un id de un producto para eliminarlo de la base de datos
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    //Recibe un id de un producto para "eliminarlo" pero no de la base de datos
    @Override
    public void deletedBoolean(Long id) {
        Product product = productRepository.findById(id).orElseThrow(()-> new NullPointerException("The product with the id was not found " + id));
        product.setDeleted(!product.isDeleted());
        productRepository.save(product);
    }

    //Recibe el nombre de una categoria y busca todos los productos que pertenezcan a esa categoria
    @Override
    public List<ProductDto> findByCategoryName(String name) {
            return productRepository.findByCategoryName(name).stream().map(productMapper::toDto).toList();
    }
}
