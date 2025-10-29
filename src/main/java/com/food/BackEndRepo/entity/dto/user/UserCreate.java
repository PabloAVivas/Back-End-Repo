package com.food.BackEndRepo.entity.dto.user;

import com.food.BackEndRepo.entity.dto.enums.Role;

public record UserCreate(
        String name,
        String lastName,
        String email,
        int cellPhone,
        String password,
        Role role
){
}
