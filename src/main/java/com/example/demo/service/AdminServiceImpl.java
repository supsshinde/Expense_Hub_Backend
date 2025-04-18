package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseCategoryDistribution;
import com.example.demo.model.UserModel;
import com.example.demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
    AdminRepository AdminRepo;

	@Override
	public boolean addCategory(CategoryModel category) {
		return AdminRepo.addCategory(category);
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		return AdminRepo.getAllCategories();
	}

	@Override
	public List<UserModel> getAllUsers() {

		return AdminRepo.getAllUsers();
	}

	@Override
	public boolean deleteCategoryById(int cid) {
		// TODO Auto-generated method stub
		return AdminRepo.deleteCategoryById(cid);
	}

	@Override
	public boolean updateCategory(CategoryModel category) {
		// TODO Auto-generated method stub
		return AdminRepo.updateCategory(category);
	}

	@Override
	public boolean deleteUser(int uid) {
		// TODO Auto-generated method stub
		return AdminRepo.deleteUser(uid);
	}

	@Override
	public int getTotalCategoryCount() {
		// TODO Auto-generated method stub
		return AdminRepo.getTotalCategoryCount();
	}

	@Override
	public int getTotalUserCount() {
		// TODO Auto-generated method stub
		return AdminRepo.getTotalUserCount();
	}

	@Override
	public int getTotalExpensesCount() {
		// TODO Auto-generated method stub
		return AdminRepo.getTotalExpensesCount();
	}

	@Override
	public int getTotalBudgetCount() {
		// TODO Auto-generated method stub
		return AdminRepo.getTotalBudgetCount();
	}

	@Override
	public List<ExpenseCategoryDistribution> getExpenseDistributionByCategory() {
		// TODO Auto-generated method stub
		return AdminRepo.getExpenseDistributionByCategory();
	}
}
