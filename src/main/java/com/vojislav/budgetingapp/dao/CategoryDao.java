package com.vojislav.budgetingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vojislav.budgetingapp.domain.Category;

public interface CategoryDao extends JpaRepository<Category,Long> {

}
