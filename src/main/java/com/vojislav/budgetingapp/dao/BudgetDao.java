package com.vojislav.budgetingapp.dao;


import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.User;

@Repository
public interface BudgetDao extends JpaRepository<Budget, Long> {

	 TreeSet<Budget> findByUsersIn(Set<User> users);
	 
	 long countByUsersIn(Set<User> users);
	
}
