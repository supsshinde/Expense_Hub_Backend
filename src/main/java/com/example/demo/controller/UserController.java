package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BudgetExpenseSummary;
import com.example.demo.model.BudgetModel;
import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.AdminService;
import com.example.demo.service.BudgetService;
import com.example.demo.service.ExpenseServiceImpl;
import com.example.demo.service.UserService;

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

	@PutMapping("/editProfile/{uid}")
    public ResponseEntity<String> editProfile(@PathVariable int uid, @RequestBody UserModel user) {
        boolean success = userService.updateUser(user, uid);
        
        if (success) {
            return ResponseEntity.ok("Profile updated successfully!");
        } else {
            return ResponseEntity.status(404).body("User not found!");
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

	@PostMapping("/forgotPassword")
	public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> request) {
	    String email = request.get("email");
	    String newPassword = request.get("newPassword");

	    boolean success = userService.resetPassword(email, newPassword);
	    if (success) {
	        return ResponseEntity.ok("Password reset successfully!");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
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
		list1 = adminService.getAllCategories(); 
	   return (list1 != null && !list1.isEmpty()) ? list1 : Collections.emptyList();
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
	    String email = loginData.get("username");
	    String password = loginData.get("password");

	    UserModel user = userService.getUserByEmailAndPassword(email, password);
	    if (user != null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("uid", user.getUid());               
	        response.put("message", "Login Successful!");     
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
	    }
	}
	
//	@PostMapping("/login")
//	public String validateUser(@RequestBody UserModel user) {
//		System.out.println(user);
//		boolean b=userService.loginUser(user.getEmail(), user.getPassword());
//		System.out.println(b);
//		if(b)return "Login success..";
//		else return "login failed";
//	}

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
	 @GetMapping("/viewExpenseByUid")
	    public List<ExpenseModel> getExpensesByUserId(@RequestParam Integer uid) {
	        // Call the service method to fetch the expenses for the given user ID
	        return expService.getExpensesByUserId(uid);
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
	@GetMapping("/viewBudgetsByUid/{uid}")
	public List<BudgetModel> getBudgetsByUid(@PathVariable int uid) {
	    return budgetService.getBudgetsByUid(uid);
	}

	
	@GetMapping("/expense/user/{uid}")
	public ResponseEntity<List<ExpenseModel>> getUserExpenses(@PathVariable int uid) {
	    List<ExpenseModel> expenses = expService.getExpensesByUid(uid);
	    return ResponseEntity.ok(expenses);
	}
	@GetMapping("/totalExpenseByUid/{uid}")
    public ResponseEntity<String> getTotalExpense(@PathVariable int uid) {
        Double totalExpense = expService.findTotalExpenseByUserId(uid);
        if (totalExpense != null) {
            return ResponseEntity.ok("Total Expense for user " + uid + " is ₹" + totalExpense);
        } else {
            return ResponseEntity.status(404).body("No expenses found for this user.");
        }
    }
	@PutMapping("/updateBudgetById/{bid}")
	public ResponseEntity<String> updateBudget(@PathVariable int bid, @RequestBody BudgetModel budget) {
	    budget.setBid(bid); // from path variable
	    boolean success = budgetService.updateBudgetByIdAndUid(budget);
	    if (success) {
	        return ResponseEntity.ok("Budget updated successfully.");
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Budget update failed.");
	    }
	}
	@GetMapping("/budget-expense-summary/{uid}")
	public ResponseEntity<BudgetExpenseSummary> getBudgetExpenseSummary(@PathVariable int uid) {
	    double totalExpense = expService.findTotalExpenseByUserId(uid);
	    double totalBudget = budgetService.findTotalBudgetByUserId(uid);

	    String message = totalExpense > totalBudget ? "⚠️ Budget Exceeded!" : "✅ Within Budget";

	    BudgetExpenseSummary dto = new BudgetExpenseSummary(totalBudget, totalExpense, message);

	    return ResponseEntity.ok(dto);
	}


}
