package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BudgetModel;
import com.example.demo.service.BudgetService;

@RestController
public class BudgetController {
	@Autowired
	BudgetService budgetService;
	List<BudgetModel> list;
	@PostMapping("/addBudget")
	public String addBudget(@RequestBody BudgetModel budget)
	{
		boolean b=budgetService.isAddBudget(budget);
		if(b)
		{
			return "budget Added";
		}
		else
		{
			return "some probkem is there";
		}
	}
	@GetMapping("/viewBudgets")
	public List<BudgetModel> getAllBudget()
	{
		list=budgetService.getAllBudgets();
		return (list != null && !list.isEmpty()) ? list : Collections.emptyList();
		//return null;
		
	}
	@DeleteMapping("/deletebudetById/{bid}")
	public String deleteBudgetById(@PathVariable int bid) {
		boolean b1=budgetService.isdeleteBudgetById(bid);
		if(b1)
		{
			return "budget deleted";
		}
		else
		{
			return "budget id not found to delete";
		}
	}
	@PutMapping("/updateBudgetById")
	public String updateBudgetById(@RequestBody BudgetModel budget)
	{
		boolean b2=budgetService.isupdateBudget(budget);
		if(b2)
		{
			return "budget updated";
		}
		else
		{
			return "budget not update some problem is there";
		}
		
	}
	
	
	

}
