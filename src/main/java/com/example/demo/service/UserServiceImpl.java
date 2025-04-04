package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CategoryModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public boolean addUser(UserModel user) {

		return userRepository.addUser(user);
	}

	@Override
	public boolean updateUser(UserModel user, int uid) {
		return userRepository.updateUser(user, uid);
	}
	
	@Override
	public List<UserModel> searchUser(String pattern, UserModel user) {
		return userRepository.searchUser(pattern, user);
	}

	@Override
	public List<CategoryModel> getAllCategories() {
		return userRepository.getAllCategories();
	}
}
