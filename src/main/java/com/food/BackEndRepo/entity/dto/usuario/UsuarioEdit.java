package com.food.BackEndRepo.entity.dto.usuario;

import com.food.BackEndRepo.entity.dto.enums.Rol;

public record UsuarioEdit(
        int celular,
        String contrasena
){
}
