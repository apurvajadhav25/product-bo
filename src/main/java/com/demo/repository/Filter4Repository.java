package com.demo.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.Filter4;

@Repository
public interface Filter4Repository extends JpaRepository<Filter4, Integer> {
	
	@Query("SELECT f FROM Filter4 f WHERE f.isEnable='true'")
	  Collection<Filter4> findFilters();

}
