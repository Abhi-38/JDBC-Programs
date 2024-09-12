package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//jdbc app to find the maximum salary
public class MaxSalApp {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//lets start with loading the class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//now establish the connection
			if(con == null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			
			//creating statement
			if(con!=null)
				st = con.createStatement();
			
			//preparing sql query
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL =(SELECT MAX(SAL) FROM EMP)";
			
			//putting the query in executeQury(-)
			if(st != null)
				rs = st.executeQuery(query);
			
			//processing the resultset records
			if(rs != null) {
				rs.next();
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}//if
				
		}//try
		catch(SQLException se) { //handling known exception
			se.printStackTrace();
		}//catch
		catch(Exception e) {     //handling unknown exception
			e.printStackTrace();
		}//catch
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
			
		}
	}
}
