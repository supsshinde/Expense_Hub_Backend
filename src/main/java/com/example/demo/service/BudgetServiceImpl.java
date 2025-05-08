package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.BudgetModel;
import com.example.demo.repository.BudgetRepositoryImpl;
@Service
public class BudgetServiceImpl implements BudgetService {
	@Autowired
	BudgetRepositoryImpl budgetRepo;

	@Override
	public boolean isAddBudget(BudgetModel budget) {
		
		return budgetRepo.isAddBudget(budget);
	}

	@Override
	public List<BudgetModel> getAllBudgets() {
		
		return budgetRepo.getAllBudgets();
	}

	@Override
	public boolean isdeleteBudgetById(int bid) {
		
		return budgetRepo.isdeleteBudgetById(bid);
	}

	@Override
	public boolean isupdateBudget(BudgetModel budget) {
		
		return budgetRepo.isupdateBudget(budget);
	}

	@Override
	public List<BudgetModel> getBudgetsByUid(int uid) {
		// TODO Auto-generated method stub
		return budgetRepo.getBudgetsByUid(uid);
	}

	@Override
	public boolean updateBudgetByIdAndUid(BudgetModel budget) {
		// TODO Auto-generated method stub
		return budgetRepo.updateBudgetByIdAndUid(budget)>0;
	}
	public Double findTotalBudgetByUserId(int uid) {
	    return budgetRepo.findTotalBudgetByUserId(uid);
	}


}
