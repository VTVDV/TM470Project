package com.veronica.tm470.beans;
import java.io.IOException;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import com.veronica.tm470.db.UserDAO;
import com.veronica.tm470.dbo.User;

@ManagedBean
@SessionScoped
public class UserBean extends AbstractBean implements Serializable 
{
	private String name;
	private String pass;
	private int userType;
	private int id;
	//Page to redirect user to.
	private String page;
	
	public int getUserType() 
	{
		return userType;
	}
	public void setUserType(int userType) 
	{
		this.userType = userType;
	}
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getPass() 
	{
		return pass;
	}
	public void setPass(String pass) 
	{
		this.pass = pass;
	}	
	public String getPage() 
	{
		return page;
	}
	public void setPage(String page) 
	{
		this.page = page;
	}
	
	public String validate()
	{
		try 
		{
			UserDAO dao = new UserDAO();
			User user = dao.validateUser(name, pass);
			if(user != null)
			{		
				this.userType = user.getUserType();
				this.id = user.getId();
				return page;				
			}
			else
			{
				FacesContext.getCurrentInstance().addMessage("login:userPass", new FacesMessage(getBundle().getString("welcome.invalid")));
				return null;
			}
		} 
		catch(Exception e)  
		{
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage("login:userPass", new FacesMessage(getBundle().getString("application.exception")));
			return null;
		}
	}		
	
	public void redirect(ComponentSystemEvent event) throws IOException
	{
		String page = (String) event.getComponent().getAttributes().get("page");
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "/faces/" + page + ".xhtml");
	}
	
	@Override
	protected void clearForm() {
		// TODO Auto-generated method stub
		
	}
}