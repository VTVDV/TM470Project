package com.veronica.tm470.dbo;

import java.util.Date;

public class Test
{
	private int id;
	private Date date;
	private Date time;
	private String booker; //User who booked in item for test.
	private String tester; //User who tested item.
	private Customer customer;
	
	private StockRecord itemType;
	private String serialNumber;
	private boolean passed; //If the item has passed the test.
	
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
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Date getTime()
	{
		return time;
	}
	public void setTime(Date time)
	{
		this.time = time;
	}
	public String getBooker()
	{
		return booker;
	}
	public void setBooker(String booker)
	{
		this.booker = booker;
	}
	public String getTester()
	{
		return tester;
	}
	public void setTester(String tester)
	{
		this.tester = tester;
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
	public boolean isPassed()
	{
		return passed;
	}
	public void setPassed(boolean passed)
	{
		this.passed = passed;
	}
	
	
}
