package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.category.CategoryCreate;
import com.food.BackEndRepo.entity.dto.category.CategoryEdit;
import com.food.BackEndRepo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    //Ruta para crear una categoria
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody CategoryCreate categoryCreate){
        try{
            return ResponseEntity.ok(categoryService.save(categoryCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar una categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoryService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar todas las categorias
    @GetMapping()
    public ResponseEntity<?> findAllByDeletedFalse(){
        try{
            return ResponseEntity.ok(categoryService.findAllByDeletedFalse());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar una categoria por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody CategoryEdit categoryEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(categoryService.edit(categoryEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para "eliminar" una categoria pero sin borrarla de la base de datos por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            categoryService.delete(id);
            return ResponseEntity.ok("Deleted category");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
