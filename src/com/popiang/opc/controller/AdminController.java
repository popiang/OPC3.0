package com.popiang.opc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.popiang.opc.dao.Lead;
import com.popiang.opc.dao.User;
import com.popiang.opc.service.LeadsService;
import com.popiang.opc.service.UsersService;

/*
 * This controller class handles:
 * 1. displaying admin page 
 * 2. creating and editing admin and user account 
 * 3. deleting user
 * 
 */

@Controller
public class AdminController 
{
	//linking to UsersService bean
	@Autowired
	private UsersService usersService;
	
	//linking to LeadsService bean
	@Autowired
	private LeadsService leadsService;
	
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
	//this method shows the admin page and list down 
	//all the existing users
	//
	@RequestMapping("/adminpage")
	public String showAdmin(Model model)
	{
		List<User> users = usersService.getAllCurrentUsers();
				
		model.addAttribute("users", users);
		
		return "adminpage";
	}
	
	//
	//this method creates admin account during the first time 
	//the web runs
	//
	@RequestMapping("createadmin")
	public String createAdminAccount(@Valid User user, BindingResult results)
	{
		if(results.hasErrors())
		{			
			return "createadmin";
		}
		
		usersService.saveUser(user);
		
		return "login";
	}
	
	//
	//this method directs to the create user form page
	//
	@RequestMapping("/createuserform")
	public String createUserForm(Model model)
	{
		model.addAttribute("user", new User());
		
		return "createuserpage";
	}
	
	//
	//this methods creates or updates user using the information
	//from create user form
	//
	@RequestMapping("/createuser")
	public String createUser(@Valid User user, BindingResult results, Model model, 
			@RequestParam(value="save_edit", required=false) String save_edit,
			@RequestParam(value="oldusername", required=false) String oldusername)
	{
		if(results.hasErrors())
		{
			return "createuserpage";
		}
		
		String eventName = user.getEvent().toUpperCase();
		user.setEvent(eventName);
		
		if(save_edit == null) //create new user
		{
			//checks if chosen username is already exist@used
			if(usersService.usernameExist(user.getUsername()))
			{
				results.rejectValue("username", "Duplicate.user.username", "Username is already exist");
				return "createuserpage";
			}
			
			//checks if chosen event name is already exist@used
			if(usersService.eventExist(user.getEvent()))
			{
				results.rejectValue("event", "Duplicate.user.event", "Event name is already exist");
				return "createuserpage";
			}
			
			user.setEnabled(true);
			user.setAuthority("ROLE_USER");
			
			//save the user into database
			usersService.saveUser(user);
			
			//redirect to admin page to display all updated users
			return "redirect:/adminpage?action=adduser";
		}
		else //edit existing user
		{
			if(!oldusername.equals(user.getUsername()))
			{
				//checks if chosen username is already exist@used
				if(usersService.usernameExist(user.getUsername()))
				{
					results.rejectValue("username", "Duplicate.user.username", "Username is already exist");
					model.addAttribute("oldusername", oldusername);
					return "createuserpage";
				}	
			}
						
			//update user in database
			usersService.saveUser(user);
			
			//redirect to admin page to display all update users
			return "redirect:/adminpage?action=edituser";
		}
	}
	
	//
	//this method runs when admin wants to edit existing user,
	//retrieve user info from database and display it in create-user-form
	//
	@RequestMapping("/edituser")
	public String editUser(@RequestParam(value="id") int id, Model model)
	{
		//user retrieved using id
		User user = usersService.getUser(id);
		
		user.setPassword("");
		model.addAttribute("user", user);
		model.addAttribute("oldusername", user.getUsername());
		model.addAttribute("action", "edituser");
		
		return "createuserpage";
	}
	
	//
	//this method deletes existing user base on id &
	//delete rows in leads table base on event name
	//
	@RequestMapping("/deleteuser")
	public String deleteUser(@RequestParam(value="id", required=false) Integer id, Model model)
	{
		String event = usersService.getEventName(id);
		
		//delete user base on id
		usersService.deleteUser(id, event);
		
		//redirect to admin page to display all updated users
		return "redirect:/adminpage?action=deleteuser";
	}
	
	//
	//this method retrieve data from server using ajax into json data format
	//
	@RequestMapping(value="/noofleads", produces="application/json")
	@ResponseBody
	public Map<String, Object> noOfLeads()
	{
		List<User> users = usersService.getAllCurrentUsers();
		
		List<Lead> leads = new ArrayList<>();
		
		Map<String, Object> data = new HashMap<String, Object>();

		for(User user : users)
		{
			if(user.getEvent().equals("ADMIN"))
			{
				continue;
			}
			
			leads = leadsService.getAllLeads(user.getEvent());

			data.put(user.getUsername(), leads.size());
		}
		
		return data;
	}
}











