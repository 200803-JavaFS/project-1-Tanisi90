package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement_Status;
import com.revature.services.ReimbursementService;

public class ReimbursementController {

	private static ReimbursementService reimbs = new ReimbursementService();
	private static ObjectMapper objm = new ObjectMapper();

	public void getSingleReimbursement(HttpServletResponse resp, int id) throws IOException {
		Reimbursement r = reimbs.findById(id);
		if (r == null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(200);
			String json = objm.writeValueAsString(r);
			resp.getWriter().println(json);
		}
	}

	public void getAllReimbursements(HttpServletResponse resp) throws IOException {
		List<Reimbursement> allr = reimbs.findAll();
		resp.getWriter().println(objm.writeValueAsString(allr));
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

		Reimbursement reimb = objm.readValue(body, Reimbursement.class);

		System.out.println("Reimb: " + reimb);

		if (reimbs.addReimbursementTicket(reimb)) {
			resp.setStatus(201);
			resp.getWriter().println("Reimbursement has been created");
		} else {
			resp.setStatus(403);
		}
	}
	
	public void ReimbursementsByStatus(HttpServletResponse resp, Reimbursement_Status status) throws IOException{
		List<Reimbursement> allStats = reimbs.ReimbursementsByStatus(status);
		resp.getWriter().println(objm.writeValueAsString(allStats));
		resp.setStatus(200);
		
	}
	
	public void updateReimbursementStatus(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		BufferedReader reader= req.getReader();
		StringBuilder s=new StringBuilder();
		String line = reader.readLine();
		while(line!=null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		
	}
}

