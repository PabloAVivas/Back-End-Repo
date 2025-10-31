package com.food.BackEndRepo.entity.dto.user;

import com.food.BackEndRepo.entity.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String name;
    String lastName;
    String email;
    int cellPhone;
    Role role;
    boolean deleted;
}
