package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.User_Roles;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;
import com.revature.utils.HibernateUtil;

public class UserDAO implements IUserDAO {

	@Override
	public List<Users> findAll() {
		Session ses = HibernateUtil.getSession();

		List<Users> list = ses.createQuery("FROM Users").list();

		return list;
	}

	@Override
	public Users findById(int id) {
		Session ses = HibernateUtil.getSession();

		Users u = ses.get(Users.class, id);
		return u;
	}

	@Override
	public boolean addUser(Users u) {
		Session ses = HibernateUtil.getSession();
		
		Transaction trans = ses.beginTransaction();
		try {
			ses.save(u);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			HibernateUtil.closeSes();
		}
	}

	@Override
	public boolean updateUser(Users u) {
		Session ses = HibernateUtil.getSession();
		
		Transaction trans = ses.beginTransaction();

		try {
			ses.merge(u);
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
	public boolean deleteUser(Users du) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.delete(du);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean selectByLICred(String username, String password) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.createQuery("FROM User WHERE username ='" + username + "' AND password='" + password + ";");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Users getByUsername(String username) {
		Session ses = HibernateUtil.getSession();
		Users u = ses.get(Users.class, username);
		return u;
	}
	
	@Override
	public User_Roles addUserRole(User_Roles userR) {
		Session ses = HibernateUtil.getSession();
		User_Roles ur = ses.get(User_Roles.class, userR);
		return ur;
	}
	
	@Override
	public User_Roles getUserRoleID(int id) {
		Session ses = HibernateUtil.getSession();
		User_Roles ur = ses.get(User_Roles.class, id);
		return ur;
	}
	
}





