package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.product.ProductCreate;
import com.food.BackEndRepo.entity.dto.product.ProductEdit;
import com.food.BackEndRepo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    //Ruta para crear un producto
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody ProductCreate productCreate){
        try{
            return ResponseEntity.ok(productService.save(productCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un producto por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(productService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para listar todos los productos
    @GetMapping("/admin")
    public ResponseEntity<?> findAllByDeletedFalse(){
        try{
            return ResponseEntity.ok(productService.findAllByDeletedFalse());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para listar todos los productos que esten disponibles
    @GetMapping("/user")
    public ResponseEntity<?> findAllByAvailableProductTrueAndDeletedFalse(){
        try{
            return ResponseEntity.ok(productService.findAllByAvailableProductTrueAndDeletedFalse());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar un producto por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody ProductEdit productEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(productService.edit(productEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para "eliminar" a un producto pero sin borrarlo de la base de datos
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            productService.delete(id);
            return ResponseEntity.ok("Delete product");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para filtrar los productos por nombre de categoria
    @GetMapping("/categories")
    public ResponseEntity<?> getProduct(@RequestParam(required = false) String categoryName){ //help:http://localhost:8080/product/categories?categoryName=Burger
        try {
            return ResponseEntity.ok(productService.findByCategoryName(categoryName));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
