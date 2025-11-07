package com.food.BackEndRepo.entity.dto.user;

import com.food.BackEndRepo.entity.Orders;
import com.food.BackEndRepo.entity.dto.enums.Role;
import com.food.BackEndRepo.entity.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    List<OrderDto> orders;
}
