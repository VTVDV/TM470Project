package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.veronica.tm470.db.CategoryDAO;
import com.veronica.tm470.db.StockDAO;
import com.veronica.tm470.dbo.Category;
import com.veronica.tm470.dbo.StockRecord;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean
@SessionScoped
public class StockRecordBean extends AbstractBean implements Serializable
{
	private int id;
	private Category category;
	private int categoryId;
	private String name;
	
	private double sellPrice;
	private double cashBuyPrice;
	private double exchangePrice;
	
	private String notes;
	private String keywords;
	private String searchTerm;
	
	private boolean requiresSerial;
	private boolean requiresTest;
	
	private int ageRating;
	
	//search = What the user is searching for.
	private String search;
	private StockRecord selectedRecord;
	private List<StockRecord> stockRecords;
		
	//Creates a new Stock Record
	public String create()
	{
		StockDAO dao = new StockDAO();
		try 
		{
			getCategoryObject();			
			StockRecord stockRecord = new StockRecord();
			stockRecord.setName(name);
			stockRecord.setCategory(category);
			stockRecord.setSellPrice(sellPrice);
			stockRecord.setCashBuyPrice(cashBuyPrice);
			stockRecord.setExchangePrice(exchangePrice);
			stockRecord.setNotes(notes);
			stockRecord.setKeywords(keywords);
			stockRecord.setRequiresSerial(requiresSerial);
			stockRecord.setRequiresTest(requiresTest);
			stockRecord.setAgeRating(ageRating);
			stockRecord.setSearchTerms();
			dao.addStockRecord(stockRecord);
			return "stockManagement";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			clearForm();
		}
	}
	
	public String deleteRecord()
	{
		System.out.println(id);
		StockDAO dao = new StockDAO();
		try 
		{
			dao.deleteStockRecord(id);
			stockRecords = null; 
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally
		{
			clearForm();
		}
		return null;
	}
	
	public String modifyRecord()
	{
		StockDAO dao = new StockDAO();
		try
		{
			getCategoryObject();
			StockRecord stockRecord = new StockRecord();
			stockRecord.setName(name);
			stockRecord.setId(id);
			stockRecord.setCategory(category);
			stockRecord.setSellPrice(sellPrice);
			stockRecord.setCashBuyPrice(cashBuyPrice);
			stockRecord.setExchangePrice(exchangePrice);
			stockRecord.setNotes(notes);
			stockRecord.setKeywords(keywords);
			stockRecord.setRequiresSerial(requiresSerial);
			stockRecord.setRequiresTest(requiresTest);
			stockRecord.setSearchTerms();
			stockRecord.setAgeRating(ageRating);
			dao.modifyStockRecord(stockRecord);
			stockRecords = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			clearForm();
		}
		return null;
	}
	
	//Uses id from dropdown to retrieve and assign category.
	public void getCategoryObject()
	{
		CategoryDAO catDao = new CategoryDAO();
		try
		{
			category = catDao.getCategory(categoryId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String generateKeywords()
	{
		 return keywords = keywords + " " + name + " " + category.getName();
	}
	
	public void getStockRecordList()
	{
		StockDAO dao = new StockDAO();
		try
		{
			stockRecords = dao.getStockRecords(search);
		}
		catch(Exception e)
		{	
			e.printStackTrace();			
		}
	}	
	
	public List<StockRecord> getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(List<StockRecord> stockRecords) {
		this.stockRecords = stockRecords;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getCategoryId() 
	{
		return categoryId;
	}

	public void setCategoryId(int categoryId) 
	{
		this.categoryId = categoryId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public double getSellPrice() 
	{
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) 
	{
		this.sellPrice = sellPrice;
	}

	public double getCashBuyPrice() 
	{
		return cashBuyPrice;
	}

	public void setCashBuyPrice(double cashBuyPrice) 
	{
		this.cashBuyPrice = cashBuyPrice;
	}

	public double getExchangePrice() 
	{
		return exchangePrice;
	}

	public void setExchangePrice(double exchangePrice) 
	{
		this.exchangePrice = exchangePrice;
	}

	public String getNotes() 
	{
		return notes;
	}

	public void setNotes(String notes) 
	{
		this.notes = notes;
	}

	public String getKeywords() 
	{
		return keywords;
	}

	public void setKeywords(String keywords) 
	{
		this.keywords = keywords;
	}

	public boolean isRequiresSerial() 
	{
		return requiresSerial;
	}

	public void setRequiresSerial(boolean requiresSerial) 
	{
		this.requiresSerial = requiresSerial;
	}

	public boolean isRequiresTest() 
	{
		return requiresTest;
	}

	public void setRequiresTest(boolean requiresTest) 
	{
		this.requiresTest = requiresTest;
	}	
	
	public StockRecord getSelectedRecord() 
	{
		return selectedRecord;
	}

	public void setSelectedRecord(StockRecord selectedRecord) 
	{
		this.selectedRecord = selectedRecord;
	}
	
	public String getSearchTerm() 
	{
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) 
	{
		this.searchTerm = searchTerm;
	}

	@Override
	protected void clearForm() 
	{
		category = null;
		categoryId = 0;
		name = null;
		sellPrice = 0;
		cashBuyPrice = 0;
		exchangePrice = 0;
		notes = null;
		keywords = null;
		requiresSerial = false;
		requiresTest = false;
		stockRecords = null;
	}

	public String getSearch() 
	{
		return search;
	}

	public void setSearch(String search) 
	{
		this.search = search;
	}

	public int getAgeRating() 
	{
		return ageRating;
	}

	public void setAgeRating(int ageRating) 
	{
		this.ageRating = ageRating;
	}
	
	
	
	
}
