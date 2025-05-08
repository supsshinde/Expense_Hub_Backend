package com.example.demo.service;
import java.util.List;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;

public interface UserService {

	public boolean addUser(UserModel user);
	public boolean updateUser(UserModel user, int uid);
	public List<UserModel> searchUser(String pattern, UserModel user);
	public List<CategoryModel> getAllCategories();

	public boolean loginUser(String email, String password);
	public UserModel getUserById(int uid);
	public UserModel getUserByEmailAndPassword(String email, String password);
	public boolean resetPassword(String email, String newPassword);

}
