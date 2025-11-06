package com.food.BackEndRepo.controller;

import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailCreate;
import com.food.BackEndRepo.entity.dto.orderDetail.OrderDetailEdit;
import com.food.BackEndRepo.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin("*")
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    OrderDetailService orderDetailService;

    //Ruta para crear un pedido detalle
    @PostMapping("/create")
    public ResponseEntity<?> save(@RequestBody OrderDetailCreate orderDetailCreate){
        try{
            return ResponseEntity.ok(orderDetailService.save(orderDetailCreate));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para buscar a un pedido detalle por id
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        try{
            return ResponseEntity.ok(orderDetailService.findById(id));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para listar todos los pedido detalles
    @GetMapping()
    public ResponseEntity<?> findAllByDeletedFalse(){
        try{
            return ResponseEntity.ok(orderDetailService.findAllByDeletedFalse());
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //Ruta para editar un pedido detalle por id
    @PutMapping("/edit/{id}")
    public ResponseEntity<?> edit(@RequestBody OrderDetailEdit orderDetailEdit, @PathVariable Long id){
        try {
            return ResponseEntity.ok(orderDetailService.edit(orderDetailEdit, id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    //Ruta para "eliminar" a un pedido detalle pero sin borrarlo de la base de datos
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            orderDetailService.delete(id);
            return ResponseEntity.ok("Delete orderDetail");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
