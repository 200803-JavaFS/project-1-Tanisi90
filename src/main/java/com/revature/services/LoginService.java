package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;

public class LoginService {
	
		UserDAO uDAO = new UserDAO();
		public boolean login(LoginDTO l){
			String username = l.username;
			Integer hashedpass = l.password.hashCode();
			String password = hashedpass.toString();
			if(uDAO.selectByLICred(username,password)) {
				return true;
			}
			return false;
		}
	}


