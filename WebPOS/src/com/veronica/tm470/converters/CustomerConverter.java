package com.veronica.tm470.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang.StringUtils;

import com.veronica.tm470.db.CustomerDAO;
import com.veronica.tm470.dbo.Customer;

@FacesConverter("customerConverter")
public class CustomerConverter implements Converter
{
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		if(!StringUtils.isEmpty(value))
		{
			CustomerDAO dao = new CustomerDAO();
			Customer customer = new Customer();
			try 
			{
				customer = dao.getCustomer(Integer.parseInt(value));
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			return customer;
		}	
		return null;	
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object customer)
	{
		if(customer != null)
		{
			return String.valueOf(( (Customer) customer).getId());
		}
		return null;	
	}
}