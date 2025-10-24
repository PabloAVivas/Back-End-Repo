package com.food.BackEndRepo.entity.mapper;

import com.food.BackEndRepo.entity.Usuario;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioCreate;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioDto;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioEdit;
import com.food.BackEndRepo.service.Sha256Util;
import org.springframework.stereotype.Component;
import static com.food.BackEndRepo.entity.dto.enums.Rol.USUARIO;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioCreate usuarioC){
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioC.nombre());
        usuario.setApellido(usuarioC.apellido());
        usuario.setEmail(usuarioC.email());
        usuario.setCelular(usuarioC.celular());
        usuario.setContrasena(Sha256Util.hash(usuarioC.contrasena()));
        usuario.setRol(USUARIO);
        return usuario;
    }

    public UsuarioDto toDto(Usuario usuario){
        return new UsuarioDto(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getCelular(),
                usuario.getRol()
        );
    }
}
