package com.revature;

import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.User_Roles;
import com.revature.models.Users;
import com.revature.services.UserService;

public interface Driver {
	
	//Testing hash code for password
	
	public static IUserDAO uDAO = new UserDAO();
	public static UserService us = new UserService();

	
	public static void main (String args[]) {
		addUser();

	}
	
	public static void addUser() {
		String attempt1 = "willthiswork?";
		StringBuilder sb = new StringBuilder();
		sb.append(attempt1.hashCode());
		String hashattempt = sb.toString();
		
		String attempt2 = "pleasework!";
		StringBuilder sb1 = new StringBuilder();
		sb1.append(attempt2.hashCode());
		String hashattempt1 = sb1.toString();
		
		String attempt3 = "hahaIwin";
		StringBuilder sb2 = new StringBuilder();
		sb2.append(attempt3.hashCode());
		String hashattempt2 = sb2.toString();
		
		User_Roles ur = uDAO.getUserRoleID(1);
		User_Roles ur2 = uDAO.getUserRoleID(2);
		
		Users u = new Users("Tanisi", hashattempt, "Brittany", "Epstein", "GodWork@gmail.com", ur2);
		Users u2 = new Users("Je_Jolie", hashattempt1,"Adam", "Cotten", "NoReallyWork@aol.com", ur);
		Users u3 = new Users("DaughterOfLilith", hashattempt2, "Arlene", "Vanderbelt", "Pleeaaassseee@hotmail.com", ur);
		
		uDAO.addUser(u);
		uDAO.addUser(u2);
		uDAO.addUser(u3);
	}

}
