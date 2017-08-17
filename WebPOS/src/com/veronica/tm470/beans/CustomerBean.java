package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.veronica.tm470.db.CustomerDAO;
import com.veronica.tm470.dbo.Address;
import com.veronica.tm470.dbo.Customer;

@ManagedBean (name = "customerBean")
@SessionScoped
public class CustomerBean extends AbstractBean implements Serializable
{
	private int age;
	private String firstNames; //Includes middle name.
	private String lastName;	
	private String note;
	
	//Due Diligence
	private Date dateOfBirth;
	private String idType; //Type of ID given.
	private String idNumber;
	
	//Address info
	private String houseNumber;	//String as some people have named houses.
	private String buildingName; //Optional
	private String streetName;
	private String city; //Use for town/city/village.
	private String county;
	private String postcode;
	
	//Contact Info
	private String mobileNumber;
	private String homeNumber;
	private String email;
		
	private boolean isStaff;
	private boolean isBanned;
	
	private List<Customer> customerList;
	private Customer selectedCustomer;
		
	public String createCustomer()
	{		
		Customer customer = createNewCustomer();
		selectedCustomer = customer;
		CustomerDAO customerDAO = new CustomerDAO();
		try 
		{
			customerDAO.create(customer);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		clearForm();
		return null;
	}
	
	private Customer createNewCustomer()
	{
		Address address = new Address(houseNumber, buildingName, streetName, city, county, postcode);
		Customer customer = new Customer();
		customer.setAddress(address);
		customer.setAge(age);
		customer.setFirstNames(firstNames);
		customer.setLastName(lastName);
		customer.setMobileNumber(mobileNumber);
		customer.setHomeNumber(homeNumber);
		customer.setEmail(email);
		customer.setDateOfBirth(dateOfBirth);
		customer.setIdType(idType);
		customer.setIdNumber(idNumber);
		customer.setStaff(isStaff);
		customer.setBanned(false);
		customer.setNote(note);
		
		return customer;
	}
	
	public String getCustomers()
	{
		customerList = new ArrayList<>();
		CustomerDAO dao = new CustomerDAO();
		try
		{
			customerList = dao.getCustomers();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String validateCustomer()
	{
		if(selectedCustomer.isBanned())
		{
			FacesContext.getCurrentInstance().addMessage("selectMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Selected customer is banned!"));
		}
		if(!StringUtils.isEmpty(selectedCustomer.getNote()))
		{
			FacesContext.getCurrentInstance().addMessage("selectMessage", new FacesMessage(FacesMessage.SEVERITY_WARN, "Note:", selectedCustomer.getNote()));
		}
			
		return null;
	}
	
	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean isStaff() {
		return isStaff;
	}

	public void setStaff(boolean isStaff) {
		this.isStaff = isStaff;
	}

	public boolean isBanned() {
		return isBanned;
	}

	public void setBanned(boolean isBanned) {
		this.isBanned = isBanned;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

	@Override
	public void clearForm() {
		age = 0;
		firstNames = null;
		lastName = null;
		note = null;
		dateOfBirth = null;
		idType = null;
		idNumber = null;
		houseNumber = null;
		buildingName = null;
		streetName = null;
		city = null;
		county = null;
		postcode = null;
		mobileNumber = null;
		homeNumber = null;
		email = null;
		isStaff = false;
		isBanned = false;		
	}
		
}
