package com.vojislav.budgetingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vojislav.budgetingapp.domain.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
	
	
}
