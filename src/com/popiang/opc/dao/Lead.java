package com.popiang.opc.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/*
 * this class will be used to create lead object
 */

@Entity
@Table(name="leads")
public class Lead 
{
	@Id
	@Column(name="Id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Id;

	@Column(name="Name")
	@NotNull(message="Cannot be blank")
	@Size(min=3, max=50, message="Must be between 3 to 50 characteres long")
	private String name;
	
	@Column(name="HP_Number")
	@NotNull(message="Cannot be blank")
	private String hpNumber;
	
	@Column(name="IC")
	private String IC;
	
	@Column(name="Marital_Status")
	private String maritalStatus;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Occupation")
	private String occupation;
	
	@Column(name="Income")
	private double income;
	
	@Column(name="Often_Travel")
	private	boolean oftenTravel;
	
	@Column(name="Local_Or_Overseas")
	private String localOrOversea;
	
	@Column(name="Preferred_Payment")
	private String preferedPayment;
	
	@Column(name="TM_Code")
	private String tmCode;
	
	@Column(name="Lead_Date")
	private String date;
	
	@Column(name="Event_Name")
	private String eventName;
	
	public Lead() 
	{

	}

	public int getId()
	{
		return Id;
	}

	public void setId(int id) 
	{
		Id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getIC() 
	{
		return IC;
	}

	public void setIC(String iC) 
	{
		IC = iC;
	}

	public String getHpNumber() 
	{
		return hpNumber;
	}

	public void setHpNumber(String hpNumber) 
	{
		this.hpNumber = hpNumber;
	}

	public String getMaritalStatus() 
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) 
	{
		this.maritalStatus = maritalStatus;
	}

	public String getOccupation() 
	{
		return occupation;
	}

	public void setOccupation(String occupation) 
	{
		this.occupation = occupation;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public double getHouseholdIncome() 
	{
		return income;
	}

	public void setHouseholdIncome(double householdIncome) 
	{
		this.income = householdIncome;
	}

	public String getPreferedPayment() 
	{
		return preferedPayment;
	}

	public void setPreferedPayment(String preferedPayment) 
	{
		this.preferedPayment = preferedPayment;
	}

	public boolean isOftenTravel() 
	{
		return oftenTravel;
	}

	public void setOftenTravel(boolean oftenTravel) 
	{
		this.oftenTravel = oftenTravel;
	}

	public String getLocalOrOversea() 
	{
		return localOrOversea;
	}

	public void setLocalOrOversea(String localOrOversea) 
	{
		this.localOrOversea = localOrOversea;
	}

	public String getTmCode() 
	{
		return tmCode;
	}

	public void setTmCode(String tmCode) 
	{
		this.tmCode = tmCode;
	}

	public String getDate() 
	{
		return date;
	}

	public void setDate(String date) 
	{
		this.date = date;
	}

	public String getEventName()
	{
		return eventName;
	}

	public void setEventName(String eventName) 
	{
		this.eventName = eventName;
	}

	@Override
	public String toString()
	{
		return "Lead [Id=" + Id + ", name=" + name + ", hpNumber=" + hpNumber + ", IC=" + IC + ", maritalStatus="
				+ maritalStatus + ", email=" + email + ", occupation=" + occupation + ", householdIncome="
				+ income + ", oftenTravel=" + oftenTravel + ", localOrOversea=" + localOrOversea
				+ ", preferedPayment=" + preferedPayment + ", tmCode=" + tmCode + ", date=" + date + ", eventName="
				+ eventName + "]";
	}
}
