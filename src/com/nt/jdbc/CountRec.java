package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//jdbc app to get column record from the table
public class CountRec {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//loading the class driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			System.out.println("We are establishing connection please wait.....");
			if(con == null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			
			//Thread.sleep(3000);
			System.out.println("Connection is established");
			
			//creating the statement
			if(con != null)
				st = con.createStatement();
			
			//creating the sql query
			String query = "SELECT COUNT(*) FROM EMP";
			System.out.println("query::: "+query);
			
			int count = 0;
			//putting the quer in executeQuery(-)
			if(st != null) {
				rs = st.executeQuery(query);
				//processing resultset records	
				if(rs != null) {
					rs.next();
					count = rs.getInt("COUNT(*)");
					System.out.println("Records count in the table are ::: "+count);
				}//if
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing the objects
			try {
				if(rs!=null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(st!=null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(con!=null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
		}//finally
	}//main
}//class
