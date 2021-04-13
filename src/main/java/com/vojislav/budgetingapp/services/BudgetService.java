package com.vojislav.budgetingapp.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.vojislav.budgetingapp.dao.BudgetDao;
import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.Group;
import com.vojislav.budgetingapp.domain.User;

@Service
public class BudgetService {

	@Autowired
	private BudgetDao budgetDao;
	
	public TreeSet<Budget> getBudgets(@AuthenticationPrincipal User user){
		
		Set<User> users=new HashSet<>();
		users.add(user);
		
		
		return budgetDao.findByUsersIn(users);
	}
	
	public Budget saveBudget(User user,Budget budget) {
		 Set<User> users = new HashSet<>();
		    Set<Budget> budgets = new HashSet<>();
		    
		    users.add(user);
		    
		    budgets.add(budget);
		    
		    long count = getBUdgetCount(users);
		    
		    budget.setName("New Budget #" + ++count);
		    budget.setUsers(users);
		    
		    Group group = new Group();
		    
		    group.setBudget(budget);
		    group.setName("Savings");
		    
		    budget.getGroups().add(group);
		    
		    user.setBudgets(budgets);
		    return budgetDao.save(budget);
	}
	public Budget updateBudget(User user,Budget budget) {
		    return budgetDao.save(budget);
	}

	private Long getBUdgetCount(Set<User> users) {
		return budgetDao.countByUsersIn(users);
	}

	public Budget findOne(Long budgetId) {
		return budgetDao.getOne(budgetId);
	}
	
	public LocalDate convertStringToDate(String date) throws ParseException {
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
		
	}

}
