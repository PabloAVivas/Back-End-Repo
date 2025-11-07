package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Users;
import com.food.BackEndRepo.entity.dto.user.UserCreate;
import com.food.BackEndRepo.entity.dto.user.UserDto;
import com.food.BackEndRepo.service.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.food.BackEndRepo.entity.dto.enums.Role.USER;

@Component
public class UserMapper {

    @Autowired
    OrderMapper orderMapper;

    public Users toEntity(UserCreate userC){
        Users user = new Users();
        user.setName(userC.name());
        user.setLastName(userC.lastName());
        user.setEmail(userC.email());
        user.setCellPhone(userC.cellPhone());
        user.setPassword(Sha256Util.hash(userC.password()));
        user.setRole(USER);
        user.setOrders(new ArrayList<>());
        return user;
    }

    public UserDto toDto(Users user){
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getCellPhone(),
                user.getRole(),
                user.getOrders().stream().map(orderMapper::toDto).toList()
        );
    }
}
