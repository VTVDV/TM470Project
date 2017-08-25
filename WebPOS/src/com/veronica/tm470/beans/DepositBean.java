package com.veronica.tm470.beans;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.TransactionType;

@ManagedBean
@SessionScoped
public class DepositBean extends AbstractBean implements Serializable
{
	private StockRecord itemType;
	private Date customer;
	private double value;
	
	@ManagedProperty(value = "#{salesBean}")
	private SalesBean salesBean;
	
	@ManagedProperty(value = "#{customerBean}")
	private CustomerBean customerBean;
	
	public String createDeposit()
	{
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date expiry = new Date();
		expiry.setTime(calendar.getTimeInMillis());
		StockItem stockItem = new StockItem();
		stockItem.setTransactionType(TransactionType.DEPOSIT);
		stockItem.setName("DEPOSIT: " + itemType.getName() + " EXPIRES: " + format.format(expiry));
		stockItem.setSellPrice(value);
		stockItem.setID(salesBean.generateId());
		salesBean.transactionCheck();
		salesBean.getTransaction().getItems().add(stockItem);
		salesBean.updateTotals(stockItem);
		return null;
	}
		
	public StockRecord getItemType() {
		return itemType;
	}

	public void setItemType(StockRecord itemType) {
		this.itemType = itemType;
	}



	public Date getCustomer() {
		return customer;
	}

	public void setCustomer(Date customer) {
		this.customer = customer;
	}

	public SalesBean getSalesBean() {
		return salesBean;
	}

	public void setSalesBean(SalesBean salesBean) {
		this.salesBean = salesBean;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}
	
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	protected void clearForm() {
		
	}

}
