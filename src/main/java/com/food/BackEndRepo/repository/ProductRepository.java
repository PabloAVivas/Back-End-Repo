package com.food.BackEndRepo.repository;

import com.food.BackEndRepo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByAvailableProductTrue();
    List<Product> findByCategoryName(String name);
}
