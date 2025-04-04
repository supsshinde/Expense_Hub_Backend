package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.CategoryService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	CategoryService categoryService;
	
	List<UserModel> list=new ArrayList<UserModel>();
	List<CategoryModel> list1=new ArrayList<CategoryModel>();
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody UserModel user)
	{
		boolean b=userService.addUser(user);
		if(b)
		{
			return "User Added Successfully";
		}
		else
		{
			return "User Not Added";
		}
	}
	@PutMapping("/updateUser/{uid}")
	public String updateUser(@RequestBody UserModel user, @PathVariable int uid) {
		boolean b = userService.updateUser(user, uid);

		if(b)
		{
			return "User Updated";
		}
		else
		{
			return "User Not Updated";
		}
	}
	@GetMapping("/searchUser/{pattern}")
	public List<UserModel> searchUser(@PathVariable String pattern, UserModel user)
	{
	    list=userService.searchUser(pattern, user);	
		return list;
	}
	
	@GetMapping("category/viewCategory")
	public List<CategoryModel> getAllCategories()
	{
		list1 = categoryService.getAllCategories(); 
	   return (list1 != null && !list1.isEmpty()) ? list1 : Collections.emptyList();
	}
}
