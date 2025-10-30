package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.Product;
import com.food.BackEndRepo.entity.dto.product.ProductCreate;
import com.food.BackEndRepo.entity.dto.product.ProductEdit;
import com.food.BackEndRepo.repository.ProductRepository;
import com.food.BackEndRepo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody ProductCreate productCreate){
        try{
            return ResponseEntity.ok(productService.save(productCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a todos los usuarios
    @GetMapping("/admin")
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(productService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a todos los usuarios
    @GetMapping("/user")
    public ResponseEntity<?> findAllByAvailableProductTrue(){
        try{
            return ResponseEntity.ok(productService.findAllByAvailableProductTrue());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar a un usuario por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ProductEdit productEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(productService.edit(productEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para eliminar a un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            productService.delete(id);
            return ResponseEntity.ok("Deleted user");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Product>> getProduct(
            @RequestParam(required = false) String categoryName //help:http://localhost:8080/product/categories?categoryName=Burger
    ) {
        if (categoryName != null && !categoryName.isEmpty()) {
            List<Product> productFiltered = productRepository.findByCategoryName(categoryName);
            return ResponseEntity.ok(productFiltered);
        } else {
            List<Product> allProduct = productRepository.findAll();
            return ResponseEntity.ok(allProduct);
        }
    }
}
