package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursement;
import com.revature.models.Reimbursement_Status;
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
		
		Transaction trans = ses.beginTransaction();

		try {
			ses.merge(rt);
			trans.commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}finally{
			HibernateUtil.closeSes();
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
	
	@Override
	public List<Reimbursement> ReimbursementsByStatus(Reimbursement_Status status) {
		Session ses = HibernateUtil.getSession();
		
		List<Reimbursement> list = ses.createQuery("FROM Reimbursement WHERE reimb_status_id = "+ status.getReimb_status_id(), Reimbursement.class).list();
		
		return list;
	}
	
}






