package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import com.veronica.tm470.db.TestDAO;
import com.veronica.tm470.dbo.Customer;
import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.Test;
import com.veronica.tm470.dbo.TransactionType;
import com.veronica.tm470.exceptions.WebDBException;

@ManagedBean
@SessionScoped
public class TestBean extends AbstractBean implements Serializable {
	private int id;
	private Date datetime;
	private String booker; //User who booked in item for test.
	private String tester; //User who tested item.
	private String checker;
	private Customer customer;
	private StockRecord itemType;
	private String serialNumber;
	private boolean doNotFormat;
	
	private Test selectedTest;
	private List<Test> allTests;
	private List<Test> activeTests;
	private List<Test> inactiveTests;
	
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
		stockItem.setTransactionType(TransactionType.TEST);
		salesBean.transactionCheck();
		salesBean.getTransaction().getItems().add(stockItem);
		Test test = new Test(userBean.getUser(), customerBean.getSelectedCustomer(), itemType);
		test.setSerialNumber(serialNumber);
		test.setStatus(0);
		test.setNotToBeFormatted(doNotFormat);
		TestDAO dao = new TestDAO();
		try 
		{
			dao.addTest(test);
		} 
		catch (WebDBException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String fetchTests()
	{
		fetchActiveTests();
		fetchInactiveTests();
		fetchAllTests();
		return null;
	}
	
	public String fetchActiveTests()
	{
		TestDAO dao = new TestDAO();
		this.activeTests = dao.getActiveTests();		
		return null;
	}
	
	public String fetchInactiveTests()
	{
		TestDAO dao = new TestDAO();
		this.inactiveTests = dao.getInactiveTests();		
		return null;
	}
	
	public String fetchAllTests()
	{
		TestDAO dao = new TestDAO();
		this.allTests = dao.getTests();
		return null;
	}
	
	@Override
	protected void clearForm() {
		this.datetime = null;
		this.booker = null;
		this.tester = null;
		this.checker = null;
		this.customer = null;
		this.itemType = null;
		this.serialNumber = null;
		this.doNotFormat = false;
		this.selectedTest = null;		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
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

	public boolean isDoNotFormat() {
		return doNotFormat;
	}

	public void setDoNotFormat(boolean doNotFormat) {
		this.doNotFormat = doNotFormat;
	}

	public List<Test> getTests() {
		return inactiveTests;
	}

	public void setTests(List<Test> tests) {
		this.inactiveTests = tests;
	}

	public Test getSelectedTest() {
		return selectedTest;
	}

	public void setSelectedTest(Test selectedTest) {
		this.selectedTest = selectedTest;
	}

	public SalesBean getSalesBean() {
		return salesBean;
	}

	public void setSalesBean(SalesBean salesBean) {
		this.salesBean = salesBean;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public List<Test> getAllTests() {
		return allTests;
	}

	public void setAllTests(List<Test> allTests) {
		this.allTests = allTests;
	}

	public List<Test> getActiveTests() {
		return activeTests;
	}

	public void setActiveTests(List<Test> activeTests) {
		this.activeTests = activeTests;
	}

	public List<Test> getInactiveTests() {
		return inactiveTests;
	}

	public void setInactiveTests(List<Test> inactiveTests) {
		this.inactiveTests = inactiveTests;
	}
	
	public void onRowSelect(SelectEvent event)
	{
		this.selectedTest = ((Test)  event.getObject());
		if(this.selectedTest.isNotToBeFormatted()==true)
		{
			FacesContext.getCurrentInstance().addMessage("testMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"WARNING!", "This item is NOT to be formatted!"));
		}
	}
	
}
