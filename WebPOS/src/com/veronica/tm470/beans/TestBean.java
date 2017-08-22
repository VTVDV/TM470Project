package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.veronica.tm470.dbo.Customer;
import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.Test;

@ManagedBean
@SessionScoped
public class TestBean extends AbstractBean implements Serializable {
	private int id;
	private Date date;
	private Date time;
	private String booker; //User who booked in item for test.
	private String tester; //User who tested item.
	private Customer customer;
	private StockRecord itemType;
	private String serialNumber;
	private boolean passed;
	
	private Test selectedTest;
	private List<Test> tests;
	
	@ManagedProperty(value = "#{salesBean}")
	private SalesBean salesBean;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty(value = "#{customerBean}")
	private CustomerBean customerBean;
	
	public String createTest()
	{
		StockItem stockItem = new StockItem();
		stockItem.setID(salesBean.generateId());
		stockItem.setName("TEST: " + itemType.getName());
		stockItem.setSerial(serialNumber);
		Test test = new Test(userBean.getUser(), customerBean.getSelectedCustomer(), itemType);
		return null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getBooker() {
		return booker;
	}

	public void setBooker(String booker) {
		this.booker = booker;
	}

	public String getTester() {
		return tester;
	}

	public void setTester(String tester) {
		this.tester = tester;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public StockRecord getItemType() {
		return itemType;
	}

	public void setItemType(StockRecord itemType) {
		this.itemType = itemType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public List<Test> getTests() {
		return tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	@Override
	protected void clearForm() {
		// TODO Auto-generated method stub		
	}

	public Test getSelectedTest() {
		return selectedTest;
	}

	public void setSelectedTest(Test selectedTest) {
		this.selectedTest = selectedTest;
	}
	
}
