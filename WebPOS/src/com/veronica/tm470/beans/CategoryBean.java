package com.veronica.tm470.beans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.veronica.tm470.db.CategoryDAO;
import com.veronica.tm470.db.UserDAO;
import com.veronica.tm470.dbo.Category;
import com.veronica.tm470.dbo.User;
import com.veronica.tm470.exceptions.WebConstants;
import com.veronica.tm470.exceptions.WebDBException;

@ManagedBean
@SessionScoped
public class CategoryBean extends AbstractBean implements Serializable
{
	private String categoryName;
	private Map<Integer, Category> categories;
	private Map<String,Integer> dropdown;
	private int id; //ID of selected category
		
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
		catch (WebDBException e) 
		{
			switch (e.getErrorCode()) {
				case WebConstants.CHILD_RECORD_FOUND:
					FacesContext.getCurrentInstance().addMessage("delete:catToDelete", new FacesMessage(getBundle().getString("cat.deleteException")));
				break;
			}
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
