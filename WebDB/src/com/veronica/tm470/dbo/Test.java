package com.veronica.tm470.dbo;

import java.util.Date;

public class Test
{
	private int id;
	private Date dateAndTime;
	private User tester; //User who tested item.
	private User booker;
	private Customer customer;
	
	private StockRecord itemType;
	private String serialNumber;
	private int status; //Status of the test, 0 = untested, 1 = pass, 2 = fail.
	
	public Test()
	{
		
	}
	
	public Test(int id, User booker, Customer customer, StockRecord itemType)
	{
		this.id = id;
		this.booker = booker;
		this.customer = customer;
		this.itemType = itemType;
		this.dateAndTime = new Date();
	}
	
	public Test(User booker, Customer customer, StockRecord itemType)
	{
		this.booker = booker;
		this.customer = customer;
		this.itemType = itemType;
		this.dateAndTime = new Date();
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Date getDate()
	{
		return dateAndTime;
	}
	public void setDate(Date date)
	{
		this.dateAndTime = date;
	}
	public Date getTime()
	{
		return dateAndTime;
	}
	public void setTime(Date time)
	{
		this.dateAndTime = time;
	}
	
	public User getTester() {
		return tester;
	}

	public void setTester(User tester) {
		this.tester = tester;
	}

	public User getBooker() {
		return booker;
	}

	public void setBooker(User booker) {
		this.booker = booker;
	}

	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public StockRecord getItemType()
	{
		return itemType;
	}
	public void setItemType(StockRecord itemType)
	{
		this.itemType = itemType;
	}
	public String getSerialNumber()
	{
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber)
	{
		this.serialNumber = serialNumber;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
