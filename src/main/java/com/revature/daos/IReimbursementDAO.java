package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement_Status;

public interface IReimbursementDAO {
	
			public List<Reimbursement> findAll();
			
			public Reimbursement findById(int id);
			
			public boolean addReimbursementTicket(Reimbursement rt);
			
			public boolean updateReimbursementTicket(Reimbursement rt); 
			
			public boolean deleteReimbursementTicket(Reimbursement drt);

			public List<Reimbursement> ReimbursementsByStatus(Reimbursement_Status status);
}

