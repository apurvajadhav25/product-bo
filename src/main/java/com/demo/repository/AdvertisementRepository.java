package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.demo.model.Advertisement;

public interface AdvertisementRepository  extends JpaRepository<Advertisement, Integer> {
	
	@Query("SELECT a FROM Advertisement a WHERE a.name= :name ")
	Advertisement findByName(@Param("name") String name);

}
