package com.revature.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import com.revature.services.UserService;

public class UserController {
	
	private static ObjectMapper objm = new ObjectMapper();
	private static UserService us = new UserService();

	
	public void setUser_Role(HttpServletRequest req, HttpServletResponse resp, Users u) throws IOException {;
	
		if (u ==null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(200);
			String json = objm.writeValueAsString(u.getUser_role_id().getuser_role_id());
			resp.getWriter().println(json);
		}
	}
}
