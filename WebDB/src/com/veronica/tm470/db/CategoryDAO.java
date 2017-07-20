package com.veronica.tm470.db;


import java.sql.*;
import java.util.ArrayList;
import java.util.TreeMap;

import org.eclipse.persistence.platform.database.MySQLPlatform;

import java.util.Map;

import com.veronica.tm470.dbo.*;
import com.veronica.tm470.exceptions.WebConstants;
import com.veronica.tm470.exceptions.WebDBException;

public class CategoryDAO extends AbstractDAO 
{	
	
	public void testConnection() throws Exception 
	{
		Connection connection = getConnection();
		System.out.println(connection.isClosed());
		
	}
	
	public Category getCategory(int id) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		Category category = null;
		
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM tbl_category WHERE CAT_ID = ?");
			statement.setInt(1, id);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) 
			{
				int catId = rs.getInt("CAT_ID");
				String catName = rs.getString("CAT_NAME");
				return new Category(catId, catName);
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
		return category;
	}
	
	public Map<Integer, Category> getAllCategories() throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		Category category = null;
		Map<Integer, Category> categories = new TreeMap<Integer, Category>();
		
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM tbl_category ORDER BY CAT_NAME ASC");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) 
			{
				int id = rs.getInt("CAT_ID");
				String name = rs.getString("CAT_NAME");
				category = new Category(id, name);
				categories.put(id, category);
			}			
			return categories;
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
		return categories;
	}
	
	public void addCategory(String name) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_category (CAT_NAME) VALUES (?)");
			statement.setString(1, name);
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
	
	public void updateCategory(int id, String newName) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("UPDATE tbl_category SET CAT_NAME = ? WHERE CAT_ID = ?");
			statement.setString(1, newName);
			statement.setInt(2, id);
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
	
	//Added for testing reasons but should not be used in final project! Or it will leave stock items with no categories!
	//Maybe add condition where a category must have no stock records attached.
	public void deleteCategory(int id) throws WebDBException 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM tbl_category WHERE CAT_ID = ?");
			statement.setInt(1, id);
			statement.executeUpdate();
		}
		catch(SQLException e) 
		{
			if (e.getErrorCode() == WebConstants.ER_ROW_IS_REFERENCED) {
				throw new WebDBException(WebConstants.CHILD_RECORD_FOUND);
			} else {
				e.printStackTrace();
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				close(statement, connection);
			}
			catch(Exception sqlxstate)
			{
				sqlxstate.printStackTrace();
				System.out.println("There was an error closing the statement.");
			}			
		}
	}
}
