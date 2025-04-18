package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseCategoryDistribution;
import com.example.demo.model.UserModel;

public interface AdminRepository {

	public boolean addCategory(CategoryModel category);
	public List<CategoryModel> getAllCategories();
	public boolean deleteCategoryById(int cid);
	public boolean updateCategory(CategoryModel category);
	public List<UserModel> getAllUsers();
	public boolean deleteUser(int uid);
	public int getTotalCategoryCount();
	public int getTotalUserCount();
	public int getTotalExpensesCount();
	public int getTotalBudgetCount();
	public List<ExpenseCategoryDistribution> getExpenseDistributionByCategory();
	
}
