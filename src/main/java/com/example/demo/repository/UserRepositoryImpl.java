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
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	JdbcTemplate template;

	List<UserModel> list = new ArrayList<UserModel>();
	List<CategoryModel> list1 = new ArrayList<CategoryModel>();

	@Override
	public boolean addUser(UserModel user) {

		LocalDate currentDate = LocalDate.now(); // Get system date
		Date sqlDate = Date.valueOf(currentDate);

		int add = template.update("insert into User values('0',?,?,?,?,?,?,?)", new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1, user.getUname());
				ps.setString(2, user.getEmail());
				ps.setString(3, user.getPassword());
				ps.setDate(4, sqlDate); // Set current system date
				Long mobile = user.getMobile();
				if (mobile != null) {
				    ps.setLong(5, mobile);
				} else {
				    ps.setNull(5, java.sql.Types.BIGINT);
				}

				ps.setString(6, user.getCity());
				ps.setInt(7, user.getPincode());
			}
		});
		return add > 0 ? true : false;
	}

	public boolean updateUser(UserModel user, int uid) {
		String sql = "UPDATE user SET uname = ?, email = ?, mobile_no = ?, city = ?, pincode = ? WHERE uid = ?";

		int updateCount = template.update(sql, user.getUname(), user.getEmail(), user.getMobile(),
				user.getCity(), user.getPincode(), uid);

		return updateCount > 0; 
	}

	@Override
	public List<UserModel> searchUser(String pattern, UserModel user) {

		list = template.query(
				"select * from user WHERE uname like '%" + pattern + "%' OR email like '%" + pattern + "%'",
				new RowMapper<UserModel>() {

					@Override
					public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
						UserModel u = new UserModel();
						u.setUname(rs.getString(2));
						u.setEmail(rs.getString(3));
						return u;
					}
				});
		return list;
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		list1 = template.query("select *from category", new RowMapper<CategoryModel>() {
			@Override
			public CategoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {

				CategoryModel c = new CategoryModel();

				c.setCname(rs.getString(2));
				return c;
			}
		});
		System.out.println(list1.size() + "---------------------");
		return list1;
	}

//	@Override
//	public boolean loginUser(String email, String password) {
//		String sql = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
//		Integer count = template.queryForObject(sql, new Object[] { email, password }, Integer.class);
//		return count != null && count > 0;
//	}

	public UserModel getUserById(int uid) {
		String query = "SELECT * FROM user WHERE uid = ?";

		return template.queryForObject(query, new Object[] { uid }, new RowMapper<UserModel>() {

			@Override
			public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserModel user = new UserModel();
				user.setUid(rs.getInt("uid"));
				user.setUname(rs.getString("uname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setCreated_date(rs.getDate("created_at"));
				user.setMobile(rs.getLong("mobile_no"));
				user.setCity(rs.getString("city"));
				user.setPincode(rs.getInt("pincode"));
				return user;
			}
		});
	}

	public UserModel getUserByEmailAndPassword(String email, String password) {
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";
		try {
			return template.queryForObject(query, new Object[] { email, password }, new RowMapper<UserModel>() {
				@Override
				public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
					UserModel user = new UserModel();
					user.setUid(rs.getInt("uid"));
					user.setUname(rs.getString("uname"));
					user.setEmail(rs.getString("email"));
					user.setPassword(rs.getString("password"));
					user.setCreated_date(rs.getDate("created_at"));
					user.setMobile(rs.getLong("mobile_no"));
					user.setCity(rs.getString("city"));
					user.setPincode(rs.getInt("pincode"));
					return user;
				}
			});
		} catch (Exception e) {
			return null; // Return null if login fails
		}
	}

	@Override
	public boolean resetPassword(String email, String newPassword) {
		int result = template.update("UPDATE user SET password = ? WHERE email = ?", newPassword, email);
		return result > 0;
	}

	@Override
	public boolean loginUser(String email, String password) {
		// TODO Auto-generated method stub
		List value=template.query("select * from user WHERE email=? and password=?", new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {

				ps.setString(1, email);
				ps.setString(2, password);
			}
		}, new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {

				return null;
			}
		});
		return value.size()>0?true:false;
	}

}