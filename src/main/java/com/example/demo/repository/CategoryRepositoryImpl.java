package com.example.demo.repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.example.demo.model.CategoryModel;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

	@Autowired
	JdbcTemplate template;
	
	List<CategoryModel> list=new ArrayList<CategoryModel>();

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

//
//	@Override
//	public boolean deleteCategory(int did) {
//		
//		int del=template.update("delete from category where cid=?", new PreparedStatementSetter() {
//			
//			@Override
//			public void setValues(PreparedStatement ps) throws SQLException {
//				ps.setInt(1, did);
//			}
//		});
//		return del>0?true:false;
//	}
//
//	
}
