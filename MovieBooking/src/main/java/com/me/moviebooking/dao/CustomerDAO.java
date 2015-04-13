package com.me.moviebooking.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.me.moviebooking.model.Customer;


public class CustomerDAO extends DAO {
	public Customer isValidCustomer(String userName, String password)
			throws Exception {
		Query q;
		try {
			q = getSession()
					.createQuery(
							"from Customer where user_name = :name and password = :password");
			q.setString("name", userName);
			q.setString("password", password);
			Customer customer = (Customer) q.uniqueResult();
			return customer;
		} catch (HibernateException e) {
			throw new Exception("Could execute query " + userName, e);
		}
	}
	public boolean createCustomer(Customer customer){
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	        session.save(customer);
	        session.getTransaction().commit();
	        HibernateUtil.shutdown();
	        return true;
		} catch (HibernateException e) {
			System.out.println("Could execute query " + e);
		}
		return false;
	}
}
