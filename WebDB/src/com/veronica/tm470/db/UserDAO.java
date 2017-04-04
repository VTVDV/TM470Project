package com.veronica.tm470.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.veronica.tm470.dbo.*;

public class UserDAO extends AbstractDAO 
{	
	
	public void testConnection() throws Exception 
	{
		Connection connection = getConnection();
		System.out.println(connection.isClosed());
		
	}
	
	public User validateUser(String name, String password) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		User user = null;
		
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM tbl_user WHERE USER_NAME = ?");
			statement.setString(1, name);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) 
			{
				int id = rs.getInt("USER_ID");
				String username = rs.getString("USER_NAME");
				String pass = rs.getString("PASSWORD");
				int usertype = rs.getInt("USERTYPE");
				if(password.equals(pass))
				{
					return new User(id, username, pass, usertype);
				}
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
				System.out.println("There was an error closing the statement.");
			}			
		}
		return user;
	}
	
	public Map<Integer, User> getUsers() throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		User user = null;
		Map<Integer, User> users = new HashMap<Integer, User>();
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM tbl_user");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) 
			{
				int id = rs.getInt("USER_ID");
				String username = rs.getString("USER_NAME");
				String pass = rs.getString("PASSWORD");
				int usertype = rs.getInt("USERTYPE");
				user = new User(id, username, pass, usertype);
				users.put(id, user);
			}			
			return users;
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
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
		return users;
	}
	
	public void addUser(String name, String password, int userType) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_user (USER_NAME,PASSWORD,USERTYPE) VALUES (?, ?, ?)");
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setInt(3, userType);
			statement.executeUpdate();
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
				System.out.println("There was an error closing the statement.");
			}			
		}
	}
	
	public void updateUser(String name, String password, int userType, int id) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE tbl_user SET USER_NAME = ?,PASSWORD = ?,USERTYPE = ? WHERE USER_ID = ?");
			statement.setString(1, name);
			statement.setString(2, password);
			statement.setInt(3, userType);
			statement.setInt(4, id);
			statement.executeUpdate();
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
				System.out.println("There was an error closing the statement.");
			}			
		}
	}
}
