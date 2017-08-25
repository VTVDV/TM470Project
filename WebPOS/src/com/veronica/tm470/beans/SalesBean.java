package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;
import org.primefaces.event.SelectEvent;

import com.veronica.tm470.db.StockDAO;
import com.veronica.tm470.dbo.Customer;
import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.Transaction;
import com.veronica.tm470.dbo.TransactionType;

@ManagedBean
@SessionScoped
public class SalesBean extends AbstractBean implements Serializable {
	private List<StockRecord> stockRecords;
	private StockRecord selectedRecord;
	private Transaction transaction;
	private String serialNumber;

	private StockItem selectedStockItem;
	private double discount;
	private double buyTotal;
	private double exchangeTotal;
	private double sellTotal;
	private double total;
	
	private int id;

	@ManagedProperty(value = "#{customerBean}")
	private CustomerBean customerBean;

	private int quantity;

	public String getAllStockRecords() {
		stockRecords = new ArrayList<>();
		StockDAO dao = new StockDAO();
		try {
			stockRecords = dao.getStockRecords();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String settleCash() {
		return null;
	}

	public List<StockRecord> getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(List<StockRecord> stockRecords) {
		this.stockRecords = stockRecords;
	}

	public StockRecord getSelectedRecord() {
		return selectedRecord;
	}

	public void setSelectedRecord(StockRecord selectedRecord) {
		this.selectedRecord = selectedRecord;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	//NOTE TO SELF! QUANTITY CHECK TO BE PERFORMED IN IF STATEMENT!
	public String sellStockItem() {
		transactionCheck();
		if (selectedRecord.isRequiresSerial() && StringUtils.isEmpty(serialNumber)) {
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "A serial number is required for this item!!"));
		} else {
			StockItem stockItem = new StockItem(generateId() ,selectedRecord, TransactionType.SALE);
			transaction.getItems().add(stockItem);
			stockItem.setSellPrice(selectedRecord.getSellPrice());
			updateTotals(stockItem);
			transaction.setCustomer(customerBean.getSelectedCustomer());
		}
		return null;
	}

	public String buyStockItem() {
		transactionCheck();
		if (selectedRecord.isRequiresSerial() && StringUtils.isEmpty(serialNumber)) {
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "A serial number is required for this item!!"));
		}
		if (selectedRecord.isRequiresTest()) {
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "This item must be tested! Please book it in for a test."));
		}  else {
			StockItem stockItem = new StockItem(generateId() ,selectedRecord, TransactionType.CASH);
			transaction.getItems().add(stockItem);
			updateTotals(stockItem);
			transaction.setCustomer(customerBean.getSelectedCustomer());
		}
		return null;
	}

	public String exchangeStockItem() {
		transactionCheck();
		if (selectedRecord.isRequiresSerial() && StringUtils.isEmpty(serialNumber)) {
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "A serial number is required for this item!!"));
		}
		if (selectedRecord.isRequiresTest()) {
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error!", "This item must be tested! Please book it in for a test."));
		} else {
			StockItem stockItem = new StockItem(generateId(), selectedRecord, TransactionType.EXCHANGE);
			transaction.getItems().add(stockItem);
			updateTotals(stockItem);
			transaction.setCustomer(customerBean.getSelectedCustomer());
		}
		return null;
	}

	public void updateTotals(StockItem stockItem)
	{
		if(stockItem.getTransactionType().equals(TransactionType.CASH))
		{
			transaction.setBuyTotal(transaction.getBuyTotal() + stockItem.getBoughtValue());
			this.buyTotal = transaction.getBuyTotal();
			
		}
		else if(stockItem.getTransactionType().equals(TransactionType.EXCHANGE))
		{
			transaction.setExchangeTotal(transaction.getExchangeTotal() + stockItem.getBoughtValue());
			this.exchangeTotal = transaction.getExchangeTotal();
		}
		else if(stockItem.isBeingSold())
		{
			transaction.setSellTotal(transaction.getSellTotal() + stockItem.getSellPrice());
			this.sellTotal = transaction.getSellTotal();
		}
		this.total = this.sellTotal - (this.buyTotal + this.exchangeTotal);
	}
	
	public void updateTotals()
	{
		this.total = this.buyTotal - this.exchangeTotal + this.sellTotal;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String voidItem() 
	{
		if(this.selectedStockItem.getTransactionType().equals(TransactionType.CASH))
		{
			this.buyTotal = this.buyTotal - selectedStockItem.getBoughtValue();
		}
		if(this.selectedStockItem.getTransactionType().equals(TransactionType.EXCHANGE))
		{
			this.exchangeTotal = this.exchangeTotal - selectedStockItem.getBoughtValue();
		}
		if(this.selectedStockItem.isBeingSold())
		{
			this.sellTotal = this.sellTotal - selectedStockItem.getSellPrice();
		}
		this.transaction.getItems().remove(selectedStockItem);
		updateTotals();
		return null;
	}
	
	public int generateId()
	{
		id++;
		return id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public CustomerBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}

	public void transactionCheck() {
		if (transaction == null) {
			transaction = new Transaction();
		}
	}

	public String discardTransaction() {
		this.transaction = null;
		customerBean.setSelectedCustomer(null);
		clearForm();
		return null;
	}

	public String setPrice() {
		selectedStockItem.setSellPrice(discount);
		this.discount = 0;       
		return null;
	}

	//Deducts percentage form
	public String discountPrice() {
		double percentageDiscount = selectedStockItem.getSellPrice() * ((100 - discount) / 100);
		selectedStockItem.setSellPrice(percentageDiscount);
		this.discount = 0;
		return null;
	}

	public String deductDiscount() {
		selectedStockItem.setSellPrice(selectedStockItem.getSellPrice() - discount);
		this.discount = 0;
		return null;
	}
	
	public double getBuyTotal() {
		return buyTotal;
	}

	public void setBuyTotal(double buyTotal) {
		this.buyTotal = buyTotal;
	}

	public double getSellTotal() {
		return sellTotal;
	}

	public void setSellTotal(double sellTotal) {
		this.sellTotal = sellTotal;
	}

	public double getExchangeTotal() {
		return exchangeTotal;
	}

	public void setExchangeTotal(double exchangeTotal) {
		this.exchangeTotal = exchangeTotal;
	}

	public StockItem getSelectedStockItem() {
		return selectedStockItem;
	}

	public void setSelectedStockItem(StockItem selectedStockItem) {
		this.selectedStockItem = selectedStockItem;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	protected void clearForm() {
		this.serialNumber = null;
		this.buyTotal = 0;
		this.exchangeTotal = 0;
		this.sellTotal = 0;
		this.total = 0;
		this.quantity = 0;
	}
	
	public void onRowSelect(SelectEvent event)
	{
		this.selectedStockItem = ((StockItem)  event.getObject());
	}
	
	public boolean selectedItemIsSale()
	{
		return selectedStockItem.getTransactionType().equals(TransactionType.SALE);
	}

}