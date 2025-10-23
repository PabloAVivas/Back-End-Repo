package com.food.BackEndRepo.repository;

import com.food.BackEndRepo.entity.Usuario;
import com.food.BackEndRepo.entity.dto.usuario.UsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
