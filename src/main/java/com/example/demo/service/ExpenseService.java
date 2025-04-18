package com.example.demo.service;

import java.util.List;

import com.example.demo.model.ExpenseModel;

public interface ExpenseService {
	public boolean addExpense(ExpenseModel expense);
	public List<ExpenseModel> getAllExpenses();
	public boolean isDeleteExpenseById(int eid);
	//public boolean isUpdate(ExpenseModel expense);
	public List<ExpenseModel> fetchAllExpensesWithCategory();
	public ExpenseModel getExpenseById(int eid) ;
	public String updateExpense(ExpenseModel exp);
	public void addExpenseForUser(int uid, ExpenseModel expense);
}
