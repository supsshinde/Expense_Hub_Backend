package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.model.ExpenseModel;
import com.example.demo.model.ExpenseReportResponse;

public interface ExpenseService {
	public boolean addExpense(ExpenseModel expense);
	public List<ExpenseModel> getAllExpenses();
	public boolean isDeleteExpenseById(int eid);
	//public boolean isUpdate(ExpenseModel expense);
	public List<ExpenseModel> fetchAllExpensesWithCategory();
	public ExpenseModel getExpenseById(int eid) ;
	public String updateExpense(ExpenseModel exp);
	public void addExpenseForUser(int uid, ExpenseModel expense);
	List<ExpenseModel> getExpensesByUid(int uid);
	 public List<ExpenseModel> getExpensesByUserId(Integer uid) ;
	 public Double findTotalExpenseByUserId(int uid);
	 public int countExpensesByUid(int uid);
	 public List<Map<String, Object>> getCategoryWiseExpense(int uid);
	 public List<ExpenseModel> getUserExpensesByDateRange(int uid, String fromDate, String toDate);
	 public ExpenseReportResponse getUserExpenseReport(int uid, String fromDate, String toDate);
}
