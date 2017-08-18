package com.veronica.tm470.dbo;

public class StockItem 
{
	private int ID;
	private StockRecord stockRecord;
	private String name;
	private String serial;
	
	private double sellPrice;
	private double cashPrice;
	private double exchangePrice;
	
	private double boughtValue;
	
	private TransactionType transactionType;
	private boolean isFaulty;
	
	private String faultDetails;	
	
	public StockItem()
	{
		
	}
	
	public StockItem(StockRecord stockRecord)
	{
		this.stockRecord = stockRecord;
		this.name = stockRecord.getName();
		this.sellPrice = stockRecord.getSellPrice();
		this.cashPrice = stockRecord.getCashBuyPrice();
		this.exchangePrice = stockRecord.getExchangePrice();
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
	
	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getCashPrice() {
		return cashPrice;
	}

	public void setCashPrice(double cashPrice) {
		this.cashPrice = cashPrice;
	}

	public double getExchangePrice() {
		return exchangePrice;
	}

	public void setExchangePrice(double exchangePrice) {
		this.exchangePrice = exchangePrice;
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

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public double generateBoughtValue()
	{
		if(transactionType.equals(TransactionType.CASH))
		{
			return cashPrice;
		}
		else if(transactionType.equals(TransactionType.EXCHANGE))
		{
			return exchangePrice;
		}
		else
		{
			return 0;
		}
	}

	public double getBoughtValue() {
		return boughtValue;
	}

	public void setBoughtValue(double boughtValue) {
		this.boughtValue = boughtValue;
	}
	
	
}
