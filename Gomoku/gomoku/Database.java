package gomoku;

import java.io.*;
import java.sql.*;
import java.util.*;

import com.mysql.jdbc.Connection;

public class Database
{
	private Connection con;
	  //Add any other data fields you like – at least a Connection object is mandatory
	  public Database()
	  {
		  Properties prop = new Properties();
		   FileInputStream fis;
		try {
			fis = new FileInputStream("gomoku/db.properties");
			try {
			prop.load(fis);
			} catch (IOException e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   String url = prop.getProperty("url");
		   String user = prop.getProperty("user");
		   String pass = prop.getProperty("password");    
		  try {
			con = (Connection) DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	  
	  public ArrayList<String> query(String query)
	  {
		  Statement stmt;
		  ResultSet rs;
		  ResultSetMetaData rmd;
		  int no_col;
		  String add = "";
		  ArrayList<String> toReturn = new ArrayList<String>();
		  try {
			  stmt=con.createStatement();
			  rs = stmt.executeQuery(query);
			  rmd = rs.getMetaData();
			  no_col = rmd.getColumnCount();
		
		  	while (rs.next())
		  	{
		  		for (int i = 1; i <= no_col; i++)
		  		{
		  			add += rs.getString(i);
		  		}
			  toReturn.add(add);
			  add = "";
			}
		  } catch (SQLException e) 
		  {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
		  if (toReturn.isEmpty())
		  {
			  return null;
		  }
		  else
		  {
			  return toReturn;
		  }
	  }
	  
	  public boolean executeDML(String dml)
	  {
		  Statement stmt;
		  try {
			  stmt=con.createStatement();
			  stmt.execute(dml);
			  return true;
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  return false;
	  }
	  
	  public Connection getConnection()
	  {
		  return con;
	  }
}
