package com.nt.jdbc;
//java app to create table
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTab {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		
		try {
			//loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//creating a connection between jdbc and database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","tiger");
			
			//creating jdbc statement
			if(con!=null)
				st = con.createStatement();
			
			//preparing sql qury to create table in the database 
			String query = "CREATE TABLE TEST(ID NUMBER(10),NAME VARCHAR2(10),LOC VARCHAR2(10))";
			System.out.println(query);    //checking the query
			
			int count = 0; //catch the results of executeupdate()
			//passing the sql query to execute
			if(st!=null)
				count  = st.executeUpdate(query);
				
			//processing the results 
			if(count == 0) 
				System.out.println("Table is created");
			else 
				System.out.println("Table is not created");
			
		}//try
		//this catch block is to handle th known exception
		catch(SQLException se) {
			
			if(se.getErrorCode()==955) {
				System.out.println("table is already created");
			}else if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("entered wrong column name or table name or wrong SQL keyword");
			}
			se.printStackTrace();
		}
		//this catch block is to handle th unknown exception
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing the jdbc objects
			try {
				if(st!=null) {
					st.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
