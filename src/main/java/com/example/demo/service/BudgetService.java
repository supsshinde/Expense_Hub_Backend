package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BudgetModel;

public interface BudgetService {
	public boolean isAddBudget(BudgetModel budget);
	public List<BudgetModel> getAllBudgets();
	public boolean isdeleteBudgetById(int bid);
	public boolean isupdateBudget(BudgetModel budget);
	public List<BudgetModel> getBudgetsByUid(int uid);
	public boolean updateBudgetByIdAndUid(BudgetModel budget);
	public Double findTotalBudgetByUserId(int uid);
	
}
