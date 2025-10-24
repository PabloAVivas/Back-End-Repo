package com.food.BackEndRepo;

import com.food.BackEndRepo.entity.Usuario;
import com.food.BackEndRepo.entity.dto.enums.Rol;
import com.food.BackEndRepo.repository.UsuarioRepository;
import com.food.BackEndRepo.service.Sha256Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndRepoApplication implements CommandLineRunner {

	private final UsuarioRepository usuarioRepository;

	public BackEndRepoApplication(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackEndRepoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		final String ADMIN_NOMBRE = "Admin";
		final String ADMIN_APELLIDO = "Istrador";
		final String ADMIN_EMAIL = "admin@email.com";
		final int ADMIN_CELULAR = 1234567890;
		final String ADMIN_CONTRASENA = "Admin123";
		final Rol ADMIN_ROL = Rol.ADMIN;

		if (!usuarioRepository.existsByEmail(ADMIN_EMAIL)) {

			String hashedContrasena = Sha256Util.hash(ADMIN_CONTRASENA);
			Usuario admin = new Usuario();
			admin.setNombre(ADMIN_NOMBRE);
			admin.setApellido(ADMIN_APELLIDO);
			admin.setEmail(ADMIN_EMAIL);
			admin.setCelular(ADMIN_CELULAR);
			admin.setContrasena(hashedContrasena);
			admin.setRol(ADMIN_ROL);

			usuarioRepository.save(admin);
			System.out.println("Usuario ADMIN de prueba creado: " + ADMIN_EMAIL);
		} else {
			System.out.println("El usuario ADMIN de prueba ya existe.");
		}

		final String USUARIO_NOMBRE = "Usu";
		final String USUARIO_APELLIDO = "Ario";
		final String USUARIO_EMAIL = "usuario@email.com";
		final int USUARIO_CELULAR = 1234567890;
		final String USUARIO_CONTRASENA = "Usuario123";
		final Rol USUARIO_ROL = Rol.USUARIO;

		if (!usuarioRepository.existsByEmail(USUARIO_EMAIL)) {

			String hashedContrasena = Sha256Util.hash(USUARIO_CONTRASENA);
			Usuario usuario = new Usuario();
			usuario.setNombre(USUARIO_NOMBRE);
			usuario.setApellido(USUARIO_APELLIDO);
			usuario.setEmail(USUARIO_EMAIL);
			usuario.setCelular(USUARIO_CELULAR);
			usuario.setContrasena(hashedContrasena);
			usuario.setRol(USUARIO_ROL);

			usuarioRepository.save(usuario);
			System.out.println("Usuario USUARIO de prueba creado: " + USUARIO_EMAIL);
		} else {
			System.out.println("El usuario USUARIO de prueba ya existe.");
		}
	}
}