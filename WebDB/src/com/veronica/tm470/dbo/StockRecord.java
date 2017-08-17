package com.veronica.tm470.dbo;

import java.util.ArrayList;
import java.util.Random;

public class StockRecord 
{
	private int id;
	private int ageRating;
	private Category category;
	private String name;
	private String barcode;
	
	private ArrayList<StockItem> stockItems;
	
	private double sellPrice;
	private double cashBuyPrice;
	private double exchangePrice;
	
	private String notes;
	private String keywords;
	private String searchTerms;
	
	private boolean requiresSerial;
	private boolean requiresTest;
		
	public int getId() 
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
		
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
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

	public ArrayList<StockItem> getStockItems() 
	{
		return stockItems;
	}

	public void setStockItems(ArrayList<StockItem> stockItems) 
	{
		this.stockItems = stockItems;
	}

	public String getSearchTerms() {
		return searchTerms;
	}

	public void setSearchTerms() {
		this.searchTerms = keywords + " " + name + " " + barcode + category.getName();
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode() 
	{
		this.barcode = String.format("%012d", id);
	}

	public void setSearchTerms(String searchTerms) 
	{
		this.searchTerms = searchTerms;
	}

	public int getAgeRating()
	{
		return ageRating;
	}

	public void setAgeRating(int ageRating)
	{
		this.ageRating = ageRating;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this.id == ((StockRecord) obj).getId())
		{
				return true;
		}
		return false;
	}
}
