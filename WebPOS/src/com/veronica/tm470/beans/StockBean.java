package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.veronica.tm470.db.CategoryDAO;
import com.veronica.tm470.db.StockDAO;
import com.veronica.tm470.db.UserDAO;
import com.veronica.tm470.dbo.Category;
import com.veronica.tm470.dbo.StockRecord;
import com.veronica.tm470.dbo.User;

@ManagedBean
@SessionScoped
public class StockBean extends AbstractBean implements Serializable
{
	private String searchTerm;
	private String categoryName;
	private Map<Integer, Category> categories;
	private Map<String,Integer> dropdown;
	private int id; //ID of selected category
	private List<StockRecord> stockRecords;
		
	public void getStockRecordList()
	{
		StockDAO dao = new StockDAO();
		try
		{
			stockRecords = dao.getStockRecords(searchTerm);
		}
		catch(Exception e)
		{	
			e.printStackTrace();			
		}
	}
	
	
	public List<StockRecord> getStockRecords() {
		return stockRecords;
	}



	public void setStockRecords(List<StockRecord> stockRecords) {
		this.stockRecords = stockRecords;
	}



	public Map<String, Integer> getDropdown() 
	{
		return dropdown;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}
	
	public String getSearchTerm() 
	{
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) 
	{
		this.searchTerm = searchTerm;
	}
		
	
	public String getCategoryName()
	{
		return categoryName;
	}		
	
	public void setCategoryName(String categoryName) 
	{
		this.categoryName = categoryName;
	}

	public String createCategory()
	{
		CategoryDAO dao = new CategoryDAO();
		try 
		{
			dao.addCategory(categoryName);
			getCategories();
			return null;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	//Get a list of all categories in database
	public void getCategories()
	{
		CategoryDAO dao = new CategoryDAO();
		try 
		{
			categories = dao.getAllCategories();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			categories = null;
		}
	}
	
	//Generates a map to be used with populating the dropdown list of categories.
	public void populateDropdown()
	{
		getCategories();
		dropdown = new TreeMap<String,Integer>();
		for(Category c:categories.values())
		{
			dropdown.put(c.getName(), c.getId());
		}
	}
	
	public String deleteCategory()
	{
		CategoryDAO dao = new CategoryDAO();
		try 
		{
			dao.deleteCategory(id);
			getCategories();
			return null;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public String modifyCategory()
	{
		CategoryDAO dao = new CategoryDAO();
		try 
		{
			dao.updateCategory(id, categoryName);
			getCategories();
			return null;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	protected void clearForm() {
		// TODO Auto-generated method stub
		
	}
}
