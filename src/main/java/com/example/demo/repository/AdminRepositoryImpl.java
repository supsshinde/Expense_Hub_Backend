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
import com.example.demo.model.ExpenseCategoryDistribution;
import com.example.demo.model.UserModel;

@Repository
public class AdminRepositoryImpl implements AdminRepository{

	@Autowired
	JdbcTemplate template;
	
	List<CategoryModel> list=new ArrayList<CategoryModel>();
	List<UserModel> list1=new ArrayList<UserModel>();


	@Override
	public boolean addCategory(CategoryModel category) {
	
		int cat=template.update("insert into Category values('0',?)", new PreparedStatementSetter()
				{
					@Override
					public void setValues(PreparedStatement ps) throws SQLException {
						 
				         ps.setString(1, category.getCname()); 
					}
				});
		return cat>0?true:false;
	}

	@Override
	public List<CategoryModel> getAllCategories() {
	 
		list=template.query("select *from category", new RowMapper<CategoryModel> ()
				{

					@Override
					public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						 
						CategoryModel c=new CategoryModel();
						
						c.setCid(rs.getInt(1));
						c.setCname(rs.getString(2));
						return c;
					}
			
				});
		return list;
	}
	@Override
	public boolean deleteCategoryById(int cid) {
	    int result = template.update("DELETE FROM category WHERE cid = ?", cid);
	    return result > 0;
	}

	@Override
	public boolean updateCategory(CategoryModel category) {
		int result = template.update("UPDATE category SET cname = ? WHERE cid = ?", category.getCname(),
				category.getCid());
		return result > 0;
	}


	@Override
	public List<UserModel> getAllUsers() {

		
		LocalDate currentDate = LocalDate.now(); // Get system date
	    Date sqlDate = Date.valueOf(currentDate);
	    
		list1 = template.query("select * from user", new RowMapper<UserModel>() {

			@Override
			public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {

				UserModel u = new UserModel();

				u.setUid(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setEmail(rs.getString(3));
				u.setPassword(rs.getString(4));
				u.setCreated_date(rs.getDate(5)); // 5th column is created_at

				u.setMobile(rs.getLong(6));
				u.setCity(rs.getString(7));
				u.setPincode(rs.getInt(8));

				return u;
			}
		});
		return list1;
	}
	@Override
	public boolean deleteUser(int uid) {
	    String sql = "DELETE FROM user WHERE uid = ?";
	    int deleted = template.update(sql, uid);
	    return deleted > 0;
	}

	@Override
	public int getTotalCategoryCount() {
		String sql = "SELECT COUNT(*) FROM category";
        return template.queryForObject(sql, Integer.class);
	}

	@Override
	public int getTotalUserCount() {
		 String sql = "SELECT COUNT(*) FROM user";
	        return template.queryForObject(sql, Integer.class);
	}

	@Override
	public int getTotalExpensesCount() {
		String sql="select COUNT(*) from expense";
		return template.queryForObject(sql, Integer.class);
	}

	@Override
	public int getTotalBudgetCount() {
		String sql="select COUNT(*) from budget";
		return template.queryForObject(sql, Integer.class);
	}
	
	 public List<ExpenseCategoryDistribution> getExpenseDistributionByCategory() {
	        String sql = "SELECT c.Cname AS category, SUM(ue.expense_price) AS amount " +
	                     "FROM user_expense ue " +
	                     "JOIN expense e ON ue.eid = e.eid " +
	                     "JOIN category c ON e.cid = c.cid " +
	                     "GROUP BY c.Cname";

	        return template.query(sql, (rs, rowNum) ->
	            new ExpenseCategoryDistribution(
	                rs.getString("category"),
	                rs.getDouble("amount")
	            )
	        );
}

}
