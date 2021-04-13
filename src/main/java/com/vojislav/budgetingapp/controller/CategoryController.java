package com.vojislav.budgetingapp.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vojislav.budgetingapp.domain.Category;
import com.vojislav.budgetingapp.domain.Group;
import com.vojislav.budgetingapp.domain.Transaction;
import com.vojislav.budgetingapp.services.CategoryService;
import com.vojislav.budgetingapp.services.GroupService;

@Controller
@RequestMapping("/budgets/{budgetId}/groups/{groupId}/categories")
public class CategoryController {
	@Autowired
	private GroupService groupService;
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public String postCategory(@PathVariable Long groupId) {
		Category category = new Category(); 
		Group group = groupService.findOne(groupId);
		category.setGroup(group);
		group.getCategories().add(category);
		category.setName("test category");
		
		category=categoryService.saveCategory(category);
		return "redirect:/budgets/"+group.getBudget().getId()+"/groups/"+group.getId()+"/categories/"+category.getId();
		
	}
	@GetMapping("{categoryId}")
	public String getCategory(@PathVariable Long categoryId,Model model) {
	  Category category=categoryService.findOne(categoryId);
	  
	   LocalDate startDate = category.getGroup().getBudget().getStartDate();
	   LocalDate endDate   =	category.getGroup().getBudget().getEndDate();
	   Set<Transaction> filteredTxns = category.getTransactions().stream()
               .filter(t -> (t.getDate().equals(startDate) 
                             || t.getDate().isAfter(startDate)) && 
                             (t.getDate().isBefore(endDate) || 
                                 t.getDate().equals(endDate)))
               .collect(Collectors.toSet());
	  model.addAttribute("filteredTxns", filteredTxns);
	  model.addAttribute("category",category);
	  model.addAttribute("group",category.getGroup());
	  return "category";
	}
	
	@PostMapping("{categoryId}")
	public String saveCategory(@ModelAttribute Category category,@PathVariable Long categoryId) {
		Category categoryFromDb=categoryService.findOne(categoryId);
		categoryFromDb.setName(category.getName());
		categoryFromDb.setBudget(category.getBudget());
		categoryFromDb=categoryService.saveCategory(categoryFromDb);
		
		Long budgetId = categoryFromDb.getGroup().getBudget().getId();
		return "redirect:/budgets/"+budgetId;
	}
}
