package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BudgetModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.AdminService;
import com.example.demo.service.BudgetService;
import com.example.demo.service.ExpenseServiceImpl;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
	AdminService adminService;
	@Autowired
	ExpenseServiceImpl expService;
	List<ExpenseModel> list11=new ArrayList<>();
	
	List<UserModel> list=new ArrayList<UserModel>();
	List<CategoryModel> list1=new ArrayList<CategoryModel>();
	@Autowired
	BudgetService budgetService;
	List<BudgetModel> list2;
	
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
	@GetMapping("/viewProfile/{uid}")
	public ResponseEntity<UserModel> viewProfile(@PathVariable int uid) {
	    UserModel user = userService.getUserById(uid);
	    if (user != null) {
	        return ResponseEntity.ok(user);
	    }
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
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
		list1 = adminService.getAllCategories(); 
	   return (list1 != null && !list1.isEmpty()) ? list1 : Collections.emptyList();
	}
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData) {
	    String email = loginData.get("username");
	    String password = loginData.get("password");

	    boolean isValid = userService.loginUser(email, password);
	    if (isValid) {
	        return ResponseEntity.ok("Login Successful!");
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
	    }
	}
	
	@PostMapping("/addExpense")
	public String addExpense(@RequestBody ExpenseModel expense )
	{	
		boolean b=expService.addExpense(expense);
		if(b)
		{
			return "expense added";
		}
		else
		{
			return "expense not added";
		}
	
	}
//	@GetMapping("/viewExpense")
//	public List<ExpenseModel> viewExpense()
//	{
//		list11=expService.getAllExpenses();
//		 return (list11 != null && !list11.isEmpty()) ? list11 : Collections.emptyList();
//		
//	}
	
	@DeleteMapping("/deleteById/{eid}")
	public String deleteExpensebyId(@PathVariable int eid)
	{
		boolean b1=expService.isDeleteExpenseById(eid);
		if(b1)
		{
			return "expense deleted";
		}
		else
		{
			return "expense not found to delete";
		}
		
	}
	@GetMapping("/viewExpense")
	public List<ExpenseModel> viewAllExpensesWithCategory() {
	    return expService.fetchAllExpensesWithCategory();
	}
	@GetMapping("/getExpenseById/{eid}")
	public ExpenseModel getExpenseById(@PathVariable int eid) {
	    return expService.getExpenseById(eid);
	}
	@PutMapping("/updateExpense/{eid}")
	public String updateExpense(@RequestBody ExpenseModel expense, @PathVariable int eid) {
	    expense.setEid(eid);
	    return expService.updateExpense(expense);
	}
	
	@PostMapping("/addBudget")
	public String addBudget(@RequestBody BudgetModel budget)
	{
		boolean b=budgetService.isAddBudget(budget);
		if(b)
		{
			return "budget Added";
		}
		else
		{
			return "some probkem is there";
		}
	}
	@GetMapping("/viewBudgets")
	public List<BudgetModel> getAllBudget()
	{
		list2=budgetService.getAllBudgets();
		return (list2 != null && !list2.isEmpty()) ? list2 : Collections.emptyList();
		//return null;
		
	}
	@PutMapping("/updateBudgetById")
	public String updateBudgetById(@RequestBody BudgetModel budget)
	{
		boolean b2=budgetService.isupdateBudget(budget);
		if(b2)
		{
			return "budget updated";
		}
		else
		{
			return "budget not update some problem is there";
		}
		
	}
	@DeleteMapping("/deletebudetById/{bid}")
	public String deleteBudgetById(@PathVariable int bid) {
		boolean b1=budgetService.isdeleteBudgetById(bid);
		if(b1)
		{
			return "budget deleted";
		}
		else
		{
			return "budget id not found to delete";
		}
	}




//	@PutMapping("/updateById")
//	public String UpdateExpense(@RequestBody ExpenseModel expense)
//	{
//		boolean b2=expService.isUpdate(expense);
//		if(b2)
//		{
//			return "expense Updated";
//		}
//		else
//		{
//			return "expense not found to update";
//		}
//		
//	}
	

}
