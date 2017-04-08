package com.veronica.tm470.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StockBean extends AbstractBean implements Serializable
{
	private String searchTerm;
	
	public String getSearch()
	{
		return searchTerm;
	}

	public String getSearchTerm() 
	{
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) 
	{
		this.searchTerm = searchTerm;
	}
	
	
}
