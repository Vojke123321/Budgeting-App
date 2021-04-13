package com.vojislav.budgetingapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vojislav.budgetingapp.dao.GroupDao;
import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.Group;

@Service
public class GroupService {
	
	@Autowired
	private BudgetService budgetService;
	@Autowired
	private GroupDao groupDao;
	
	public Group createNewGroup(Long budgetId) {
		Group group=new Group();
		
		Budget budget=budgetService.findOne(budgetId);
		group.setBudget(budget);
		budget.getGroups().add(group);
		return save(group);
	}


	public Group save(Group group) {
		return groupDao.save(group);
		
	}


	public Group findOne(Long groupId) {
		return groupDao.getOne(groupId);
	}
}
