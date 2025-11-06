package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.order.OrderCreate;
import com.food.BackEndRepo.entity.dto.order.OrderEdit;
import com.food.BackEndRepo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    //Ruta para crear un pedido detalle
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody OrderCreate orderCreate){
        try{
            return ResponseEntity.ok(orderService.save(orderCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un pedido detalle por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(orderService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para listar todos los pedido detalles
    @GetMapping()
    public ResponseEntity<?> findAllByDeletedFalse(){
        try{
            return ResponseEntity.ok(orderService.findAllByDeletedFalse());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //Ruta para editar un pedido detalle por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody OrderEdit orderEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(orderService.edit(orderEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/detailAdd/{id}/{idDetail}")
    public ResponseEntity<?> edit(@PathVariable Long id, @PathVariable Long idDetail){
        try {
            return ResponseEntity.ok(orderService.detailAdd(id, idDetail));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para "eliminar" a un pedido detalle pero sin borrarlo de la base de datos
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            orderService.delete(id);
            return ResponseEntity.ok("Delete order");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
