package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.Configuration;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {

}
