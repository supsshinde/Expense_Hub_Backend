package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import com.example.demo.model.ExpenseModel;

public interface ExpenseRepository {
	public boolean addExpense(ExpenseModel expense);
	public List<ExpenseModel> getAllExpenses();
	public boolean isDeleteExpenseById(int eid);
	//public boolean isUpdate(ExpenseModel expense);
	public List<ExpenseModel> fetchAllExpensesWithCategory();
	public ExpenseModel getExpenseById(int eid);
	public String updateExpense(ExpenseModel exp);
	public void addExpenseForUser(int uid, ExpenseModel expense);
	List<ExpenseModel> getExpensesByUid(int uid);
	 public List<ExpenseModel> getExpensesByUserId(Integer uid);
	 public Double findTotalExpenseByUserId(int uid);
	 public int countExpensesByUid(int uid);
	 public List<Map<String, Object>> getCategoryWiseExpense(int uid);
	 public List<ExpenseModel> getUserExpensesBetweenDates(int uid, String fromDate, String toDate);
	 public float getTotalExpenseAmount(int uid, String fromDate, String toDate);

}
