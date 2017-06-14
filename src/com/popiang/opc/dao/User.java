package com.popiang.opc.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/*
 *this class will be used to create user object 
 */

@Entity
@Table(name="users")
public class User 
{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="username")
	@NotNull(message="Username cannot be blank")
	@Size(min=5, max=20, message="Must be between 5 to 20 characters")
	private String username;
	
	@Column(name="password")
	@NotNull(message="Password cannot be blank")
	@Pattern(regexp="^\\S+$", message="Invalid password")
	private String password;
	
	@Column(name="event")
	@NotBlank(message="Event must not be blank")
	private String event;
	
	@Column(name="authority")
	private String authority;
	
	@Column(name="enabled")
	private boolean enabled;
	
	public User()
	{
		
	}
	
	public User(String username, String password, String authority, boolean enabled, String event) 
	{
		this.username = username;
		this.password = password;
		this.authority = authority;
		this.enabled = enabled;
		this.event = event;
	}
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getUsername() 
	{
		return username;
	}

	public void setUsername(String username) 
	{
		this.username = username;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public String getAuthority() 
	{
		return authority;
	}

	public void setAuthority(String authority) 
	{
		this.authority = authority;
	}

	public boolean isEnabled() 
	{
		return enabled;
	}

	public void setEnabled(boolean enabled) 
	{
		this.enabled = enabled;
	}

	public String getEvent() 
	{
		return event;
	}

	public void setEvent(String event) 
	{
		this.event = event;
	}

	public String toString() 
	{
		return "User [id=" + id + ", username=" + username + ", event=" + event + ", password=" + password
				+ ", authority=" + authority + ", enabled=" + enabled + "]";
	}
}











