package com.revature.services;


import com.revature.daos.IUserDAO;
import com.revature.daos.UserDAO;
import com.revature.models.LoginDTO;
import com.revature.models.Users;

public class LoginService {
	
	private static IUserDAO udao = new UserDAO();

	public boolean login(LoginDTO l) {
		try {
			String username = l.username;
			String password = l.password;
			Users u = udao.getByUsername(username);

			if (u != null) {
				StringBuilder stb = new StringBuilder(password.hashCode());
				String hashedpass = stb.toString();

				if (u.getpassword().equals(hashedpass) && udao.selectByLICred(username, hashedpass)) {
					return true;
				} else {
					System.out.println("Unable to find Username or Password. " + "Please try again!");
				}
			} else {
				System.out.println("Login failed try again.");
			}
		} catch (NullPointerException e) {
			System.out.println("Unable to find Username or Password.");
			e.printStackTrace();
		}
		return false;
	}

}


