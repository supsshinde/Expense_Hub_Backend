package com.example.demo.repository;

import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.ExpenseModel;

@Repository
public class ExpenseRepositoryImpl implements ExpenseRepository {

	@Autowired
	JdbcTemplate template;
	 LocalDate currentDate = LocalDate.now(); // Get system date
	    Date sqlDate = Date.valueOf(currentDate); 
	    
	    List<ExpenseModel> list=new ArrayList<ExpenseModel>(); 
	  
	    public boolean addExpense(ExpenseModel expense) {
	        try {
	            // Step 1: Insert into expense table
	            String expenseSql = "INSERT INTO expense (ename, eprice, payment_method, description, expense_date, cid) VALUES (?, ?, ?, ?, ?, ?)";
	            template.update(expenseSql,
	                expense.getEname(),
	                expense.getEprice(),
	                expense.getPaymentMethod(),
	                expense.getDescription(),
	                sqlDate,
	                expense.getCid()
	            );

	            // Step 2: Get the inserted eid
	            Integer eid = template.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

	            // Step 3: Insert into user_expense
	            String userExpenseSql = "INSERT INTO user_expense (uid, eid, expense_price) VALUES (?, ?, ?)";
	            template.update(userExpenseSql,
	                expense.getUid(),
	                eid,
	                expense.getEprice()
	            );

	            return true; // ✅ Success
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false; // ❌ Failure
	        }
	    }

	 // Service
	    public List<ExpenseModel> getExpensesByUserId(Integer uid) {
	    	String sql = "SELECT e.eid, e.ename, e.eprice, e.payment_method, e.description, e.expense_date, " +
	                "e.cid, c.cname AS category_name, u.uid AS uid " +
	                "FROM expense e " +
	                "JOIN category c ON e.cid = c.cid " +
	                "JOIN user_expense ue ON e.eid = ue.eid " +
	                "JOIN user u ON ue.uid = u.uid " +
	                "WHERE u.uid = ?";



	        // Query the database and map the result directly without a separate RowMapper
	        return template.query(sql, new Object[]{uid}, (rs, rowNum) -> {
	            ExpenseModel expense = new ExpenseModel();
	            expense.setEid(rs.getInt("eid"));
	            expense.setEname(rs.getString("ename"));
	            expense.setEprice(rs.getFloat("eprice"));
	            expense.setPaymentMethod(rs.getString("payment_method"));
	            expense.setDescription(rs.getString("description"));
	            expense.setExpenseDate(rs.getDate("expense_date"));
	            expense.setCid(rs.getInt("cid"));
	            expense.setCategoryName(rs.getString("category_name")); // Mapping the category name directly
	            expense.setUid(rs.getInt("uid"));


	            return expense;
	        });
	    }

	
	@Override
	public List<ExpenseModel> getAllExpenses() {
		list=template.query("select * from expense", new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				ExpenseModel exp=new ExpenseModel();
				exp.setEid(rs.getInt(1));
				exp.setEname(rs.getString(2));
				exp.setEprice(rs.getFloat(3));
				exp.setPaymentMethod(rs.getString(4));
				exp.setDescription(rs.getString(5));
				exp.setExpenseDate(rs.getDate(6));
				exp.setCid(rs.getInt(7));
				return exp;
			}
		});
		return list;
	}
	@Override
	public List<ExpenseModel> getExpensesByUid(int uid) {
	    String sql = "SELECT e.* FROM expense e " +
	                 "JOIN user_expense ue ON e.eid = ue.eid " +
	                 "WHERE ue.uid = ?";
	    
	    return template.query(sql, new BeanPropertyRowMapper<>(ExpenseModel.class), uid);
	}

	@Override
	public boolean isDeleteExpenseById(int eid) {
		int value=template.update("delete from expense where eid=?",eid);
		return value>0?true:false;
	}
	
	@Override
	public List<ExpenseModel> fetchAllExpensesWithCategory() {
		String sql = "SELECT e.eid, e.ename, e.eprice, e.payment_method, e.description, e.expense_date, e.cid, c.cname " +
                "FROM expense e " +
                "JOIN category c ON e.cid = c.cid";

   return template.query(sql, new RowMapper<ExpenseModel>() {
       @Override
       public ExpenseModel mapRow(ResultSet rs, int rowNum) throws SQLException {
           ExpenseModel exp = new ExpenseModel();
           exp.setEid(rs.getInt("eid"));
           exp.setEname(rs.getString("ename"));
           exp.setEprice(rs.getFloat("eprice"));
           exp.setPaymentMethod(rs.getString("payment_method"));
           exp.setDescription(rs.getString("description"));
           exp.setExpenseDate(rs.getDate("expense_date"));
           exp.setCid(rs.getInt("cid"));
           exp.setCategoryName(rs.getString("cname")); // from JOIN
           return exp;
       }
   });
	}
	
	public ExpenseModel getExpenseById(int eid) {
	    String sql = "SELECT * FROM expense WHERE eid = ?";
	    return template.queryForObject(sql, new BeanPropertyRowMapper<>(ExpenseModel.class), eid);
	}
	public String updateExpense(ExpenseModel exp) {
	    String sql = "UPDATE expense SET ename = ?, eprice = ?, payment_method = ?, description = ?, expense_date = ?, cid = ? WHERE eid = ?";
	    int rows = template.update(sql,
	        exp.getEname(),
	        exp.getEprice(),
	        exp.getPaymentMethod(),
	        exp.getDescription(),
	        exp.getExpenseDate(),
	        exp.getCid(),
	        exp.getEid()
	    );
	    return rows > 0 ? "Expense updated successfully" : "Failed to update expense";
	}

	@Override
	public void addExpenseForUser(int uid, ExpenseModel expense) {
		String insertExpense = "INSERT INTO expense (ename, eprice, payment_method, description, expense_date, cid) VALUES (?, ?, ?, ?, ?, ?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		template.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(insertExpense, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, expense.getEname());
			ps.setDouble(2, expense.getEprice());
			ps.setString(3, expense.getPaymentMethod());
			ps.setString(4, expense.getDescription());
			ps.setDate(5, sqlDate);
			ps.setInt(6, expense.getCid());
			return ps;
		}, keyHolder);

		int eid = keyHolder.getKey().intValue();

		// Insert into `user_expense` table
		String insertUserExpense = "INSERT INTO user_expense (uid, eid, expense_price) VALUES (?, ?, ?)";
		template.update(insertUserExpense, uid, eid, expense.getEprice());
	}
	
	 public Double findTotalExpenseByUserId(int uid) {
		 String sql = "SELECT SUM(expense_price) FROM user_expense WHERE uid = ?";
	        Double totalExpense = template.queryForObject(sql, Double.class, uid);
	        return totalExpense != null ? totalExpense : 0.0;
	    }
}
