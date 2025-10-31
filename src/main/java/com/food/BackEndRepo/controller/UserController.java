package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.user.UserCreate;
import com.food.BackEndRepo.entity.dto.user.UserEdit;
import com.food.BackEndRepo.entity.dto.user.UserLogin;
import com.food.BackEndRepo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //Ruta para crear usuarios
    @PostMapping("/register")
    public ResponseEntity<?> save(@RequestBody UserCreate userCreate){
        try{
            return ResponseEntity.ok(userService.save(userCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(userService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a todos los usuarios
    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(userService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar a un usuario por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody UserEdit userEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(userService.edit(userEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para eliminar a un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            userService.delete(id);
            return ResponseEntity.ok("Deleted user");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/deletedBoolean/{id}")
    public ResponseEntity<?> deleted(@PathVariable Long id){
        try{
            userService.deletedBoolean(id);
            return ResponseEntity.ok("Deleted Boolean user");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para verificar si un email existe
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            userService.findByEmail(email);
            return ResponseEntity.ok("The email exists");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLogin userL){

        return userService.login(userL.email(), userL.password())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"));
    }
}
