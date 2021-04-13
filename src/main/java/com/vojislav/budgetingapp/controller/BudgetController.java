package com.vojislav.budgetingapp.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.User;
import com.vojislav.budgetingapp.services.BudgetService;

@Controller
@RequestMapping("/budgets")
public class BudgetController {
	
	@Autowired
	private BudgetService budgetService;
	
	@GetMapping
	public String getBudgets(@AuthenticationPrincipal User user,Model model) {
		populateUserBudgetModel(user, model);
		return "budgets";
	}
	
	@GetMapping("{budgetId}")
	public String getBudget(@PathVariable Long budgetId,Model model) {
		Budget budget=budgetService.findOne(budgetId);
		boolean hasCategories=budget.getGroups().stream().filter(group -> group.getCategories().size() > 0).count() > 0;
		model.addAttribute("budget",budget);
		model.addAttribute("hasCategories",hasCategories);
		return "budget";
	}
	
	@PostMapping
	public @ResponseBody Budget postBudget(@AuthenticationPrincipal User user,Model model) {
		
		Budget budget=new Budget();

		LocalDate firstOfMoth = LocalDate.now().withDayOfMonth(1);
		LocalDate endOfMoth=LocalDate.now().withDayOfMonth(firstOfMoth.lengthOfMonth());
	    
		budget.setStartDate(firstOfMoth);
		budget.setEndDate(endOfMoth);
		budget = budgetService.saveBudget(user,budget);
		
		populateUserBudgetModel(user, model);
		
		return budget;
	}
	
	  @PutMapping("{budgetId}")
	  public @ResponseBody void putBudget(@AuthenticationPrincipal User user, @RequestParam String startDate,
	      @RequestParam String endDate, @PathVariable Long budgetId) throws ParseException 
	  {
	    Budget budget = budgetService.findOne(budgetId);
	    
	    budget.setStartDate(budgetService.convertStringToDate(startDate));
	    budget.setEndDate(budgetService.convertStringToDate(endDate));
	    budgetService.updateBudget(user, budget);
	  }
	  
	
	private void populateUserBudgetModel(User user, Model model) {
		TreeSet<Budget> budgets = budgetService.getBudgets(user);
		model.addAttribute("budgets",budgets);
	}
	
}
