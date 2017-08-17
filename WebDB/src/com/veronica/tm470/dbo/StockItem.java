package com.veronica.tm470.dbo;

public class StockItem 
{
	private int ID;
	private StockRecord stockRecord;
	private String name;
	private String serial;
	
	private double sold;
	private double boughtCash;
	private double boughtExchange;
	
	private boolean hasSold;
	private boolean isFaulty;
	
	private String faultDetails;	
	
	public StockItem()
	{
		
	}
	
	public StockItem(StockRecord stockRecord)
	{
		this.stockRecord = stockRecord;
		this.name = stockRecord.getName();
	}

	public StockRecord getStockRecord() {
		return stockRecord;
	}

	public void setStockRecord(StockRecord stockRecord) {
		this.stockRecord = stockRecord;
	}
	
	public int getID() 
	{
		return ID;
	}

	public void setID(int iD) 
	{
		ID = iD;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getSerial() 
	{
		return serial;
	}

	public void setSerial(String serial) 
	{
		this.serial = serial;
	}

	public double getSold() 
	{
		return sold;
	}

	public void setSold(double sold) 
	{
		this.sold = sold;
	}

	public double getBoughtCash() 
	{
		return boughtCash;
	}

	public void setBoughtCash(double boughtCash) 
	{
		this.boughtCash = boughtCash;
	}

	public double getBoughtExchange() 
	{
		return boughtExchange;
	}

	public void setBoughtExchange(double boughtExchange) 
	{
		this.boughtExchange = boughtExchange;
	}

	public boolean hasSold() 
	{
		return hasSold;
	}

	public void setHasSold(boolean hasSold) 
	{
		this.hasSold = hasSold;
	}

	public boolean isFaulty() 
	{
		return isFaulty;
	}

	public void setFaulty(boolean isFaulty) 
	{
		this.isFaulty = isFaulty;
	}

	public String getFaultDetails() 
	{
		return faultDetails;
	}

	public void setFaultDetails(String faultDetails) 
	{
		this.faultDetails = faultDetails;
	}
	
	
}
