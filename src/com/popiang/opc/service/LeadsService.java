package com.popiang.opc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.popiang.opc.dao.Lead;
import com.popiang.opc.dao.LeadsDao;

/*
 * this class act as a layer between controller and leadsdao class
 */

@Repository
public class LeadsService 
{
	//linking to LeadsDao bean
	@Autowired
	private LeadsDao leadsDao;

	//this method retrieves leads base on event name
	@Transactional
	public List<Lead> getAllLeads(String event) 
	{
		return leadsDao.getAllLeads(event);
	}

	//this method retrieves lead base on lead id
	@Transactional
	public Lead getLead(Integer id) 
	{
		return leadsDao.getLead(id);
	}

	//this method saves or updates the lead
	@Transactional
	public void saveLead(Lead lead) 
	{
		leadsDao.saveLead(lead);
	}

	//this method delete a lead base on lead id
	@Transactional
	public void deletelead(int id) 
	{
		leadsDao.deletelead(id);
	}
}
