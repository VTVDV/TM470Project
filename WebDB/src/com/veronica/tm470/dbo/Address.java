package com.veronica.tm470.dbo;

public class Address 
{
	private String houseNumber;	//String as some people have named houses.
	private String buildingName; //Optional
	private String streetName;
	private String city; //Use for town/city/village.
	private String county;
	private String postcode;	
	
	public Address()
	{
		
	}
	
	public Address(String houseNumber, String buildingName, String streetName, String city, String county, String postcode)
	{
		this.houseNumber = houseNumber;
		this.buildingName = buildingName;
		this.streetName = streetName;
		this.city = city;
		this.county = county;
		this.postcode = postcode;
	}
	
	public String getHouseNumber()
	{
		return houseNumber;
	}
	public void setHouseNumber(String houseNumber)
	{
		this.houseNumber = houseNumber;
	}
	public String getBuildingName()
	{
		return buildingName;
	}
	public void setBuildingName(String buildingName)
	{
		this.buildingName = buildingName;
	}
	public String getStreetName()
	{
		return streetName;
	}
	public void setStreetName(String streetName)
	{
		this.streetName = streetName;
	}
	public String getCity()
	{
		return city;
	}
	public void setCity(String city)
	{
		this.city= city;
	}
	public String getCounty()
	{
		return county;
	}
	public void setCounty(String county)
	{
		this.county = county;
	}
	public String getPostcode()
	{
		return postcode;
	}
	public void setPostcode(String postcode)
	{
		this.postcode = postcode;
	}
	
	
}
