package com.example.demo.service;
import java.util.List;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;

public interface AdminService {

	public boolean addCategory(CategoryModel category);
	public List<CategoryModel> getAllCategories();
	public List<UserModel> getAllUsers();
}
