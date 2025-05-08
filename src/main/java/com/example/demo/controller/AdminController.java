package com.example.demo.controller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseCategoryDistribution;
import com.example.demo.model.UserModel;
import com.example.demo.service.AdminService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	List<UserModel> user=new ArrayList<UserModel>();
	List<CategoryModel> category=new ArrayList<CategoryModel>();
	
	@PostMapping("/adminLogin")
	public boolean adminLogin(@RequestParam String  username, @RequestParam String password)
	{
		System.out.println(username+ " " +password);
		boolean adminLogin = adminService.adminLogin(username, password);
		
		return adminLogin ? true: false;
	}
	
	@PostMapping("/addCategory")
	public String addCategory(@RequestBody CategoryModel cat)
	{
		boolean b=adminService.addCategory(cat);

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
	@DeleteMapping("/deleteCategory/{cid}")
	public String deleteCategory(@PathVariable int cid) {
	    boolean b = adminService.deleteCategoryById(cid);
	    return b ? "Category deleted" : "Category not found";
	}

	@PutMapping("/updateCategory")
	public String updateCategory(@RequestBody CategoryModel category) {
	    boolean b = adminService.updateCategory(category);
	    return b ? "Category updated" : "Update failed";
	}

	
	@GetMapping("/viewUsers")
	public List<UserModel> getAllUser( UserModel users)
	{
		 user=adminService.getAllUsers();
		    return (user != null && !user.isEmpty()) ? user : Collections.emptyList();

	}
	@DeleteMapping("/deleteUser/{uid}")
	public String deleteUser(@PathVariable int uid) {
	    boolean result = adminService.deleteUser(uid);
	    return result ? "User deleted" : "User not found";
	   
	}
	@GetMapping("/countCategory")
    public int getTotalCategoryCount() {
        return adminService.getTotalCategoryCount();
    }
	 @GetMapping("/countUsers")
	    public int getTotalUsers() {
	        return adminService.getTotalUserCount();
	    }
	 @GetMapping("/countExpenses")
	    public int getTotalExpenses() {
	        return adminService.getTotalExpensesCount();
	    }
	 @GetMapping("/countBudgets")
	    public int getTotalBudgetCount() {
	        return adminService.getTotalBudgetCount();
	    }
	 
	 @GetMapping("/expense-distribution")
	    public List<ExpenseCategoryDistribution> getExpenseDistribution() {
	        return adminService.getExpenseDistributionByCategory();
	    }
}
