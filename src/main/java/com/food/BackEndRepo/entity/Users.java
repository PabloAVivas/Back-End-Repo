package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String email;
    private int cellPhone;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
