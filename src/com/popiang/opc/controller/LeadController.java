package com.popiang.opc.controller;
 
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.popiang.opc.dao.Lead;
import com.popiang.opc.service.LeadsService;
import com.popiang.opc.service.UsersService;

/*
 * this class handles:
 * 1. displaying all leads
 * 2. adding new lead
 * 3. editing existing lead
 * 4. deleting lead 
 */

@Controller
public class LeadController 
{
	//linking to LeadsService bean
	@Autowired
	private LeadsService leadsService;
	
	//linking to UsersService bean
	@Autowired
	private UsersService usersService;
	
	//
	//this method is to trim down string to eliminate empty spaces before 
	//and after the string in form input
	//
	@InitBinder
	public void initBinder(WebDataBinder dataBinder)
	{
		StringTrimmerEditor trimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, trimmerEditor);
	}
	
	//
	//this method displays all the leads
	//based on the event name
	//
	@RequestMapping("/allleads")
	public String showAllLeads(Model model, Principal principal)
	{
		String username = principal.getName();
		String event = usersService.getEventName(username); 
		
		//retrieve all leads from database base on event name
		List<Lead> leads = leadsService.getAllLeads(event);
		
		model.addAttribute("leads", leads);
		model.addAttribute("event", event);
		
		//redirect to display-all-leads page
		return "leads";
	}
	
	//
	//this method display lead-form to create new lead
	//or to edit existing lead
	//
	@RequestMapping("/leadform")
	public String showLeadForm(Lead lead, Model model, Principal principal,@RequestParam(name="Id", required=false) Integer Id)
	{
		String username = principal.getName();
		String event = usersService.getEventName(username); 

		model.addAttribute("event", event);
		
		//meaning to create new lead
		if(Id == null)
		{
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();

			lead.setDate(dateFormat.format(date));
			
			lead.setEventName(event);
			
			model.addAttribute("lead", lead);
		}
		else//meaning to edit existing lead
		{
			//retrieved lead base on lead id
			lead = leadsService.getLead(Id);
			model.addAttribute("lead", lead);
		}
		
		//redirect to create-lead-form page
		return "leadform";
	}
	
	//
	//this method process adding or updating lead into database
	//
	@RequestMapping("/newlead")
	public String processNewLead(@Valid Lead lead, BindingResult results)
	{
		//
		//if validation fails user will be redirected 
		//to create-lead-form page to re-enter lead's info
		//
		if(results.hasErrors())
		{
			//redirected to create-lead-form page
			return "leadform";
		}
		
		//if validation successfull, will save or update lead into database
		leadsService.saveLead(lead);
		
		//redirected to lead-save-success page
		return "leadsubmitted";
	}
	
	//
	//this method process deleting lead
	//
	@RequestMapping("/deletelead")
	public String processDelete(@RequestParam(value="Id") int id, Principal principal, Model model)
	{
		String username = principal.getName();
		String event = usersService.getEventName(username);
		
		//lead is deleted base on id
		leadsService.deletelead(id);
				
		List<Lead> leads = leadsService.getAllLeads(event);
		
		model.addAttribute("leads", leads);
		model.addAttribute("event", event);
		model.addAttribute("action", "delete");
		
		//redirect to all-leads page
		return "leads";		
	}
}
