package com.veronica.tm470.dbo;

import java.util.Date;

public class Test
{
	private int id;
	private Date date;
	private Date time;
	private String booker; //User who booked in item for test.
	private String tester; //User who tested item.
	private Customer customer;
	
	private StockRecord itemType;
	private String serialNumber;
	private boolean passed; //If the item has passed the test.
}
