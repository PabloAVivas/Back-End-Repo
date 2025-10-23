package com.food.BackEndRepo.entity.dto.usuario;

import com.food.BackEndRepo.entity.dto.enums.Rol;

public record UsuarioCreate(
        String nombre,
        String apellido,
        String email,
        int celular,
        String contrasena,
        Rol rol
){
}
