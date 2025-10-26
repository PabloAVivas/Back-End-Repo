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

    //Recibe parametros para crear un usuario, verifica si el email ingresado ya existe
    //si existe detiene la creacion y devuelve un RuntimeException
    //si no, crea el usuario y lo guarda en la base de datos
    @Override
    public UsuarioDto save(UsuarioCreate usuarioCreate) {
        Usuario usuario = usuarioMapper.toEntity(usuarioCreate);
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }
        usuario = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    //Recibe los parametros a editar y un id
    //modifica los datos y los guarda en la base de datos
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

    //Recibe un id para buscar un usuario con ese id
    @Override
    public UsuarioDto findById(Long id) {
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow(()-> new NullPointerException("No se encontro al curso con el id " + id)));
    }

    //Busca a todos los usuarios existentes
    @Override
    public List<UsuarioDto> findAll() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    //Recibe un id del usuario a eliminar
    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    //Recibe un email y lo busca si esta en la base de datos
    @Override
    public UsuarioDto findByEmail(String email) {
        return usuarioMapper.toDto(usuarioRepository.findByEmail(email).orElseThrow(()-> new NullPointerException("No se encontro el email " + email)));

    }

    //Recibe un email y una contraseña, hashea la contraseña y la compara los datos con el usuario
    //para ver si coinciden
    @Override
    public Optional<UsuarioDto> login(String email, String password){

        String hashPassword = Sha256Util.hash(password);

        return usuarioRepository.findByEmail(email)
                .filter(user -> hashPassword.equals(user.getContrasena()))
                .map(usuarioMapper::toDto);
    }
}
