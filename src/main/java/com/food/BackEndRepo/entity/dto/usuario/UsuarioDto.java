package com.food.BackEndRepo.entity.dto.usuario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto {
    Long id;
    String nombre;
    String apellido;
    String email;
    int celular;
}
