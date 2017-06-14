package com.popiang.opc.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

/*
 * this class handles:
 * 1. retrieving all users from database
 * 2. retrieving certain data from database
 * 3. creating new user
 * 4. editing existing user
 * 5. deleting existing user
 * 6. cheking if username or event name exist in database
 * --hibernate is used in this class to handle database operations--
 */

@Repository
public class UsersDao 
{
	//linking to SessionFactory bean to user hibernate in this class
	@Autowired
	private SessionFactory factory;
	
	//lingking to PasswordEncoder bean
	@Autowired
	private PasswordEncoder passwordEncoder;

	//this method retrieves all users from database
	@Transactional
	public List<User> getAllUsers() 
	{
		Session session = factory.getCurrentSession();
		
		List<User> users = session.createQuery("from User", User.class).getResultList();
		
		return users;
	}

	//this method retrieves event name base on username
	public String getEventName(String username) 
	{
		Session session = factory.getCurrentSession();
		
		List<User> users = session.createQuery("from User where username=:username", User.class).setParameter("username", username).getResultList();
		
		return users.get(0).getEvent();
	}
	
	//this method retrieves event name base on user id
	public String getEventName(int id) 
	{
		Session session = factory.getCurrentSession();
		
		User user = session.get(User.class, id);
		
		return user.getEvent();
	}

	//
	//this method deletes user base on id &
	//deletes rows in leads table base on event name
	//
	public void deleteUser(int id, String event) 
	{
		Session session = factory.getCurrentSession();
		
		session.createQuery("delete User where id=:id").setParameter("id", id).executeUpdate();
		session.createQuery("delete Lead where eventName=:eventName").setParameter("eventName", event).executeUpdate();
	}

	//this method retrieves user base on user id
	public User getUser(int id) 
	{
		Session session = factory.getCurrentSession();
		
		User user = session.get(User.class, id);
		
		return user;
	}

	//this method saves or updates user
	public void saveUser(User user) 
	{
		Session session = factory.getCurrentSession();
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		session.saveOrUpdate(user);
	}

	//this method checks if username is already exist in database
	public boolean usernameExist(String username) 
	{
		Session session = factory.getCurrentSession();
		
		List<User> users = session.createQuery("from User where username=:username", 
				User.class).setParameter("username", username).getResultList();
		
		return users.size() > 0;
	}

	//this method checks if event name is already exist in database 
	public boolean eventExist(String event) 
	{
		Session session = factory.getCurrentSession();
		
		List<User> users = session.createQuery("from User where event=:event", 
				User.class).setParameter("event", event).getResultList();
		
		return users.size() > 0;
	}

	//this method returns all event's names
	public List<String> getAllEvents() 
	{
		Session session = factory.getCurrentSession();

		List<String> events = session.createQuery("select event from User", String.class).getResultList();
		
		return events;
	}
}
