package com.example.demo.repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
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
	   // List<CategoryModel> list=new ArrayList<CategoryModel>();
	@Override
	public boolean addExpense(ExpenseModel expense) {
		int value=template.update("insert into expense values('0',?,?,?,?,?,?)",new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, expense.getEname());
				ps.setFloat(2, expense.getEprice());
				ps.setString(3,expense.getPaymentMethod());
				ps.setString(4,expense.getDescription());
				ps.setDate(5, sqlDate);
				ps.setInt(6, expense.getCid());
				
				
				
			}
		});
		return value>0?true:false;
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
	public boolean isDeleteExpenseById(int eid) {
		int value=template.update("delete from expense where eid=?",eid);
		return value>0?true:false;
	}
	@Override
	public boolean isUpdate(ExpenseModel expense) {
		int value=template.update("update expense set  ename=?, eprice=?,payment_method=?,description=?,expense_date=?, cid=? where eid=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, expense.getEname());
				ps.setFloat(2, expense.getEprice());
				ps.setString(3, expense.getPaymentMethod());
				ps.setString(4,expense.getDescription());
				ps.setDate(5, sqlDate);
				ps.setInt(6,expense.getCid());
				 ps.setInt(7, expense.getEid());
				
			}
		});
		return value>0?true:false;
	}
	

}
