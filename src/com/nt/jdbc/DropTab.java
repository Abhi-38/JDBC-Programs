package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTab {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		
		try {
			//loading jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection with the database
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating jdbc statement
			if(con !=null)
				st = con.createStatement();
			
			//creating an sql query
			String query = "DROP TABLE TEST PURGE";
			System.out.println(query);
			
			//now executing the query
			int count = 0;
			if(st!=null)
				count = st.executeUpdate(query);
			
			//process the results 
			if(count == 0)
				System.out.println("Table is dropped");
			else
				System.out.println("Table is not created");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("entered wrong table name or col name or sql keyword");
			}
			se.printStackTrace();
		}
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
		}
	}
}
