package com.food.BackEndRepo;

import com.food.BackEndRepo.entity.*;
import com.food.BackEndRepo.entity.dto.enums.Role;
import com.food.BackEndRepo.entity.dto.enums.State;
import com.food.BackEndRepo.repository.*;
import com.food.BackEndRepo.service.Sha256Util;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootApplication
public class BackEndRepoApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final OrderDetailRepository orderDetailRepository;
	private final OrderRepository orderRepository;

	public BackEndRepoApplication(UserRepository userRepository, CategoryRepository categoryRepository, ProductRepository productRepository, OrderDetailRepository orderDetailRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.orderDetailRepository = orderDetailRepository;
		this.orderRepository = orderRepository;
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
			admin.setOrders(new ArrayList<>());

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
			usuario.setOrders(new ArrayList<>());

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
		try {
			if (!orderRepository.existsById(1L)){
				Orders orders = new Orders();
				orders.setDate(LocalDate.now());
				orders.setState(State.PENDING);
				orders.setTotal(0);
				orders.setDetails(new ArrayList<>());


				OrderDetail detail = new OrderDetail();
				detail.setAmount(3);
				detail.setSubtotal(75000);
				detail.setProduct(productRepository.findById(1L).get());
				orderDetailRepository.save(detail);

				orders.addOrderDetail(detail);
				orderRepository.save(orders);

				Users users = userRepository.findById(1L).get();
				Orders orders1 = orderRepository.findById(1L).get();
				users.addOrders(orders1);
				userRepository.save(users);
				System.out.println("The ORDER and ORDERDETAILS were created");
			}else {
				System.out.println("The ORDER and ORDERDETAILS already exist.");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}