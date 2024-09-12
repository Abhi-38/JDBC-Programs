package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelect {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//taking input from user 
			sc = new Scanner(System.in);
			int no = 0;
			String query = null;
			
			if(sc!=null) {
				System.out.println("Enter your select/non-select query ::: ");
				query = sc.nextLine();
			}//reads input
			
			//lets load the jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//creating the connection 
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating statement to execute rthe query
			if(con!=null)
				st =con.createStatement();
			
			if(st!=null) {
				boolean flag = st.execute(query);
				if(flag==true) {
					System.out.println("Select sql query executed");
					//gater and process result
					rs = st.getResultSet();
					//process resultset
					if(rs!=null) {
						while(rs.next()) {
							System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
						}//while
					}//if
				}
				else {
					System.out.println("Non select query executed");
					no = st.getUpdateCount();  //long no = st.getLargeUpdateCount();
					System.out.println("No of records that are affected: "+no);
				}//else
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
