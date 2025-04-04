package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryModel;
import com.example.demo.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
    CategoryRepository categoryRepo;

	@Override
	public boolean addCategory(CategoryModel category) {
		return categoryRepo.addCategory(category);
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		return categoryRepo.getAllCategories();
	}

	

	
}
