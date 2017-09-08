package com.veronica.tm470.db;


import java.sql.*;
import java.util.TreeMap;

import java.util.Map;

import com.veronica.tm470.dbo.*;
import com.veronica.tm470.exceptions.WebConstants;
import com.veronica.tm470.exceptions.WebDBException;

public class TransactionDAO extends AbstractDAO 
{	
	
	public void testConnection() throws Exception 
	{
		Connection connection = getConnection();
		System.out.println(connection.isClosed());
		
	}
	
	public void getTransaction(int id) throws Exception 
	{
		
	}
	
	public void getAllTransactions() throws Exception
	{
		
	}
	
	public void addTransaction(Transaction transaction) throws Exception 
	{
		
	}	
}
