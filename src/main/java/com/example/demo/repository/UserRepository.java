package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.UserDetail;

@Repository
public interface UserRepository extends JpaRepository<UserDetail, Integer>{

		
		@Query("from UserDetail where username=?1")
		UserDetail findByUsername(String username) ;
}
