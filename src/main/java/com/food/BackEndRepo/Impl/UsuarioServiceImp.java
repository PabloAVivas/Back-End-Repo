package com.food.BackEndRepo.Impl;

import com.food.BackEndRepo.entity.Usuario;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioCreate;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioDto;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioEdit;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioLogin;
import com.food.BackEndRepo.entity.mapper.UsuarioMapper;
import com.food.BackEndRepo.repository.UsuarioRepository;
import com.food.BackEndRepo.service.Sha256Util;
import com.food.BackEndRepo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

import static com.food.BackEndRepo.entity.dto.enums.Rol.USUARIO;

@Service
public class UsuarioServiceImp implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioMapper usuarioMapper;

    @Override
    public UsuarioDto save(UsuarioCreate usuarioCreate) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreate);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto edit(UsuarioEdit d, Long id) {
        UsuarioDto usuarioDto = findById(id);
        Usuario usuario = new Usuario();
        usuario.setId(usuarioDto.getId());
        usuario.setNombre(usuarioDto.getNombre());
        usuario.setApellido(usuarioDto.getApellido());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setCelular(d.getCelular());
        usuario.setContrasena(Sha256Util.hash(d.getContrasena()));
        usuario.setRol(usuarioDto.getRol());
        usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    @Override
    public UsuarioDto findById(Long id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(()-> new NullPointerException("No se encontro al curso con el id " + id)));
    }

    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioDto findByEmail(String email) {
        return usuarioMapper.toDto(usuarioRepository.findByEmail(email).orElseThrow(()-> new NullPointerException("No se encontro el email " + email)));

    }

    @Override
    public Optional<UsuarioDto> login(String email, String password){

        String hashPassword = Sha256Util.hash(password);

        return usuarioRepository.findByEmail(email)
                .filter(user -> hashPassword.equals(user.getContrasena()))
                .map(usuarioMapper::toDto);
    }
}
