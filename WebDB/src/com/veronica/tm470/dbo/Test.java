package com.veronica.tm470.dbo;

import java.util.Calendar;
import java.util.Date;


public class Test
{
	private int id;
	private Date dateAndTime;
	private User tester; //User who tested item.
	private User booker; //User who booked item in for testing
	private User checker; //User who checks out item.
	private Customer customer;
	
	private StockRecord itemType;
	private boolean notToBeFormatted; //If the item in test is to be formatted or not.
	private String serialNumber;
	private int status; //Status of the test, 0 = untested, 1 = pass, 2 = fail, 3 = Bought in, 4 = Declined and item returned to customer.
	
	public Test()
	{
		
	}
	
	public Test(int id, User booker, Customer customer, StockRecord itemType)
	{
		this.id = id;
		this.booker = booker;
		this.customer = customer;
		this.itemType = itemType;
	}
	
	public Test(User booker, Customer customer, StockRecord itemType)
	{
		this.booker = booker;
		this.customer = customer;
		this.itemType = itemType;
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	
	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getChecker() {
		return checker;
	}

	public void setChecker(User checker) {
		this.checker = checker;
	}

	public boolean isNotToBeFormatted() {
		return notToBeFormatted;
	}

	public void setNotToBeFormatted(boolean notToBeFormatted) {
		this.notToBeFormatted = notToBeFormatted;
	}
	
	
}
