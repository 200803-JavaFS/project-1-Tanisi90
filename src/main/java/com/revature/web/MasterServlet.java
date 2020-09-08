package com.revature.web;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.revature.controller.LoginController;
import com.revature.controller.ReimbursementController;
import com.revature.controller.UserController;
import com.revature.models.LoginDTO;
import com.revature.models.User_Roles;
import com.revature.models.Users;
import com.revature.services.UserService;

public class MasterServlet extends HttpServlet {
	
	private static UserController userc = new UserController();
	private static ReimbursementController  reimbc = new ReimbursementController();
	private static LoginController lc = new LoginController();
	private static UserService userv = new UserService();

	
	public MasterServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setStatus(404);
		
		final String URI = req.getRequestURI().replace("/Project1/", "");
		
		String[] portions = URI.split("/");

		System.out.println(Arrays.toString(portions));

		
		try {
			switch (portions[0]) {
			case "Login":
				lc.login(req, resp);
				break;
			case "Success": 
				System.out.println(req.getSession(false).getAttribute("user"));
					if (req.getSession(false) != null && (boolean) req.getSession().getAttribute("loggedin")) {
						LoginDTO lDTO = (LoginDTO) req.getSession().getAttribute("user");
						
						System.out.println(" Username: "+  lDTO);
						
						Users u = userv.getByUsername(lDTO.username);
						User_Roles ur = u.getUser_role_id();	
						System.out.println(ur);
						if(req.getMethod().equals("GET")) {
							userc.setUser_Role(req, resp, u);
						}
					}
					break;
			case "Reimbursements":
				if (req.getMethod().equals("GET")) {
					if (portions.length == 2) {
						int id = Integer.parseInt(portions[1]);
						reimbc.getSingleReimbursement(resp, id);
					}else if (portions.length == 1){
						reimbc.getAllReimbursements(resp);
					}
				} else if(req.getMethod().equals("POST")) {
					reimbc.addReimbursementTicket(req, resp);
				}
				break;
			
			case "logout":
				lc.logout(req, resp);
				break;
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
			resp.getWriter().println("Please provide a valid ID number!");
			resp.setStatus(400);
		}
		
	}
	
//	private void updateReimbursement(String[] portions, HttpServletRequest req, HttpServletResponse resp)
//			throws IOException {
//		switch (portions[2]) {
//		case "approve":
//			reimbc.updateReimbursementTicket(req, resp);
//			break;
//		case "denied":
//			reimbc.updateReimbursementTicket(req, resp);
//			break;
//		}
//	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
	

}
