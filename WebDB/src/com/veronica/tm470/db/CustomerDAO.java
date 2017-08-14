package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.veronica.tm470.dbo.Address;
import com.veronica.tm470.dbo.Customer;

public class CustomerDAO extends AbstractDAO
{
	public void create(Customer customer) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_customer "
					+ "(CUSTOMER_FIRSTNAMES,"
					+ "CUSTOMER_LASTNAME,"
					+ "CUSTOMER_MOBILENUMBER,"
					+ "CUSTOMER_HOMENUMBER,"
					+ "CUSTOMER_EMAIL,"
					+ "CUSTOMER_DOB,"
					+ "CUSTOMER_IDTYPE,"
					+ "CUSTOMER_IDNUMBER,"
					+ "CUSTOMER_DOORNUMBER,"
					+ "CUSTOMER_BUILDING,"
					+ "CUSTOMER_STREET,"
					+ "CUSTOMER_CITY,"
					+ "CUSTOMER_COUNTY,"
					+ "CUSTOMER_POSTCODE,"
					+ "CUSTOMER_IS_BANNED,"
					+ "CUSTOMER_IS_STAFF)"
					+ "VALUES (?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)"
					);
			int parameterCount = 1;
			statement.setString(parameterCount++, customer.getFirstNames());
			statement.setString(parameterCount++, customer.getLastName());
			statement.setString(parameterCount++, customer.getMobileNumber());
			statement.setString(parameterCount++, customer.getHomeNumber());
			statement.setString(parameterCount++, customer.getEmail());
			statement.setDate(parameterCount++, new Date(customer.getDateOfBirth().getTime()));
			statement.setString(parameterCount++, customer.getIdType());
			statement.setString(parameterCount++, customer.getIdNumber());
			statement.setString(parameterCount++, customer.getAddress().getHouseNumber());
			statement.setString(parameterCount++, customer.getAddress().getBuildingName());
			statement.setString(parameterCount++, customer.getAddress().getStreetName());
			statement.setString(parameterCount++, customer.getAddress().getCity());
			statement.setString(parameterCount++, customer.getAddress().getCounty());
			statement.setString(parameterCount++, customer.getAddress().getPostcode());
			statement.setInt(parameterCount++, customer.isBanned() ? 1 : 0 );
			statement.setInt(parameterCount++, customer.isStaff() ? 1 : 0);	
			statement.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Error inserting customer.");
		}
		finally
		{
			try
			{
				close(statement, connection);
			}
			catch(SQLException sqlxstate)
			{
				sqlxstate.printStackTrace();
				System.out.println("There was an error closing the statement.");
			}
		}
	}
	
	public List<Customer> getCustomers() throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<Customer> customers = new ArrayList<>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_customer");
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				Address address = new Address();
				address.setHouseNumber(rs.getString("CUSTOMER_DOORNUMBER"));
				address.setBuildingName(rs.getString("CUSTOMER_BUILDING"));
				address.setStreetName(rs.getString("CUSTOMER_STREET"));
				address.setCity(rs.getString("CUSTOMER_CITY"));
				address.setCounty(rs.getString("CUSTOMER_COUNTY"));
				address.setPostcode(rs.getString("CUSTOMER_POSTCODE"));
				Customer customer = new Customer();
				customer.setId(rs.getInt("CUSTOMER_ID"));
				customer.setFirstNames(rs.getString("CUSTOMER_FIRSTNAMES"));
				customer.setLastName(rs.getString("CUSTOMER_LASTNAME"));
				customer.generateFullName();
				customer.setMobileNumber(rs.getString("CUSTOMER_MOBILENUMBER"));
				customer.setHomeNumber(rs.getString("CUSTOMER_HOMENUMBER"));
				customer.setEmail(rs.getString("CUSTOMER_EMAIL"));
				customer.setIdType(rs.getString("CUSTOMER_IDTYPE"));
				customer.setIdNumber(rs.getString("CUSTOMER_IDNUMBER"));
				customer.setBanned(rs.getBoolean("CUSTOMER_IS_BANNED"));
				customer.setStaff(rs.getBoolean("CUSTOMER_IS_STAFF"));
				customer.setNote(rs.getString("CUSTOMER_NOTE"));
				customer.setAddress(address);
				customers.add(customer);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				close(statement, connection);
			}
			catch(SQLException sqlxstate)
			{
				sqlxstate.printStackTrace();
			}		
		}
		return customers;
	}
	
	public Customer getCustomer(int id) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		Customer customer = new Customer();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_customer WHERE CUSTOMER_ID = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next())
			{
				Address address = new Address();
				address.setHouseNumber(rs.getString("CUSTOMER_DOORNUMBER"));
				address.setBuildingName(rs.getString("CUSTOMER_BUILDING"));
				address.setStreetName(rs.getString("CUSTOMER_STREET"));
				address.setCity(rs.getString("CUSTOMER_CITY"));
				address.setCounty(rs.getString("CUSTOMER_COUNTY"));
				address.setPostcode(rs.getString("CUSTOMER_POSTCODE"));
				customer.setId(rs.getInt("CUSTOMER_ID"));
				customer.setFirstNames(rs.getString("CUSTOMER_FIRSTNAMES"));
				customer.setLastName(rs.getString("CUSTOMER_LASTNAME"));
				customer.generateFullName();
				customer.setMobileNumber(rs.getString("CUSTOMER_MOBILENUMBER"));
				customer.setHomeNumber(rs.getString("CUSTOMER_HOMENUMBER"));
				customer.setEmail(rs.getString("CUSTOMER_EMAIL"));
				customer.setIdType(rs.getString("CUSTOMER_IDTYPE"));
				customer.setIdNumber(rs.getString("CUSTOMER_IDNUMBER"));
				customer.setBanned(rs.getBoolean("CUSTOMER_IS_BANNED"));
				customer.setStaff(rs.getBoolean("CUSTOMER_IS_STAFF"));
				customer.setNote(rs.getString("CUSTOMER_NOTE"));
				customer.setAddress(address);
			}
		}			
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				close(statement, connection);
			}
			catch(SQLException sqlxstate)
			{
				sqlxstate.printStackTrace();
			}		
		}
		return customer;
	}
}
