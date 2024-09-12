package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Scanner;

public class LowSalApp {
	public static void main(String[] args) {
		
		//Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//loading the oracle driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			//creating statement
			if(con != null)
				st = con.createStatement();
			
			//creating sql query
			String query ="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL = (SELECT MIN(SAL) FROM EMP)";
			System.out.println(query);
			
			//providing the query
			if(st != null)
				rs = st.executeQuery(query);
			
			//processing resultset records
			
			if(rs != null) {
				rs.next();
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}//if
		}//try
		catch(SQLException se) {   //handling known exception
			se.printStackTrace();
		}
		catch(Exception e) {       // handling unknown exception
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
		}
	}
}
