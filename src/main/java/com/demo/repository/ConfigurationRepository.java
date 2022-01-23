package com.demo.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.model.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
	
	@Query("SELECT c FROM Configuration c WHERE c.key= :key and c.language= :language")
	 Collection<Configuration> findByKey(@Param("key") String key, @Param("language") String language);

}
