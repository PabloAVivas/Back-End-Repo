package com.food.BackEndRepo;

import com.food.BackEndRepo.entity.Category;
import com.food.BackEndRepo.entity.Product;
import com.food.BackEndRepo.entity.Users;
import com.food.BackEndRepo.entity.dto.enums.Role;
import com.food.BackEndRepo.repository.CategoryRepository;
import com.food.BackEndRepo.repository.ProductRepository;
import com.food.BackEndRepo.repository.UserRepository;
import com.food.BackEndRepo.service.Sha256Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndRepoApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public BackEndRepoApplication(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(BackEndRepoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (!userRepository.existsByEmail("admin@email.com")) {
			Users admin = new Users();
			admin.setName("Admin");
			admin.setLastName("Istrador");
			admin.setEmail("admin@email.com");
			admin.setCellPhone(1234567890);
			admin.setPassword(Sha256Util.hash("Admin123"));
			admin.setRole(Role.ADMIN);

			userRepository.save(admin);
			System.out.println("Test ADMIN user created: admin@email.com");
		} else {
			System.out.println("The test ADMIN user already exists.");
		}

		if (!userRepository.existsByEmail("usuario@email.com")) {
			Users usuario = new Users();
			usuario.setName("Usu");
			usuario.setLastName("Ario");
			usuario.setEmail("usuario@email.com");
			usuario.setCellPhone(1234567890);
			usuario.setPassword(Sha256Util.hash("Usuario123"));
			usuario.setRole(Role.USER);

			userRepository.save(usuario);
			System.out.println("User Test USER created: usuario@email.com");
		} else {
			System.out.println("The user test USER already exists.");
		}

		if(!categoryRepository.existsById(1L)){
			Category category = new Category();
			category.setName("Hamburguesas");
			category.setDescription("Diferentes hamburguesas de tipo smash");
			category.setUrl("https://www.foodandwine.com/thmb/XE8ubzwObCIgMw7qJ9CsqUZocNM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/MSG-Smash-Burger-FT-RECIPE0124-d9682401f3554ef683e24311abdf342b.jpg");
			categoryRepository.save(category);
			System.out.println("The test CATEGORY was created");
		} else {
			System.out.println("The test CATEGORY alredy exist");
		}
		if (!productRepository.existsById(1L)){
			Product product = new Product();
			product.setName("Hamburguesa triple smash");
			product.setDescription("Hamburguesa triple tasty turbo bacon (con cebolla)");
			product.setPrice(25000);
			product.setStock(55);
			product.setCategory(categoryRepository.findById(1L).get());
			product.setUrlImg("https://www.foodandwine.com/thmb/XE8ubzwObCIgMw7qJ9CsqUZocNM=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/MSG-Smash-Burger-FT-RECIPE0124-d9682401f3554ef683e24311abdf342b.jpg");
			product.setAvailableProduct(true);
			productRepository.save(product);
			System.out.println("The test PRODUCT was created");
		}else {
			System.out.println("The test PRODUCT alredy exist");
		}
	}
}