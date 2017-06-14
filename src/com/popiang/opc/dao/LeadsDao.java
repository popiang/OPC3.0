package com.popiang.opc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * this class handles:
 * 1. retrieving leads from database
 * 2. saving lead into database
 * 3. deleting lead from database
 * --hibernate is used in this class to handle database operations-- 
 */

@Repository
public class LeadsDao 
{
	//linking to SessionFactory bean to use hibernate in this class
	@Autowired
	private SessionFactory factory;
	
	//this method retrieves leads from database base on event name
	public List<Lead> getAllLeads(String event) 
	{
		Session session = factory.getCurrentSession();
		
		List<Lead> leads = session.createQuery("from Lead where eventName=:eventName", Lead.class).setParameter("eventName", event).getResultList();
		
		return leads;
	}

	//this method retrieves lead from database base on lead id
	public Lead getLead(Integer id) 
	{
		Session session = factory.getCurrentSession();
		
		Lead lead = session.get(Lead.class, id);
		
		return lead;
	}

	//this method saves or updates lead into database
	public void saveLead(Lead lead) 
	{
		Session session = factory.getCurrentSession();
		
		session.saveOrUpdate(lead);
	}

	//this method deletes lead from database base on lead id
	public void deletelead(int id) 
	{
		Session session = factory.getCurrentSession();
		
		session.createQuery("delete from Lead where id=:id").setParameter("id", id).executeUpdate();
	}
}








