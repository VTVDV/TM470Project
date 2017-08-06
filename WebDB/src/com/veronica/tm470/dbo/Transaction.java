package com.veronica.tm470.dbo;

import java.util.Date;
import java.util.List;

public class Transaction 
{
	private int id;
	private Date date;
	private Date time;
	//User who processed transaction.
	private User user;
	private Customer customer;
	private List<StockItem> items;
	private double total;
	private String paymentMethod;
	
}
