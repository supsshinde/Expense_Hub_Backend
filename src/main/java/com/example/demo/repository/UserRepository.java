package com.example.demo.repository;
import java.util.List;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;

public interface UserRepository {
	public boolean addUser(UserModel user);
	public boolean updateUser(UserModel user, int uid);
	public List<UserModel> searchUser(String pattern, UserModel user);
	public List<CategoryModel> getAllCategories();

}
