package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.veronica.tm470.dbo.Test;
import com.veronica.tm470.exceptions.WebConstants;
import com.veronica.tm470.exceptions.WebDBException;

public class TestDAO extends AbstractDAO
{
	public void addTest(Test test) throws WebDBException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_test "
					+ "(TEST_DATETIME,"
					+ "TEST_BOOKER_ID,"
					+ "TEST_STOCKRECORD_ID,"
					+ "TEST_SERIAL,"
					+ "TEST_STATUS,"
					+ "TEST_DO_NOT_FORMAT,"
					+ "TEST_CUSTOMER_ID) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
			int parameterCount = 1;
			java.sql.Timestamp datetime = new java.sql.Timestamp(new java.util.Date().getTime());
			statement.setTimestamp(parameterCount++, datetime);
			statement.setInt(parameterCount++, test.getBooker().getId());
			statement.setInt(parameterCount++, test.getItemType().getId());
			statement.setString(parameterCount++, test.getSerialNumber());
			statement.setInt(parameterCount++, test.getStatus());
			statement.setInt(parameterCount++, test.isNotToBeFormatted() ? 1 : 0);
			statement.setInt(parameterCount++, test.getCustomer().getId());
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
	
	public List<Test> getTests()
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<Test> tests = new ArrayList<>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_test");
			
			UserDAO udao = new UserDAO();
			StockDAO sdao = new StockDAO();
			CustomerDAO cdao = new CustomerDAO();
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Test test = new Test();
				test.setId(rs.getInt("TEST_ID"));
				test.setDateAndTime(new Date(rs.getTimestamp("TEST_DATETIME").getTime()));
				test.setBooker(udao.getUser(rs.getInt("TEST_BOOKER_ID")));
				test.setTester(udao.getUser(rs.getInt("TEST_TESTER_ID")));
				test.setChecker(udao.getUser(rs.getInt("TEST_CHECKER_ID")));
				test.setItemType(sdao.getStockRecord(rs.getInt("TEST_STOCKRECORD_ID")));
				test.setSerialNumber(rs.getString("TEST_SERIAL"));
				test.setStatus(rs.getInt("TEST_STATUS"));
				test.setNotToBeFormatted(rs.getBoolean("TEST_DO_NOT_FORMAT"));
				test.setCustomer(cdao.getCustomer(rs.getInt("TEST_CUSTOMER_ID")));
				tests.add(test);
			}
		}
		catch (Exception e) 
		{
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
		return tests;
	}
	
	public List<Test> getTests(int status)
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<Test> tests = new ArrayList<>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_test WHERE TEST_STATUS = ?");
			statement.setInt(1, status);
			
			UserDAO udao = new UserDAO();
			StockDAO sdao = new StockDAO();
			CustomerDAO cdao = new CustomerDAO();
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Test test = new Test();
				test.setId(rs.getInt("TEST_ID"));
				test.setDateAndTime(new Date(rs.getTimestamp("TEST_DATETIME").getTime()));
				test.setBooker(udao.getUser(rs.getInt("TEST_BOOKER_ID")));
				test.setTester(udao.getUser(rs.getInt("TEST_TESTER_ID")));
				test.setChecker(udao.getUser(rs.getInt("TEST_CHECKER_ID")));
				test.setItemType(sdao.getStockRecord(rs.getInt("TEST_STOCKRECORD_ID")));
				test.setSerialNumber(rs.getString("TEST_SERIAL"));
				test.setStatus(rs.getInt("TEST_STATUS"));
				test.setNotToBeFormatted(rs.getBoolean("TEST_DO_NOT_FORMAT"));
				test.setCustomer(cdao.getCustomer(rs.getInt("TEST_CUSTOMER_ID")));
				tests.add(test);
			}
		}
		catch (Exception e) 
		{
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
		return tests;
	}
	
	public List<Test> getActiveTests()
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<Test> tests = new ArrayList<>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_test WHERE TEST_STATUS in (0,1,2,5,6,7)");
			
			UserDAO udao = new UserDAO();
			StockDAO sdao = new StockDAO();
			CustomerDAO cdao = new CustomerDAO();
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Test test = new Test();
				test.setId(rs.getInt("TEST_ID"));
				test.setDateAndTime(new Date(rs.getTimestamp("TEST_DATETIME").getTime()));
				test.setBooker(udao.getUser(rs.getInt("TEST_BOOKER_ID")));
				test.setTester(udao.getUser(rs.getInt("TEST_TESTER_ID")));
				test.setChecker(udao.getUser(rs.getInt("TEST_CHECKER_ID")));
				test.setItemType(sdao.getStockRecord(rs.getInt("TEST_STOCKRECORD_ID")));
				test.setSerialNumber(rs.getString("TEST_SERIAL"));
				test.setStatus(rs.getInt("TEST_STATUS"));
				test.setNotToBeFormatted(rs.getBoolean("TEST_DO_NOT_FORMAT"));
				test.setCustomer(cdao.getCustomer(rs.getInt("TEST_CUSTOMER_ID")));
				tests.add(test);
			}
		}
		catch (Exception e) 
		{
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
		return tests;
	}
	
	public List<Test> getInactiveTests()
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<Test> tests = new ArrayList<>();
		try
		{
			connection = getConnection();
			statement = connection.prepareStatement("SELECT * FROM webpos.tbl_test WHERE TEST_STATUS in (3,4,8,9,10)");
			
			UserDAO udao = new UserDAO();
			StockDAO sdao = new StockDAO();
			CustomerDAO cdao = new CustomerDAO();
			
			ResultSet rs = statement.executeQuery();
			while(rs.next())
			{
				Test test = new Test();
				test.setId(rs.getInt("TEST_ID"));
				test.setDateAndTime(new Date(rs.getTimestamp("TEST_DATETIME").getTime()));
				test.setBooker(udao.getUser(rs.getInt("TEST_BOOKER_ID")));
				test.setTester(udao.getUser(rs.getInt("TEST_TESTER_ID")));
				test.setChecker(udao.getUser(rs.getInt("TEST_CHECKER_ID")));
				test.setItemType(sdao.getStockRecord(rs.getInt("TEST_STOCKRECORD_ID")));
				test.setSerialNumber(rs.getString("TEST_SERIAL"));
				test.setStatus(rs.getInt("TEST_STATUS"));
				test.setNotToBeFormatted(rs.getBoolean("TEST_DO_NOT_FORMAT"));
				test.setCustomer(cdao.getCustomer(rs.getInt("TEST_CUSTOMER_ID")));
			}
		}
		catch (Exception e) 
		{
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
		return tests;
	}
}
