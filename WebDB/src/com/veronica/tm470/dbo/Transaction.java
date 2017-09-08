package com.veronica.tm470.dbo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transaction
{
	private int id;
	private Date dateAndTime;
	private String date;
	private String time;
	//User who processed transaction.
	private User user;
	private Customer customer;
	private List<StockItem> items;
	
	private double buyTotal;
	private double exchangeTotal;
	private double sellTotal;
	private double total;
	
	private double cashPaid;
	private double cardPaid;
	private double voucherPaid;
	
	public Transaction()
	{
		items = new ArrayList<>();
	}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}

	public void generateDateAndTime()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		this.date= dateFormat.format(dateAndTime);
		SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		this.time= timeFormat.format(dateAndTime);
		
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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
			
	public double getBuyTotal() {
		return buyTotal;
	}

	public void setBuyTotal(double buyTotal) {
		this.buyTotal = buyTotal;
	}

	public double getExchangeTotal() {
		return exchangeTotal;
	}

	public void setExchangeTotal(double exchangeTotal) {
		this.exchangeTotal = exchangeTotal;
	}

	public double getSellTotal() {
		return sellTotal;
	}

	public void setSellTotal(double sellTotal) {
		this.sellTotal = sellTotal;
	}
	
	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getCashPaid() {
		return cashPaid;
	}

	public void setCashPaid(double cashPaid) {
		this.cashPaid = cashPaid;
	}

	public double getCardPaid() {
		return cardPaid;
	}

	public void setCardPaid(double cardPaid) {
		this.cardPaid = cardPaid;
	}

	public double getVoucherPaid() {
		return voucherPaid;
	}

	public void setVoucherPaid(double voucherPaid) {
		this.voucherPaid = voucherPaid;
	}

	public List<StockItem> getItems() {
		return items;
	}

	public void setItems(List<StockItem> items) {
		this.items = items;
	}
	
	
	
}
