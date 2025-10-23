package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.Rol;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String email;
    private int celular;
    private String contrasena;

    @Enumerated(EnumType.STRING)
    private Rol rol;
}
