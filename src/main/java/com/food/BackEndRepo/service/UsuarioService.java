package com.food.BackEndRepo.service;

import com.food.BackEndRepo.entity.dto.usuario.UsuarioCreate;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioDto;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioEdit;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioLogin;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public UsuarioDto save (UsuarioCreate d);
    public UsuarioDto edit (UsuarioEdit d, Long id);
    public UsuarioDto findById (Long id);
    public List<UsuarioDto> findAll();
    public void delete (Long id);
    public UsuarioDto findByEmail(String email);
    Optional<UsuarioDto> login (String email, String password);
}
