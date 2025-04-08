package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseModel;
import com.example.demo.service.ExpenseServiceImpl;

@RestController
@RequestMapping("/expense")
public class Expense_controller {
	@Autowired
	ExpenseServiceImpl expService;
	List<ExpenseModel> list=new ArrayList<>();
	@PostMapping("/addExpense")
	public String addExpense(@RequestBody ExpenseModel expense )
	{
		boolean b=expService.addExpense(expense);
		if(b)
		{
			return "expense added";
		}
		else
		{
			return "expense not added";
		}
	
	}
	@GetMapping("/viewExpense")
	public List<ExpenseModel> viewExpense()
	{
		list=expService.getAllExpenses();
		 return (list != null && !list.isEmpty()) ? list : Collections.emptyList();
		
	}
	
	@DeleteMapping("/deleteById/{eid}")
	public String deleteExpensebyId(@PathVariable int eid)
	{
		boolean b1=expService.isDeleteExpenseById(eid);
		if(b1)
		{
			return "expense deleted";
		}
		else
		{
			return "expense not found to delete";
		}
		
	}
	@PutMapping("/updateById")
	public String UpdateExpense(@RequestBody ExpenseModel expense)
	{
		boolean b2=expService.isUpdate(expense);
		if(b2)
		{
			return "expense Updated";
		}
		else
		{
			return "expense not found to update";
		}
		
	}
	
	

}
