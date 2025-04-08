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
				u.setCreated_date(sqlDate);
				u.setMobile(rs.getLong(6));
				u.setCity(rs.getString(7));
				u.setPincode(rs.getInt(8));

				return u;
			}
		});
		return list1;
	}
}
