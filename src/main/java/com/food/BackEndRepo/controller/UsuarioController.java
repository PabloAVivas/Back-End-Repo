package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.usuario.UsuarioCreate;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioEdit;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioLogin;
import com.food.BackEndRepo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    //Ruta para crear usuarios
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UsuarioCreate usuarioCreate){
        try{
            return ResponseEntity.ok(usuarioService.save(usuarioCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un usuario por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(usuarioService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a todos los usuarios
    @GetMapping()
    public ResponseEntity<?> findAll(){
        try{
            return ResponseEntity.ok(usuarioService.findAll());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para editar a un usuario por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody UsuarioEdit usuarioEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(usuarioService.edit(usuarioEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para eliminar a un usuario
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            usuarioService.delete(id);
            return ResponseEntity.ok("Usuario eliminado");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para verificar si un email existe
    @GetMapping("/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        try {
            usuarioService.findByEmail(email);
            return ResponseEntity.ok("El email existe");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para iniciar sesion
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioLogin userL){

        return usuarioService.login(userL.email(), userL.contrasena())
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales invalidas"));
    }
}
