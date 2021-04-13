package com.vojislav.budgetingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vojislav.budgetingapp.dao.CategoryDao;
import com.vojislav.budgetingapp.domain.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryDao categoryDao;
	
	public Category saveCategory(Category category) {
		
		return categoryDao.save(category);
		
	}

	public Category findOne(Long categoryId) {
		return categoryDao.getOne(categoryId);
	}
}
