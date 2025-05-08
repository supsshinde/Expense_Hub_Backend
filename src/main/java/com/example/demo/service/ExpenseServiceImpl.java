package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ExpenseModel;
import com.example.demo.repository.ExpenseRepository;
@Service
public class ExpenseServiceImpl implements ExpenseService {
	@Autowired
	ExpenseRepository expRepo;

	@Override
	public boolean addExpense(ExpenseModel expense) {
		
		return expRepo.addExpense(expense);
	}

	@Override
	public List<ExpenseModel> getAllExpenses() {
		// TODO Auto-generated method stub
		return expRepo.getAllExpenses();
	}

	@Override
	public boolean isDeleteExpenseById(int eid) {
		// TODO Auto-generated method stub
		return expRepo.isDeleteExpenseById(eid);
	}

	@Override
	public List<ExpenseModel> fetchAllExpensesWithCategory() {
		// TODO Auto-generated method stub
		return expRepo.fetchAllExpensesWithCategory();
	}

	@Override
	public ExpenseModel getExpenseById(int eid) {
		// TODO Auto-generated method stub
		return expRepo.getExpenseById(eid);
	}

	@Override
	public String updateExpense(ExpenseModel exp) {
		// TODO Auto-generated method stub
		return expRepo.updateExpense(exp);
	}

	@Override
	public void addExpenseForUser(int uid, ExpenseModel expense) {
		// TODO Auto-generated method stub
		expRepo.addExpenseForUser(uid, expense);
		
	}

	@Override
	public List<ExpenseModel> getExpensesByUid(int uid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ExpenseModel> getExpensesByUserId(Integer uid) {
		// TODO Auto-generated method stub
		return expRepo.getExpensesByUserId(uid);
	}

	@Override
	public Double findTotalExpenseByUserId(int uid) {
		// TODO Auto-generated method stub
		return expRepo.findTotalExpenseByUserId(uid);
	}

}
