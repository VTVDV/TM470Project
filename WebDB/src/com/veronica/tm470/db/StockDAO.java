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
			statement.setString(2, stockRecord.getCategory());
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
	
	public void addStockItem(StockItem stockItem) throws Exception 
	{
		Connection connection = null;
		PreparedStatement statement = null;
				
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("INSERT INTO webpos.tbl_stock "
					+ "(STOCK_ID,"
					+ "STOCK_REC_ID,"
					+ "STOCK_NAME,"
					+ "STOCK_SERIAL,"
					+ "STOCK_IS_FAULTY,"
					+ "STOCK_FAULT_NOTE,"
					+ "STOCK_HAS_SOLD,"
					+ "STOCK_SOLD_CASH,"
					+ "STOCK_BOUGHT_CASH,"
					+ "STOCK_BOUGHT_EXCHANGE) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setInt(1, stockItem.getID());
			statement.setInt(2, stockItem.getStockRecord().getId());
			statement.setString(3, stockItem.getName());
			statement.setString(4, stockItem.getSerial());
			statement.setInt(5, stockItem.isFaulty() ? 1 : 0);
			statement.setString(6, stockItem.getFaultDetails());
			statement.setInt(7, stockItem.hasSold() ? 1 : 0);
			statement.setDouble(8, stockItem.getSold());
			statement.setDouble(9, stockItem.getBoughtCash());
			statement.setDouble(10, stockItem.getBoughtExchange());
			statement.executeUpdate();
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("There was a problem inserting a stock item.");
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
