package com.veronica.tm470.dbo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable
{
	private int id;
	private int age;
	private String firstNames; //Includes middle name.
	private String lastName;
	private String fullName;
	private Address address;
	private List<Transaction> transactionHistory;
	
	//Contact Info
	private String mobileNumber;
	private String homeNumber;
	private String email;
	
	//Due Diligence
	private Date dateOfBirth;
	private String idType; //Type of ID given.
	private String idNumber;
	
	//Account status
	private boolean isStaff;
	private boolean isBanned;
	private String note;
	
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getFirstNames()
	{
		return firstNames;
	}
	public void setFirstNames(String firstNames)
	{
		this.firstNames = firstNames;
	}
	public String getLastName()
	{
		return lastName;
	}
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	public Address getAddress()
	{
		return address;
	}
	public void setAddress(Address address)
	{
		this.address = address;
	}
	public List<Transaction> getTransactionHistory()
	{
		return transactionHistory;
	}
	public void setTransactionHistory(List<Transaction> transactionHistory)
	{
		this.transactionHistory = transactionHistory;
	}
	public boolean isStaff()
	{
		return isStaff;
	}
	public void setStaff(boolean isStaff)
	{
		this.isStaff = isStaff;
	}
	public boolean isBanned()
	{
		return isBanned;
	}
	public void setBanned(boolean isBanned)
	{
		this.isBanned = isBanned;
	}
	public String getNote()
	{
		return note;
	}
	public void setNote(String note)
	{
		this.note = note;
	}
	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}
	public String getIdType()
	{
		return idType;
	}
	public void setIdType(String idType)
	{
		this.idType = idType;
	}
	public String getIdNumber()
	{
		return idNumber;
	}
	public void setIdNumber(String idNumber)
	{
		this.idNumber = idNumber;
	}
	public String getMobileNumber()
	{
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
	public String getHomeNumber()
	{
		return homeNumber;
	}
	public void setHomeNumber(String homeNumber)
	{
		this.homeNumber = homeNumber;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
		
	public String getFullName()
	{
		return fullName;
	}

	public void setFullName(String fullName)
	{
		this.fullName = fullName;
	}

	@Override
	public boolean equals(Object obj)
	{
		if(this.id == ((Customer) obj).getId())
		{
			return true;
		}
		return false;			
	}
	
	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	public void generateFullName()
	{
		this.fullName = this.getFirstNames() + " " + this.getLastName();
	}
}
