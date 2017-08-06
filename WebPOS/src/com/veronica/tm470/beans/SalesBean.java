package com.veronica.tm470.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.veronica.tm470.dbo.StockRecord;

@ManagedBean
@SessionScoped
public class SalesBean 
{
	private List<StockRecord> stockRecords;
	private StockRecord selectedRecord;
	
	private int quantity;
}
