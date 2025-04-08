package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryModel;
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
}
