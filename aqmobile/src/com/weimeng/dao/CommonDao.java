package com.weimeng.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CommonDao {
   
	   @Autowired
	   private SessionFactory sessionFactory;
	   
	   public Session getSession() {
		   return sessionFactory.openSession();
	   }
	   
	   public Session getCurrentSession() {
		   return sessionFactory.getCurrentSession();
	   }
}
