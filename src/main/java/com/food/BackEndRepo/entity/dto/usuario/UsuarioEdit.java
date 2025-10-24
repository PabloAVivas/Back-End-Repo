package com.food.BackEndRepo.entity.dto.usuario;

import com.food.BackEndRepo.entity.dto.enums.Rol;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEdit{
    int celular;
    String contrasena;
}
