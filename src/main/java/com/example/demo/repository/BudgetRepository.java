package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.BudgetModel;

public interface BudgetRepository {
	public boolean isAddBudget(BudgetModel budget);
	public List<BudgetModel> getAllBudgets();
	public boolean isdeleteBudgetById(int bid);
	public boolean isupdateBudget(BudgetModel budget);
	

}
