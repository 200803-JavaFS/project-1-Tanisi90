package com.revature.daos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

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
		try {
			ses.save(u);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateUser(Users u) {
		Session ses = HibernateUtil.getSession();

		try {
			ses.merge(u);
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteUser(Users u) {
		Session ses = HibernateUtil.getSession();
		try {
			ses.delete(u);
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

}



