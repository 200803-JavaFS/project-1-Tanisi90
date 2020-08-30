package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.revature.models.Reimbursement;
import com.revature.utils.HibernateUtil;

public class ReimbursementDAO implements IReimbursementDAO {
	
	@Override
	public List<Reimbursement> findAll() {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement").list();
		
		return list;
	}
	
	@Override
	public Reimbursement findById(int id) {
		Session ses = HibernateUtil.getSession();
		
		Reimbursement rt = ses.get(Reimbursement.class, id);
		return rt;
	}
	
	@Override
	public boolean addReimbursementTicket(Reimbursement rt) {
		Session ses = HibernateUtil.getSession();
		try {
		ses.save(rt);
		
		return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateReimbursementTicket(Reimbursement rt) {
		Session ses = HibernateUtil.getSession();

		try {
			ses.merge(rt);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteReimbursementTicket(Reimbursement drt) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.delete(drt);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}



