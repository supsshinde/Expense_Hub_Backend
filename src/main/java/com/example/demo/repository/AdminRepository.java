package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;

public interface AdminRepository {

	public boolean addCategory(CategoryModel category);
	public List<CategoryModel> getAllCategories();
	public List<UserModel> getAllUsers();
}
