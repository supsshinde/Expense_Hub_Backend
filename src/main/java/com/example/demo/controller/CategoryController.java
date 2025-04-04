package com.example.demo.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.service.CategoryService;

@RestController
@RequestMapping("/category")

public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	List<CategoryModel> list=new ArrayList<CategoryModel>();
	
	@PostMapping("/addCategory")
	public String addCategory(@RequestBody CategoryModel category)
	{
		boolean b=categoryService.addCategory(category);
		System.out.println("Controller" +category.getCname());

		if(b)
		{
			return "Category Added";
		}
		else
		{
			return "Category Not Added";
		}
	}
	@GetMapping("/viewCategory")
	public List<CategoryModel> getAllCategories()
	{
		list = categoryService.getAllCategories(); 
		
	    return (list != null && !list.isEmpty()) ? list : Collections.emptyList();
	}
	
	
//	@DeleteMapping("/deleteCategory/{did}")
//	public List<CategoryModel> deleteCategory(@PathVariable int did)
//	{
//		boolean b=categoryService.deleteCategory(did);
//	   list =categoryService.getAllCategories();
//	    return (list != null && !list.isEmpty()) ? list : Collections.emptyList();
//	}
//	
	
}
