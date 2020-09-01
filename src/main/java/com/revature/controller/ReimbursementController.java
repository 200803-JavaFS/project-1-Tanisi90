package com.revature.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementDTO;
import com.revature.services.ReimbursementService;

public class ReimbursementController {
	
	private static ReimbursementService reimbs = new ReimbursementService();
	private static ObjectMapper objm = new ObjectMapper();
	
	public void getSingleReimbursement(HttpServletResponse resp, int id) throws IOException{
		Reimbursement r = reimbs.findById(id);
		if(r == null) {
			resp.setStatus(204);
		} else {
			resp.setStatus(200);
			String json = objm.writeValueAsString(r);
			resp.getWriter().println(json);
		}
	}
	public void getAllReimbursements(HttpServletRequest req, HttpServletResponse resp)throws IOException{
		List<Reimbursement> allr = reimbs.findAll();
		resp.getWriter().println(objm.writeValueAsString(allr));
		resp.setStatus(200);
	}
	
	public void addReimbursement(HttpServletRequest req, HttpServletResponse resp)throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		System.out.println(body);
		
		ReimbursementDTO rdto = objm.readValue(body, ReimbursementDTO.class);
		
		System.out.println(rdto);
		
		if(reimbs.addReimbursementTicket(rt)) {
			resp.setStatus(201);
			resp.getWriter().println("Reimbursement Added!");
		}else {
			resp.setStatus(403);
			
		}
	}
}
