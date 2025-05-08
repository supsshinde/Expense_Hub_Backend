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

import com.example.demo.model.BudgetModel;
@Repository
public class BudgetRepositoryImpl implements BudgetRepository {
	@Autowired
	JdbcTemplate template;
	 LocalDate currentDate = LocalDate.now(); // Get system date
	    Date sqlDate = Date.valueOf(currentDate); 
	    List<BudgetModel> list=new ArrayList<>();

	@Override
	public boolean isAddBudget(BudgetModel budget) {
		int value=template.update("insert into budget values(0,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1,budget.getUid());
				ps.setFloat(2,budget.getBudgetAmount());
				ps.setDate(3, sqlDate);
				 ps.setDate(4, budget.getEndDate());
				
			}
		});
		return value>0?true:false;
	}

	@Override
	public List<BudgetModel> getAllBudgets() {
	    List<BudgetModel> list = template.query("SELECT * FROM Budget", new RowMapper<BudgetModel>() {
	        @Override
	        public BudgetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	            BudgetModel b = new BudgetModel();
	            b.setBid(rs.getInt(1));
	            b.setUid(rs.getInt(2));
	            b.setBudgetAmount(rs.getFloat(3));
	            b.setStartDate(rs.getDate(4)); // assuming column 4 is startDate
	            b.setEndDate(rs.getDate(5));   // assuming column 5 is endDate
	            return b;
	        }
	    });
	    return list;
	

		
	}

	@Override
	public boolean isdeleteBudgetById(int bid) {
		int value=template.update("delete from budget where bid=?",bid);
		return value>0?true:false;
	}

//	@Override
//	public boolean isupdateBudget(BudgetModel budget) {
//	    int value = template.update("update budget set uid=?, budget_amount=?, start_date=?, end_date=? where bid=?", 
//	        new PreparedStatementSetter() {
//	            @Override
//	            public void setValues(PreparedStatement ps) throws SQLException {
//	                ps.setInt(1, budget.getUid());
//	                ps.setFloat(2, budget.getBudgetAmount());
//	                ps.setDate(3, budget.getStartDate());
//	                ps.setDate(4, budget.getEndDate());
//	                ps.setInt(5, budget.getBid());
//	            }
//	        }
//	    );
//	    return value > 0 ? true : false;
//	}
	@Override
    public boolean isupdateBudget(BudgetModel budget) {
        int value = template.update(
            "UPDATE budget SET budget_amount = ?, start_date = ?, end_date = ? WHERE bid = ? AND uid = ?",
            new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setFloat(1, budget.getBudgetAmount());
                    ps.setDate(2, budget.getStartDate());
                    ps.setDate(3, budget.getEndDate());
                    ps.setInt(4, budget.getBid());
                    ps.setInt(5, budget.getUid());  // Ensure the budget belongs to the logged-in user
                }
            }
        );
        return value > 0;
    }
	@Override
	public List<BudgetModel> getBudgetsByUid(int uid) {
	    return template.query("SELECT * FROM budget WHERE uid = ?", new Object[]{uid}, new RowMapper<BudgetModel>() {
	        @Override
	        public BudgetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	            BudgetModel b = new BudgetModel();
	            b.setBid(rs.getInt(1));
	            b.setUid(rs.getInt(2));
	            b.setBudgetAmount(rs.getFloat(3));
	            b.setStartDate(rs.getDate(4));
	            b.setEndDate(rs.getDate(5));
	            return b;
	        }
	    });
	}

	@Override
	public int updateBudgetByIdAndUid(BudgetModel budget) {
		String sql = "UPDATE budget SET budget_amount = ?, start_date = ?, end_date = ? WHERE bid = ? AND uid = ?";
        return template.update(sql, budget.getBudgetAmount(), budget.getStartDate(), budget.getEndDate(), budget.getBid(), budget.getUid());
	}
	public Double findTotalBudgetByUserId(int uid) {
	    String sql = "SELECT SUM(budget_amount) FROM budget WHERE uid = ?";
	    return template.queryForObject(sql, Double.class, uid);
	}



}
