package com.peerius.utils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

public class DBConnector {
	
	public  Properties connectionProps;
	String dbhost;
	String username;
	String password;
	List <String> results;
	
	public static Connection connection;
	public static Driver driver;
	public static  Statement userQuery ;
	public static  ResultSet queryResult;
	
	
	public String connectUrl(){
		dbhost="jdbc:sqlserver://testdb:1433";
		
		return dbhost;
	}
	
	
	public Properties connectDetails(){
		
		username ="3duser";
		password="3d534rch";
		
		connectionProps = new Properties();
		connectionProps.put("username", this.username);
		connectionProps.put("password", this.password);
		

		return connectionProps;
	}
	
	
	private Connection getConnection(){
		
		try {
			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
			connection = DriverManager.getConnection(connectUrl(), connectDetails());
			if(connection!=null) System.out.println("Connection Successful!");
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
			 System.out.println("Error Trace in getConnection() : " + e.getMessage());
			
		} 
		
		
		return connection;
	}
	
	private void closeConnection(){
        try{
             if(connection!=null)
                  connection.close();
             connection=null;
        }catch(Exception e){
             e.printStackTrace();
        }
   }
	
	public List<String> userQuery(String query){
		
		try {
			
			userQuery = this.getConnection().createStatement();
			queryResult = userQuery.executeQuery(query);
			
				
				for(String result:results){
					while(queryResult.next()) {
						
						queryResult.getMetaData();
						results.add(result);
						
					}
					
					
				}
				
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return results;
		
	}
	

			
			
	public static void main(String[]args){
		
	DBConnector testConnector = new  DBConnector();
	testConnector.getConnection();
	//testConnector.userQuery("SELECT TOP 10 * FROM [qaLive0].[dbo].[C_Product]");
		
		
	}
	

}
