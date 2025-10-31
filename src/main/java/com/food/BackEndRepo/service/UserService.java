package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.user.UserCreate;
import com.food.BackEndRepo.entity.dto.user.UserDto;
import com.food.BackEndRepo.entity.dto.user.UserEdit;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserDto save (UserCreate userCreate);
    public UserDto edit (UserEdit userEdit, Long id);
    public UserDto findById (Long id);
    public List<UserDto> findAll();
    public void delete (Long id);
    public void deletedBoolean (Long id);
    public UserDto findByEmail(String email);
    Optional<UserDto> login (String email, String password);
}
