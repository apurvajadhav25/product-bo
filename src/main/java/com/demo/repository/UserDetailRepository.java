package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.UserDetail;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Integer>{

}
