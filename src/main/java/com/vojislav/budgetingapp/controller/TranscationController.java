package com.vojislav.budgetingapp.controller;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.Category;
import com.vojislav.budgetingapp.domain.Transaction;
import com.vojislav.budgetingapp.dto.CategoryDto;
import com.vojislav.budgetingapp.services.BudgetService;
import com.vojislav.budgetingapp.services.CategoryService;
import com.vojislav.budgetingapp.services.TranscationService;

@Controller
@RequestMapping(value= {"/budgets/{budgetId}/groups/{groupId}/categories/{categoryId}/transactions",
"/budgets/{budgetId}/transactions"})
public class TranscationController {
	
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TranscationService transcationService;
	@PostMapping("")
	public String addTranscationToBudget(@PathVariable Long budgetId, 
            @PathVariable(required=false) Long groupId,
            @PathVariable(required=false) Long categoryId)
	{
		String retUrl="";
		Transaction tx=new Transaction();
		Budget budget=budgetService.findOne(budgetId);
		budget.getTranscations().add(tx);
		tx.setBudget(budget);
		tx.setDate(LocalDate.now());
		if(categoryId != null) {
			Category category = categoryService.findOne(categoryId);
			tx.setCategory(category);
			category.getTransactions().add(tx);
			retUrl="/budgets/"+budgetId+"/groups/"+category.getGroup().getId()+"/categories/"+category.getId()+"/transactions/";
		}else {
			retUrl="/budgets/"+budgetId+"/transactions/";
		}
		
		tx=transcationService.save(tx);
		return "redirect:"+retUrl+tx.getId();
	}
	
	@GetMapping("{transactionId}")
	public String getTranscation(@PathVariable Long transactionId,Model model) {
		Transaction transcation=transcationService.findOne(transactionId);
		model.addAttribute("transaction",transcation);
		model.addAttribute("budget",transcation.getBudget());
		List<CategoryDto> categories = transcation.getBudget().getGroups().stream()
																	   .map(group -> group.getCategories())
																	   .flatMap(Collection::stream)
																	   .map(category -> new CategoryDto(category.getId().toString(),category.getName()))
																	   .collect(Collectors.toList());
		model.addAttribute("categories",categories);
		
		return "transcation";
	}
	
	@PostMapping("{transactionId}")
	public String postTranscation(@ModelAttribute Transaction transaction,@PathVariable Long transactionId) {
		
		transaction = transcationService.save(transaction);
		
		return "redirect:/budgets/"+transaction.getBudget().getId();
		
	}
}
