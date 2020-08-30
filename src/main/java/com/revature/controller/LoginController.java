package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.LoginDTO;
import com.revature.services.LoginService;

public class LoginController {

	private static LoginService ls = new LoginService();
	private static ObjectMapper om = new ObjectMapper();

	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException {

		if (req.getMethod().equals("POST")) {
			// this is how a login should generally be handled. Sending credentials in the
			// body of a POST request.
			BufferedReader reader = req.getReader();

			StringBuilder sb = new StringBuilder();

			String line = reader.readLine();

			while (line != null) {
				sb.append(line);
				line = reader.readLine();
			}

			String body = new String(sb);

			LoginDTO l = om.readValue(body, LoginDTO.class);

			if (ls.login(l)) {
				HttpSession ses = req.getSession();
				ses.setAttribute("user", l);
				ses.setAttribute("loggedin", true);
				res.setStatus(200);
				res.getWriter().println("Login Successful");
			} else {
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login Unsuccessful");
			}
		}
	}

	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		HttpSession ses = req.getSession(false);

		if (ses != null) {
			LoginDTO l = (LoginDTO) ses.getAttribute("user");
			ses.invalidate();
			resp.setStatus(200);
			resp.getWriter().println(l.username + " has logged out successfully");
		} else {
			resp.setStatus(400);
			resp.getWriter().println("Please login!");
		}
	}

}

