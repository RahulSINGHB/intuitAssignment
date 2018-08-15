package com.intuit.dao;

import com.intuit.model.User;

public interface UserDao {

	Boolean findByUsername(String userName);

	User saveUser(User userEntity);

	User loginUser(String userName, String password);

	
	
}