package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Users;

public class UserService {
	
	private static final Logger log = LogManager.getLogger(Users.class);
	
	private static final IUserDAO uDAO = new UserDAO();
	
	public List<Users> findAll() {
		log.info("Retrieving list of Users");
		return uDAO.findAll();
	}
	
	public Users findById(int id) {
		log.info("Find User by ID");
		return uDAO.findById(id);
	}
	
	public boolean addUser(Users u) {
		log.info("Add a User");
		return uDAO.addUser(u);
	}
	
	public boolean updateUser(Users u) {
		log.info("Update a User");
		return uDAO.updateUser(u);
	}
	
	public boolean deleteUser(Users u) {
		log.info("Delete User");
		return uDAO.deleteUser(u);
	}
	
	public Users selectByLICred(String username, String password) {
		log.info("Get User Credentials");
		return uDAO.selectByLICred(username, password);
	}

}
