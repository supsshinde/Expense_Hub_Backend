 
  package com.example.demo.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")

public class AdminController {

	@Autowired
	AdminService adminService;
	
	List<UserModel> user=new ArrayList<UserModel>();
	List<CategoryModel> category=new ArrayList<CategoryModel>();
	
	
	@PostMapping("/addCategory")
	public String addCategory(@RequestBody CategoryModel cat)
	{
		boolean b=adminService.addCategory(cat);
		System.out.println("Controller" +cat.getCname());

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
		category = adminService.getAllCategories(); 
		
	    return (category != null && !category.isEmpty()) ? category : Collections.emptyList();
	}
	
	@GetMapping("/viewUsers")
	public List<UserModel> getAllUser(@RequestBody UserModel users)
	{
		 user=adminService.getAllUsers();
		    return (user != null && !user.isEmpty()) ? user : Collections.emptyList();

	}
}
