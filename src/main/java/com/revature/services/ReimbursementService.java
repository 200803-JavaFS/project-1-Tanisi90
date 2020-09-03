package com.revature.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.IReimbursementDAO;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement_Status;


public class ReimbursementService {
	private static final Logger log = LogManager.getLogger(ReimbursementService.class);
	
	private static IReimbursementDAO rDAO = new ReimbursementDAO();
	
	public List<Reimbursement> findAll(){
		log.info("Retrieving list of Reimbursements");
		return rDAO.findAll();
	}
	
	public Reimbursement findById(int id) {
		log.info("Find Reimbursement by ID" );
		return rDAO.findById(id);
	}
	
	public boolean addReimbursementTicket(Reimbursement rt) {
		log.info("Add a Reimbursement Ticket" );
		return rDAO.addReimbursementTicket(rt);
	}
	
	public boolean updateReimbursementTicket(Reimbursement rt) {
		log.info("Update a Reimbursement Ticket" );
		return rDAO.updateReimbursementTicket(rt);
	}
	
	public boolean deleteReimbursementTicket(Reimbursement drt) {
		log.info("Delete a Reimbursement Ticket");
		return rDAO.deleteReimbursementTicket(drt);
	}
	
	public List<Reimbursement> ReimbursementsByStatus(Reimbursement_Status status){
		log.info("Retrieving Reimbursements By Status");
		return rDAO.ReimbursementsByStatus(status);
	}
	
	public Reimbursement_Status updateReimbursementStatus(Reimbursement_Status status) {
		log.info("Setting A Reimbursement Status Status");
		return rDAO.updateReimbursementStatus(status);
	}
	
	
}
