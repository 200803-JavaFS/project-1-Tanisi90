package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface IReimbursementDAO {
	
			public List<Reimbursement> findAll();
			
			public Reimbursement findById(int id);
			
			public boolean addReimbursementTicket(Reimbursement rt);
			
			public boolean updateReimbursementTicket(Reimbursement rt); 
			
			public boolean deleteReimbursementTicket(Reimbursement drt);
}

