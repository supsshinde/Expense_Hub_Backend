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
import com.example.demo.model.UserModel;

@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	JdbcTemplate template;
	
	List<UserModel> list=new ArrayList<UserModel>();
	List<CategoryModel> list1=new ArrayList<CategoryModel>();
	
	@Override
	public boolean addUser(UserModel user) {
		
		 LocalDate currentDate = LocalDate.now(); // Get system date
		    Date sqlDate = Date.valueOf(currentDate); 
		    
		int add=template.update("insert into User values(0,?,?,?,?,?,?,?)", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, user.getUname());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
				ps.setDate(4, sqlDate); // Set current system date
				ps.setLong(5, user.getMobile());
				ps.setString(6, user.getCity());
				ps.setInt(7, user.getPincode());
			}
		});
		return add>0?true:false;
	}

	@Override
	public boolean updateUser(UserModel user, int uid) {
		int update=template.update("UPDATE user SET uname=?, email=?, password=?, mobile_no=?, city=?, pincode=? WHERE uid=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				 ps.setString(1, user.getUname()); 
		         ps.setString(2, user.getEmail());   
		         ps.setString(3, user.getPassword());
		         ps.setLong(4, user.getMobile());
		         ps.setString(5, user.getCity());
		         ps.setInt(6, user.getPincode());
		         ps.setInt(7, uid);
			}
		});
		return update>0?true:false;
	}

	@Override
	public List<UserModel> searchUser(String pattern, UserModel user) {
		
		list=template.query("select * from user WHERE uname like '%"+pattern+"%' OR email like '%"+pattern+"%'", new RowMapper<UserModel> ()
				{

					@Override
					public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserModel u=new UserModel();
						u.setUname(rs.getString(2));
						u.setEmail(rs.getString(3));
						return u;
					}
				});
		return list;
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		list1 =template.query("select *from category", new RowMapper<CategoryModel> ()
		{
			@Override
			public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				 
				CategoryModel c=new CategoryModel();
				
				c.setCname(rs.getString(2));
				return c;
			}
		});
		System.out.println(list1.size() + "---------------------");
return list1;
	}
}
