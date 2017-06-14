package com.popiang.opc.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.popiang.opc.dao.Lead;
import com.popiang.opc.dao.User;
import com.popiang.opc.service.UsersService;
import com.popiang.opc.utilities.Concatenation;

/*
 * This class handles displaying home page of the website
 * based on the role of the logged in account 
 */

@Controller
public class HomeController 
{
	//linking to UsersService bean
	@Autowired
	private UsersService usersService;
	
	//linking to Concatenation bean
	@Autowired
	private Concatenation concatenate;
	
	//
	//this method display to home page
	//
	/*@RequestMapping(value={"/", "/favicon.ico"})*/
	@RequestMapping("/")
	public String showHome(Principal principal, Model model)
	{
		String username = principal.getName();
		String event = usersService.getEventName(username); 
		
		model.addAttribute("event", concatenate.removeUnderscore(event));

		//
		//this lead object is used if the account logged in is user account
		//and the user decides to add new lead
		//
		model.addAttribute("lead", new Lead());
		
		//redirect to home page of this website
		return "home";
	}
	
	//
	//this method shows the login page or
	//create-admin-account page
	//
	@RequestMapping("/loginpage")
	public String showLogin(Model model)
	{
		List<User> users = usersService.getAllCurrentUsers();
		
		//
		//if no users account exist, meaning no admin account yet,
		//admin account has to be created first
		//
		if(users.size() == 0)
		{
			User user = new User();
			
			//role of the account set to be ROLE_ADMIN
			user.setAuthority("ROLE_ADMIN");
			user.setEnabled(true);
			user.setEvent("admin");
			
			model.addAttribute("user", user);
			
			//redirect to create-admin-account page
			return "createadmin";
		}
		else//if admin account exist
		{
			//redirect to login page
			return "login";
		}
	}
}



