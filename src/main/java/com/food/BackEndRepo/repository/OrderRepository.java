package com.food.BackEndRepo.repository;

import com.food.BackEndRepo.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByDeletedFalse();
    @Query(value = "SELECT * FROM orders o WHERE o.users_id = :userId AND o.deleted = false", nativeQuery = true)
    List<Orders> findOrdersByUserIdAndNotDeleted(@Param("userId") Long userId);
}
