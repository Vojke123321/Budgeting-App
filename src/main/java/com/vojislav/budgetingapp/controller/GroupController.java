package com.vojislav.budgetingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vojislav.budgetingapp.domain.Budget;
import com.vojislav.budgetingapp.domain.Group;
import com.vojislav.budgetingapp.services.BudgetService;
import com.vojislav.budgetingapp.services.GroupService;

@Controller
@RequestMapping("/budgets/{budgetId}/groups")
public class GroupController {
	
	@Autowired
	private GroupService groupService;

	@PostMapping
	public String postGroups(@PathVariable Long budgetId,Model model) {
		
		Group group = groupService.createNewGroup(budgetId);
		model.addAttribute("group",group);
		return "redirect:/budgets/"+budgetId+"/groups/"+group.getId();
		
	}
	
	@GetMapping("{groupId}")
	public String getGroup(@PathVariable Long groupId,Model model) {
		
		Group group=groupService.findOne(groupId);
		model.addAttribute("group",group);
		return "group";
	}
	
	@PostMapping("{groupId}")
	public String postGroup(@ModelAttribute Group group,@PathVariable Long budgetId,@PathVariable Long groupId,Model model) {
		Group groupFromDb=groupService.findOne(groupId);
		System.out.println(groupFromDb.getName());
		groupFromDb.setName(group.getName());
		groupFromDb=groupService.save(groupFromDb);
		return "redirect:/budgets/"+budgetId;
	}
	
}
