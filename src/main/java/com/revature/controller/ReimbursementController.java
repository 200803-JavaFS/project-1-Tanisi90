package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.models.Reimbursement_Status;
import com.revature.models.Reimbursement_Type;
import com.revature.models.Users;
import com.revature.services.ReimbursementService;
import com.revature.services.UserService;

public class ReimbursementController {

	private static ReimbursementService reimbs = new ReimbursementService();
	private static ObjectMapper objm = new ObjectMapper();
	private static UserService us = new UserService();
	private static ReimbursementDAO rd = new ReimbursementDAO();
	private static UserDAO ud = new UserDAO();
	

	public void getSingleReimbursement(HttpServletResponse resp, int id) throws IOException {
		List<Reimbursement> rl = reimbs.getReimbursementByUser_Id(id);
		if (rl == null) {
			resp.setStatus(204);
		} else {

			List<ReimbursementDTO> rDTOl = new ArrayList<>();
			for (Reimbursement r : rl) {
				ReimbursementDTO rd = new ReimbursementDTO();
				rd.reimb_author = r.getReimb_author().getUsername(); // gets the username associated with the user
																		// object author id
				rd.reimb_amount = r.getReimb_amount(); // gets the reimbursement amount
				rd.reimb_id = r.getReimb_id();
				if (r.getReimb_resolver() != null) {
					rd.reimb_resolver = r.getReimb_resolver().getUsername(); // gets the username associated with the
																				// user who resolved the ticket.
				} else {
					rd.reimb_resolver = null; // have to do this way because it will not allow us to find a field of a
												// null object
				}
				rd.reimb_description = r.getReimb_description();
				rd.reimb_submitted = r.getReimb_submitted().toString(); // have to toString the timestamp so JS can read
																		// it
				if (r.getReimb_resolved() != null) {
					rd.reimb_resolved = r.getReimb_resolved().toString();
				} else {
					rd.reimb_resolved = null;
				}
				rd.reimb_status_id = r.getReimb_status_id().getReimb_status(); // gets my status object and grabs the
																				// pending, approved, denied
				rd.reimb_type_id = r.getReimb_type_id().getReimb_type();
				rDTOl.add(rd);
			}
			resp.getWriter().println(objm.writeValueAsString(rDTOl));
			resp.setStatus(200);
		}
	}

	public void getAllReimbursements(HttpServletResponse resp) throws IOException {
		List<Reimbursement> allr = reimbs.findAll();
		List<ReimbursementDTO> rDTOl = new ArrayList<>();
		for (Reimbursement r : allr) {
			ReimbursementDTO rd = new ReimbursementDTO();
			rd.reimb_author = r.getReimb_author().getUsername(); // gets the username associated with the user object
																	// author id
			rd.reimb_amount = r.getReimb_amount(); // gets the reimbursement amount
			rd.reimb_id = r.getReimb_id();
			if (r.getReimb_resolver() != null) {
				rd.reimb_resolver = r.getReimb_resolver().getUsername(); // gets the username associated with the user
																			// who resolved the ticket.
			} else {
				rd.reimb_resolver = null; // have to do this way because it will not allow us to find a field of a null
											// object
			}
			rd.reimb_description = r.getReimb_description();
			rd.reimb_submitted = r.getReimb_submitted().toString(); // have to toString the timestamp so JS can read it
			if (r.getReimb_resolved() != null) {
				rd.reimb_resolved = r.getReimb_resolved().toString();
			} else {
				rd.reimb_resolved = null;
			}
			rd.reimb_status_id = r.getReimb_status_id().getReimb_status(); // gets my status object and grabs the
																			// pending, approved, denied
			rd.reimb_type_id = r.getReimb_type_id().getReimb_type();
			rDTOl.add(rd);
		}
		resp.getWriter().println(objm.writeValueAsString(rDTOl));
		resp.setStatus(200);
	}

	public void addReimbursementTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		System.out.println("Body: " + body);

		ReimbursementDTO reimb = objm.readValue(body, ReimbursementDTO.class);
		Reimbursement re = new Reimbursement();
		re.setReimb_amount(reimb.reimb_amount);
		re.setReimb_description(reimb.reimb_description);
		re.setReimb_status_id(rd.getByOneStatus(1));
		System.out.println(reimb.reimb_type_id);
		System.out.println("Where am I 1?");
		
		int id = 0;
		if(reimb.reimb_type_id.equals("Travel Exp")) {
		    id= 1;
		}else if (reimb.reimb_type_id.equals("Dining")) {
			id = 2;
		}else if (reimb.reimb_type_id.equals("Lodging")) {
			id = 3;
		}else if (reimb.reimb_type_id.equals("Internet")) {
			id = 4;
		}else if (reimb.reimb_type_id.equals("Certs")) {
			id = 5;
		}else { 			
			id = 6;
		}
		System.out.println("Where am I? 2");
		HttpSession ses = req.getSession(false);
		re.setReimb_author(ud.findById((Integer)ses.getAttribute("user_id")));

		re.setReimb_type_id(rd.getByOneType(id));
		System.out.println("Reimb: " + reimb);

		if (reimbs.addReimbursementTicket(re)) {
			resp.setStatus(201);
			resp.getWriter().println("Reimbursement has been created");
		} else {
			resp.setStatus(403);
		}
	}

	public void ReimbursementsByStatus(HttpServletResponse resp, Reimbursement_Status status) throws IOException {
		List<Reimbursement> allStats = reimbs.ReimbursementsByStatus(status);
		resp.getWriter().println(objm.writeValueAsString(allStats));
		resp.setStatus(200);

	}
	
	

//	public void updateReimbursementTicket(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		BufferedReader reader = req.getReader();
//
//		StringBuilder s = new StringBuilder();
//
//		String line = reader.readLine();
//
//		Users u = (Users) req.getSession().getAttribute("user");
//		while (line != null) {
//			s.append(line);
//			line = reader.readLine();
//		}
//		try {
//		switch (portions[0]) {
//		case "approve": 
//			reimbs.updateReimbursementTicket(rt)(u.getUsers_id(), Integer.parseInt(s.toString()));
//		resp.getWriter().print("You've been approved!");
//		resp.setStatus(201);
//		case "denied":
//			Users user = (Users) req.getSession().getAttribute("user");
//			while (line != null) {
//				s.append(line);
//				line = reader.readLine();
//			}
//		}
//	
//	}

//	public void updateReimbursementStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//		BufferedReader reader = req.getReader();
//		StringBuilder s = new StringBuilder();
//		String line = reader.readLine();
//		while (line != null) {
//			s.append(line);
//			line = reader.readLine();
//		}
//
//		String body = new String(s);
//
//		Reimbursement_Status rs = objm.readValue(body, Reimbursement_Status.class);
//
//		if (reimbs.updateReimbursementStatus(rs)) {
//			resp.setStatus(202);
//			resp.getWriter().println("Reimbursement Status Updated");
//		} else {
//			resp.setStatus(304);
//		}
//	}

}
