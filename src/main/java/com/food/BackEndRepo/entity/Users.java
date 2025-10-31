package com.food.BackEndRepo.entity;

import com.food.BackEndRepo.entity.dto.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Users extends Base {
    private String name;
    private String lastName;
    private String email;
    private int cellPhone;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
