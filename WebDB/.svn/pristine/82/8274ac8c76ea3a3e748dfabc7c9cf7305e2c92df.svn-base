package com.veronica.tm470.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public abstract class AbstractDAO 
{
	private Connection connection;	
	
	protected Connection getConnection() throws Exception 
	{
		InitialContext context = new InitialContext();
		DataSource ds = (DataSource) context.lookup("java:jboss/datasources/webds");
		return ds.getConnection();
	}
	
	protected void close(Statement statement, Connection connection) throws Exception
	{
		if(statement!=null)
		{
			statement.close();
		}
		if(connection!=null)
		{
			connection.close();
			
		}
	}
}


