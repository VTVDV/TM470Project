package com.veronica.tm470.dbo;

import java.util.ArrayList;
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
	private List<StockItem> itemsSold;
	private List<StockItem> itemsBought;
	private List<StockItem> itemsExchanged;
	private double total;
	private String paymentMethod;
	
	public Transaction()
	{
		itemsSold = new ArrayList<>();
		itemsBought = new ArrayList<>();
		itemsExchanged = new ArrayList<>();
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public Date getDate()
	{
		return date;
	}
	public void setDate(Date date)
	{
		this.date = date;
	}
	public Date getTime()
	{
		return time;
	}
	public void setTime(Date time)
	{
		this.time = time;
	}
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	public Customer getCustomer()
	{
		return customer;
	}
	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	public List<StockItem> getItemsSold()
	{
		return itemsSold;
	}
	public void setItemsSold(List<StockItem> itemsSold)
	{
		this.itemsSold = itemsSold;
	}
	public List<StockItem> getItemsBought()
	{
		return itemsBought;
	}
	public void setItemsBought(List<StockItem> itemsBought)
	{
		this.itemsBought = itemsBought;
	}
	public List<StockItem> getItemsExchanged()
	{
		return itemsExchanged;
	}
	public void setItemsExchanged(List<StockItem> itemsExchanged)
	{
		this.itemsExchanged = itemsExchanged;
	}
	public double getTotal()
	{
		return total;
	}
	public void setTotal(double total)
	{
		this.total = total;
	}
	public String getPaymentMethod()
	{
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}
	
	
	
}
