package com.veronica.tm470.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

import com.veronica.tm470.db.StockDAO;
import com.veronica.tm470.dbo.Customer;
import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.Transaction;
import com.veronica.tm470.dbo.TransactionType;

@ManagedBean
@SessionScoped
public class SalesBean extends AbstractBean
{
	private List<StockRecord> stockRecords;
	private StockRecord selectedRecord;
	private Transaction transaction;
	private String serialNumber;
	
	private StockItem selectedStockItem;
	private double discount;
	
	@ManagedProperty(value="#{customerBean}")
	private CustomerBean customerBean;
	
	private int quantity;

	public String getAllStockRecords()
	{
		stockRecords = new ArrayList<>();
		StockDAO dao = new StockDAO();
		try
		{
			stockRecords = dao.getStockRecords();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
	
	public String sellStockItem()
	{
		return null;
	}
	
	public String buyStockItem()
	{
		transactionCheck();	
		if(selectedRecord.isRequiresSerial() && StringUtils.isEmpty(serialNumber))
		{
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "A serial number is required for this item!!"));
		}
		if(selectedRecord.isRequiresTest())
		{
			FacesContext.getCurrentInstance().addMessage("stockMessage", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "This item must be tested! Please book it in for a test."));
		}
		else
		{
			StockItem stockItem = new StockItem(selectedRecord);
			stockItem.setTransactionType(TransactionType.CASH);
			stockItem.generateBoughtValue();
			transaction.getItems().add(stockItem);
			transaction.setCustomer(customerBean.getSelectedCustomer());
		}
		return null;
	}	

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public String settle()
	{
		return null;	
	}

	public Transaction getTransaction()
	{
		return transaction;
	}

	public void setTransaction(Transaction transaction) 
	{
		this.transaction = transaction;
	}
	
	public CustomerBean getCustomerBean() 
	{
		return customerBean;
	}

	public void setCustomerBean(CustomerBean customerBean) 
	{
		this.customerBean = customerBean;
	}

	public void transactionCheck() 
	{
		if (transaction == null)
		{
			transaction = new Transaction();
		}
	}
	
	public String discardTransaction()
	{
 		this.transaction = null;
		customerBean.setSelectedCustomer(null);
		return null;
	}
	
	public String setPrice()
	{
		selectedStockItem.setSellPrice(discount);
		return null;
	}
	
	public String discountPrice()
	{
		double percentageDiscount =  selectedStockItem.getSellPrice()*((100-discount)/100);
		selectedStockItem.setSellPrice(percentageDiscount);
		return null;
	}
	
	public String deductDiscount()
	{
		selectedStockItem.setSellPrice(selectedStockItem.getSellPrice()-discount);
		return null;
	}

	@Override
	protected void clearForm() {
		// TODO Auto-generated method stub
		
	}
	
}