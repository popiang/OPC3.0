package com.popiang.opc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.popiang.opc.dao.Lead;
import com.popiang.opc.dao.User;
import com.popiang.opc.service.LeadsService;
import com.popiang.opc.service.UsersService;
import com.popiang.opc.utilities.EmailSender;
import com.popiang.opc.utilities.ExcelCreator;

/*
 * this class handles:
 * 1. retrieving leads to be exported
 * 2. converting the data into excel format
 * 3. sending the excel file via email to admin email account
 */

@Controller
public class ExportController 
{
	//linking to LeadsService bean
	@Autowired
	private LeadsService leadsService;
	
	//linking to ExcelCreator bean
	@Autowired
	private ExcelCreator excelCreator;
	
	//linking to EmailSender bean
	@Autowired
	private EmailSender emailSender;
	
	//linking to UsersService bean
	@Autowired
	private UsersService usersService;
	
	//
	//this method retrieves to be exported leads, convert it to excel format and email it
	//
	@RequestMapping("/exportleads")
	public String exportLeads(HttpServletRequest context, @RequestParam(value="event") String event, Model model)
	{
		//retrieves leads base on event name
		List<Lead> leads = leadsService.getAllLeads(event);
		
		//
		//convert retrieved leads to excel format using method from ExcelCreator class
		//and save it in certain filePath
		//
		String filePath = excelCreator.createExcel(leads, context, event);

		//sends the created excel file using method from EmailSender class to admin email account
		emailSender.emailFile(context, event, filePath);
		
		List<User> users = usersService.getAllCurrentUsers();
		
		model.addAttribute("users", users);
		model.addAttribute("action", "export");
		
		//redirect to admin page to indicate export successfull 
		return "adminpage";
	}
}
