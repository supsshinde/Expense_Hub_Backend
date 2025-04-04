package com.example.demo.service;
import java.util.List;

import com.example.demo.model.CategoryModel;

public interface CategoryService {

	public boolean addCategory(CategoryModel category);
	public List<CategoryModel> getAllCategories();
}
