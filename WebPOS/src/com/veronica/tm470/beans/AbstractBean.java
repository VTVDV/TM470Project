package com.veronica.tm470.beans;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public abstract class AbstractBean implements Serializable
{
	protected ResourceBundle getBundle()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		ResourceBundle bundle = app.getResourceBundle(context, "bundle");
		return bundle;
	}
	
	protected abstract void clearForm();
	
}