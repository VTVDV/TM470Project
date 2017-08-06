package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.veronica.tm470.dbo.*;
import com.veronica.tm470.exceptions.WebConstants;
import com.veronica.tm470.exceptions.WebDBException;

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
					+ "REC_CATEGORY_ID,"
					+ "REC_SELL,"
					+ "REC_CASH,"
					+ "REC_EXCHANGE,"
					+ "REC_NOTE,"
					+ "REC_KEYWORDS,"
					+ "REC_REQ_SERIAL,"
					+ "REC_REQ_TEST,"
					+ "REC_SEARCHTERMS,"
					+ "REC_AGE_RATING) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			statement.setString(1, stockRecord.getName());
			statement.setInt(2, stockRecord.getCategory().getId());
			statement.setDouble(3, stockRecord.getSellPrice());
			statement.setDouble(4, stockRecord.getCashBuyPrice());
			statement.setDouble(5, stockRecord.getExchangePrice());
			statement.setString(6, stockRecord.getNotes());
			statement.setString(7, stockRecord.getKeywords());
			statement.setInt(8, stockRecord.isRequiresSerial() ? 1 : 0 );
			statement.setInt(9, stockRecord.isRequiresTest() ? 1 : 0 );
			statement.setString(10, stockRecord.getSearchTerms());
			statement.setInt(11, stockRecord.getAgeRating());
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
	
	public List<StockRecord> getStockRecords(String searchTerm) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		List<StockRecord> stockRecords = new ArrayList<>();
		try
		{
			connection = getConnection();
			
			String[] searchTermSplit = searchTerm.split("\\s+");
			StringBuilder builder = new StringBuilder();
			
			builder.append("SELECT * FROM webpos.tbl_record records ");
			builder.append("LEFT JOIN webpos.tbl_category categories ON records.REC_Category_ID = categories.CAT_ID ");
			
			for (int i = 0; i < searchTermSplit.length; i++ )
			{
				if (i == 0)
				{
					builder.append("WHERE records.REC_SEARCHTERMS LIKE ? ");
				}
				else
				{
					builder.append("AND records.REC_SEARCHTERMS LIKE ? ");
				}
			}
						
			statement = connection.prepareStatement(builder.toString());
			
			for (int i = 0; i < searchTermSplit.length; i++)
			{
				statement.setString(i + 1, "%" + searchTermSplit[i] + "%");
			}
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) 
			{
				StockRecord stockRecord = new StockRecord();
				stockRecord.setId(rs.getInt("REC_ID"));
				stockRecord.setName(rs.getString("REC_NAME"));
				Category category = new Category();
				category.setId(rs.getInt("CAT_ID"));
				category.setName(rs.getString("CAt_NAME"));				
				stockRecord.setCategory(category);
				stockRecord.setSellPrice(rs.getDouble("REC_SELL"));
				stockRecord.setCashBuyPrice(rs.getDouble("REC_CASH"));
				stockRecord.setExchangePrice(rs.getDouble("REC_EXCHANGE"));
				stockRecord.setNotes(rs.getString("REC_NOTE"));
				stockRecord.setKeywords(rs.getString("REC_KEYWORDS"));
				stockRecord.setRequiresSerial(rs.getBoolean("REC_REQ_SERIAL"));
				stockRecord.setRequiresTest(rs.getBoolean("REC_REQ_TEST"));
				stockRecord.setAgeRating(rs.getInt("REC_AGE_RATING"));
				stockRecord.setBarcode();
				stockRecord.setSearchTerms();
				stockRecords.add(stockRecord); 
			}			
			
		}
		catch(SQLException exception)
		{
			exception.printStackTrace();
			System.out.println("There was an error getting a list of stock records.");
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
		return stockRecords;
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
	
	public void deleteStockRecord(int id) throws WebDBException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try 
		{
			connection = getConnection();
			statement = connection.prepareStatement("DELETE FROM tbl_record WHERE REC_ID = ?");
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
	
	public void modifyStockRecord(StockRecord stockRecord) throws WebDBException
	{
		Connection connection = null;
		PreparedStatement statement = null;
		
		try 
		{
			connection = getConnection();
			String sql = "UPDATE tbl_record SET "
					+ "REC_NAME = ?, "
					+ "REC_CATEGORY_ID = ?, "
					+ "REC_SELL = ?, "
					+ "REC_CASH = ?, "
					+ "REC_EXCHANGE = ?,"
					+ "REC_NOTE = ?,"
					+ "REC_KEYWORDS = ?,"
					+ "REC_REQ_SERIAL = ?, "
					+ "REC_REQ_TEST = ?, "
					+ "REC_SEARCHTERMS = ?, "
					+ "REC_AGE_RATING = ?"					
					+ " WHERE REC_ID = ? ";
			statement = connection.prepareStatement(sql);
			int parameterCount = 1;
			statement.setString(parameterCount++, stockRecord.getName());
			statement.setInt(parameterCount++, stockRecord.getCategory().getId());
			statement.setDouble(parameterCount++, stockRecord.getSellPrice());
			statement.setDouble(parameterCount++, stockRecord.getCashBuyPrice());
			statement.setDouble(parameterCount++, stockRecord.getExchangePrice());
			statement.setString(parameterCount++, stockRecord.getNotes());
			statement.setString(parameterCount++, stockRecord.getKeywords());
			statement.setInt(parameterCount++, stockRecord.isRequiresSerial() ? 1 : 0 );
			statement.setInt(parameterCount++, stockRecord.isRequiresTest() ? 1 : 0 );
			statement.setString(parameterCount++, stockRecord.getSearchTerms());
			statement.setInt(parameterCount++, stockRecord.getAgeRating());
			statement.setInt(parameterCount++,stockRecord.getId());
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
