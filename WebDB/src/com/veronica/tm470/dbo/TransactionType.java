package com.veronica.tm470.dbo;

public enum TransactionType 
{
	SALE("Sale"), CASH("Cash"), EXCHANGE("Exchange"), TEST("Test"), DEPOSIT("Deposit"), BUYBACK("Buy Back"), MISC("Miscellaneous Item");
	
	String name;
	
	TransactionType(String name) 
	{
		this.name = name;
	}
	
	public String getName() 
	{
		return name;
	}
}


