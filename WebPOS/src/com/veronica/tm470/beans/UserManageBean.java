package com.veronica.tm470.beans;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import com.veronica.tm470.db.UserDAO;
import com.veronica.tm470.dbo.User;

@ManagedBean
@SessionScoped
public class UserManageBean extends AbstractBean implements Serializable 
{
	private String name;
	private String pass;
	private int userType;
	private int id;
	private Map<Integer,User> userMap;
	private Map<String,Integer>userNames;
	
	
	public Map<String, Integer> getUserNames() 
	{
		return userNames;
	}
	
	public Map<Integer, User> getUserMap() 
	{
		return userMap;
	}

	public void setUserMap(Map<Integer, User> userMap) 
	{
		this.userMap = userMap;
	}

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
	
	//Gets a map of users from database
	public void populateUsers()
	{
		UserDAO dao = new UserDAO();
		try 
		{
			userMap = dao.getUsers();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	//Formats map of users for use with a dropdown menu
	public void populateDropdown()
	{
		populateUsers();
		userNames = new HashMap<String,Integer>();
		for(User u:userMap.values())
		{
			userNames.put(u.getName(), u.getId());
		}
	}
	
	public String createUser()
	{
		UserDAO dao = new UserDAO();
		try 
		{
			dao.addUser(name, pass, userType);
			return "users";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public String modUser()
	{
		UserDAO dao = new UserDAO();
		try
		{
			dao.updateUser(name, pass, userType, id);
			populateDropdown();
			return "users";
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	protected void clearForm() {
		// TODO Auto-generated method stub
		
	}
}