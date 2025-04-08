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
	public boolean isUpdate(ExpenseModel expense) {
		return expRepo.isUpdate(expense);
	}

}
