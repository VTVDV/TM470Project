package com.veronica.tm470.dbo;

public class StockItem 
{
	private int id;	
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
	
	public StockItem(int id, StockRecord stockRecord, TransactionType transactionType)
	{
		this.stockRecord = stockRecord;
		this.name = stockRecord.getName();
		this.cashPrice = stockRecord.getCashBuyPrice();
		this.exchangePrice = stockRecord.getExchangePrice();
		this.sellPrice = stockRecord.getSellPrice();
		this.transactionType = transactionType;
		this.boughtValue = generateBoughtValue();
		this.id = id;
	}

	public StockRecord getStockRecord() {
		return stockRecord;
	}

	public void setStockRecord(StockRecord stockRecord) {
		this.stockRecord = stockRecord;
	}
	
	public int getID() 
	{
		return id;
	}

	public void setID(int iD) 
	{
		id = iD;
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
	
	public boolean isBeingSold()
	{
		return this.transactionType.equals(TransactionType.SALE)||
		this.transactionType.equals(TransactionType.MISC) ||
		this.transactionType.equals(TransactionType.DEPOSIT);
	}
	
	public boolean isBeingBought()
	{
		return this.transactionType.equals(TransactionType.EXCHANGE) ||
		this.transactionType.equals(TransactionType.CASH) ||
		this.transactionType.equals(TransactionType.TEST) ||
		this.transactionType.equals(TransactionType.BUYBACK);
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
		return this.boughtValue;
	}

	public void setBoughtValue(double boughtValue) {
		this.boughtValue = boughtValue;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this.id == ((StockItem) obj).getID())
		{
			return true;
		}
		return false;
	}
	
}
