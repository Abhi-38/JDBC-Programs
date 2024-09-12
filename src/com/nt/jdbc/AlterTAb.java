package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AlterTAb {
	public static void main(String[] args) {
		
		Connection con = null;
		Statement st = null;
		
		try {
			//loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating jdbc statement
			if(con != null) 
				st = con.createStatement();
			
			//preparing sql query to execute
			String query = "ALTER TABLE TEST ADD COMPANY VARCHAR2(10)";
			System.out.println(query);
			//executing the query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			//processing the records
			if(count == 0)
				System.out.println("Records are updated");
			else
				System.out.println("records are not updated");	
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Entered wrong col name,tab name or SQL keyword");
			}
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing jdbc objects
			try {
				if(st!=null) {
					st.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null) {
					con.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
