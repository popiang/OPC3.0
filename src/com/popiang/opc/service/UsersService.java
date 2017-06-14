package com.popiang.opc.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.popiang.opc.dao.User;
import com.popiang.opc.dao.UsersDao;

/*
 * this class acts as a layer between controller and usersdao class 
 */

@Service
public class UsersService 
{
	//linking to UsersDao bean
	@Autowired
	private UsersDao usersDao;
	
	//this method retrieved all current users from database
	@Transactional
	public List<User> getAllCurrentUsers()
	{
		return usersDao.getAllUsers();
	}

	//this method retrieves event name base on username
	@Transactional
	public String getEventName(String username) 
	{
		return usersDao.getEventName(username);
	}
	
	//this method retrieves event name base on id
	@Transactional
	public String getEventName(int id) 
	{
		return usersDao.getEventName(id);
	}

	//this method deletes user base on id
	@Transactional
	public void deleteUser(int id, String event) 
	{
		usersDao.deleteUser(id, event);
	}

	//this method retrieves user base on id
	@Transactional
	public User getUser(int id) 
	{
		return usersDao.getUser(id);
	}

	//this method saves or updates user
	@Transactional
	public void saveUser(User user) 
	{
		usersDao.saveUser(user);
	}

	//this method checks if username is already exist in database
	@Transactional
	public boolean usernameExist(String username) 
	{
		return usersDao.usernameExist(username);
	}

	//this method checks if event name is already exist in database
	@Transactional
	public boolean eventExist(String event) 
	{
		return usersDao.eventExist(event);
	}

	//this method return all event's names
	@Transactional
	public List<String> getAllEvents()
	{
		return usersDao.getAllEvents();
	}
}
