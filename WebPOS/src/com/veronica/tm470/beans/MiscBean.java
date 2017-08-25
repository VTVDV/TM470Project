package com.veronica.tm470.beans;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.veronica.tm470.dbo.StockItem;
import com.veronica.tm470.dbo.TransactionType;

@ManagedBean
@SessionScoped
public class MiscBean extends AbstractBean implements Serializable
{
	private String name;
	private double sellPrice;
	
	@ManagedProperty(value = "#{salesBean}")
	private SalesBean salesBean;
	
	public String createMiscItem()
	{
		StockItem stockItem = new StockItem();
		stockItem.setName(name);
		stockItem.setSellPrice(sellPrice);
		stockItem.setID(salesBean.generateId());
		stockItem.setTransactionType(TransactionType.MISC);
		salesBean.transactionCheck();
		salesBean.getTransaction().getItems().add(stockItem);
		salesBean.updateTotals(stockItem);
		return null;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public SalesBean getSalesBean() {
		return salesBean;
	}

	public void setSalesBean(SalesBean salesBean) {
		this.salesBean = salesBean;
	}

	@Override
	protected void clearForm() {
		
	}
	
	

}
