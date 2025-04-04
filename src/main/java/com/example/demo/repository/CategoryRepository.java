package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.CategoryModel;

public interface CategoryRepository {

	public boolean addCategory(CategoryModel category);
	public List<CategoryModel> getAllCategories();
	

}
