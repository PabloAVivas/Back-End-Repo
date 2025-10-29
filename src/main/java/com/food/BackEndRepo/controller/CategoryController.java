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

    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody CategoryCreate categoryCreate){
        try{
            return ResponseEntity.ok(categoryService.save(categoryCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(categoryService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a todos los usuarios
    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(categoryService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar a un usuario por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody CategoryEdit categoryEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(categoryService.edit(categoryEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para eliminar a un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            categoryService.delete(id);
            return ResponseEntity.ok("Deleted user");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
