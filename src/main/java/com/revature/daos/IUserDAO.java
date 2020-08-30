package com.revature.daos;

import java.util.List;

import com.revature.models.Users;

public interface IUserDAO {
	
	public List<Users> findAll();
	
	public Users findById(int id);
	
	public boolean addUser(Users u);
	
	public boolean updateUser(Users u); 
	
	public boolean deleteUser(Users u);
	
	public Users selectByLICred(String username, String password);
}


