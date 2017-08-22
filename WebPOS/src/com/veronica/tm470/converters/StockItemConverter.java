package com.veronica.tm470.converters;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.veronica.tm470.beans.SalesBean;
import com.veronica.tm470.dbo.StockItem;

@FacesConverter("stockItemConverter")
public class StockItemConverter implements Converter
{
	@ManagedProperty(value = "#{salesBean}")
	private SalesBean salesBean;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		for (StockItem s: salesBean.getTransaction().getItems())
		{
			if(s.getID() == Integer.getInteger(value))
			{
				return s;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object stockItem) {
		if(stockItem != null)
		{
			return String.valueOf(((StockItem) stockItem).getID());
					
		}
		else
		{
			return null;
		}
		
	}
	
}
