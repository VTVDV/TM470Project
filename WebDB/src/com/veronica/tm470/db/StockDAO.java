package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.veronica.tm470.dbo.*;

public class StockDAO extends AbstractDAO
{
	public void addStockRecord(StockRecord stockRecord) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_record "
					+ "(REC_NAME,"
					+ "REC_CATEGORY,"
					+ "REC_SELL,"
					+ "REC_CASH,"
					+ "REC_EXCHANGE,"
					+ "REC_NOTE,"
					+ "REC_KEYWORDS,"
					+ "REC_REQ_SERIAL,"
					+ "REC_REQ_TEST) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, stockRecord.getName());
			statement.setInt(2, stockRecord.getCategoryID());
			statement.setDouble(3, stockRecord.getSellPrice());
			statement.setDouble(4, stockRecord.getCashBuyPrice());
			statement.setDouble(5, stockRecord.getExchangePrice());
			statement.setString(6, stockRecord.getNotes());
			statement.setString(7, stockRecord.getKeywords());
			statement.setInt(8, stockRecord.isRequiresSerial() ? 1 : 0 );
			statement.setInt(9, stockRecord.isRequiresTest() ? 1 : 0 );
			statement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("There was a problem inserting a stock record.");
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
