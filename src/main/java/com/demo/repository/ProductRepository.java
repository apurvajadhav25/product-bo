package com.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.model.Filter1;
import com.demo.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductRepositoryCustom {
	
	   @Query("SELECT p FROM Product p  ORDER BY p.price ")
	   Collection<Product> sortProducts();
	   
	   @Query("SELECT p FROM Product p  ORDER BY p.price Desc")
	   Collection<Product> sortProductsByDesc();
	   
	   @Query("SELECT p FROM Product p WHERE p.isEnable='true'")
	   Collection<Product> findProducts();

}
