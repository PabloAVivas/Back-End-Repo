package com.food.BackEndRepo.entity.dto.user;


public record UserCreate(
        String name,
        String lastName,
        String email,
        int cellPhone,
        String password
){
}
