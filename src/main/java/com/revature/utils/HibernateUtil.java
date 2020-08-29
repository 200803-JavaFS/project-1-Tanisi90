package com.revature.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration; // other one is security

public class HibernateUtil {
	
	private static Session ses;
	private static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	// we set our fields to static so that it has to go through the methods below to access the session
	public static Session getSession() {
		if(ses == null) {
			ses = sf.openSession(); //this will open a session which allows it to have connections
		}
		return ses;
	}
	
	public static void closeSes() {
		ses.close(); // ends our session
		ses = null;
	}

}