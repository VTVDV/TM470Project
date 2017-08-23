package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

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
					+ "TEST_DO_NOT_FORMAT) "
					+ "VALUES (?, ?, ?, ?, ?, ?)");
			int parameterCount = 1;
			java.sql.Timestamp datetime = new java.sql.Timestamp(new java.util.Date().getTime());
			statement.setTimestamp(parameterCount++, datetime);
			statement.setInt(parameterCount++, test.getBooker().getId());
			statement.setInt(parameterCount++, test.getItemType().getId());
			statement.setString(parameterCount++, test.getSerialNumber());
			statement.setInt(parameterCount++, test.getStatus());
			statement.setInt(parameterCount++, test.isNotToBeFormatted() ? 1 : 0);
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
