package com.veronica.tm470.utility;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.veronica.tm470.db.StockDAO;
import com.veronica.tm470.dbo.StockRecord;

@FacesConverter("stockRecordConverter")
public class StockRecordConverter implements Converter
{
	public Object getAsObject(FacesContext context, UIComponent component, String value)
	{
		if(value != null && value.trim().length() > 0)
		{
			StockDAO dao = new StockDAO();
			StockRecord stockRecord = new StockRecord();
			try
			{
				stockRecord = dao.getStockRecord(Integer.parseInt(value));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return stockRecord;
		}
		
		return null;
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object stockRecord)
	{
		if(stockRecord != null)
		{
			return String.valueOf(( (StockRecord) stockRecord).getId());
		}
		return null;
	}
}
