package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
