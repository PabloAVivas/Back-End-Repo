package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Users;
import com.food.BackEndRepo.entity.dto.user.UserCreate;
import com.food.BackEndRepo.entity.dto.user.UserDto;
import com.food.BackEndRepo.entity.dto.user.UserEdit;
import com.food.BackEndRepo.entity.mapper.UserMapper;
import com.food.BackEndRepo.repository.UserRepository;
import com.food.BackEndRepo.service.Sha256Util;
import com.food.BackEndRepo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    //Recibe parametros para crear un usuario, verifica si el email ingresado ya existe
    //si existe detiene la creacion y devuelve un RuntimeException
    //si no, crea el usuario y lo guarda en la base de datos
    @Override
    public UserDto save(UserCreate userCreate) {
        Users user = userMapper.toEntity(userCreate);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is already in use.");
        }
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    //Recibe los parametros a editar y un id
    //modifica los datos y los guarda en la base de datos
    @Override
    public UserDto edit(UserEdit userEdit, Long id) {
        Users user = userRepository.findById(id).orElseThrow(()-> new NullPointerException("The user with the id was not found " + id));
        user.setCellPhone(userEdit.getCellPhone());
        user.setPassword(Sha256Util.hash(userEdit.getPassword()));
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    //Recibe un id para buscar un usuario con ese id
    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow(()-> new NullPointerException("The user with the id was not found " + id)));
    }

    //Busca a todos los usuarios existentes
    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    //Recibe un id del usuario a eliminar
    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void deletedBoolean(Long id) {
        Users user = userRepository.findById(id).orElseThrow(()-> new NullPointerException("The user with the id was not found " + id));
        user.setDeleted(!user.isDeleted());
        userRepository.save(user);
    }

    //Recibe un email y lo busca si esta en la base de datos
    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(()-> new NullPointerException("Email not found " + email)));

    }

    //Recibe un email y una contraseña, hashea la contraseña y la compara los datos con el usuario
    //para ver si coinciden
    @Override
    public Optional<UserDto> login(String email, String password){

        String hashPassword = Sha256Util.hash(password);

        return userRepository.findByEmail(email)
                .filter(user -> hashPassword.equals(user.getPassword()))
                .map(userMapper::toDto);
    }
}
