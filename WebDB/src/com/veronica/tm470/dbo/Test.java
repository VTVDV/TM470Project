package com.veronica.tm470.dbo;


import java.text.SimpleDateFormat;
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
	private int status; 
	private String failReason;
	/*Status of the test, 
	 * 0 = untested, 
	 * 1 = pass, 
	 * 2 = fail, 
	 * 3 = Bought in, 
	 * 4 = Declined and item returned to customer,
	 * 5 = Warranty Test: Untested,
	 * 6 = Warranty Test: Faulty,
	 * 7 = Warranty Test: Fault Not Found,
	 * 8 = Warranty Test: Returned to Customer,
	 * 9 = Warranty Test: Refunded,
	 * 10 = Warranty Test: Repaired/Replaced*/
	
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
	public String getDate()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		return format.format(dateAndTime);
	}
	public String getTime()
	{
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(dateAndTime);
	}
	public String getStatusAsString()
	{
		switch(this.status){
			case 0: return "Untested";
			case 1: return "Passed";
			case 2: return "Fail";
			case 3: return "Bought In";
			case 4: return "Returned to Customer";
			case 5: return "Warranty Test: Untested";
			case 6: return "Warranty Test: Faulty";
			case 7: return "Warranty Test: Fault Not Found";
			case 8: return "Warranty Test: Returned to Customer";
			case 9: return "Warranty Test: Refunded";
			case 10: return "Warranty Test: Repaired/Replaced";
			case 11: return "Test Voided";
			default: return "Unkown?";
		}
	}

	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	
}
