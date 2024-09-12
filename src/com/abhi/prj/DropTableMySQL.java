package com.abhi.prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DropTableMySQL {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			//loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection
			con = DriverManager.getConnection("jdbc:mysql:///abhi","root","root");
			//writing the query to DROP the table in the Database
			String query = "DROP TABLE TEST";
			System.out.println(query);
			//creating jdbc statement
			if(con!=null)
				st = con.createStatement();
			//now execute the query
			int result = 0;
			if(st!=null)
				result = st.executeUpdate(query);
			//check the results
			if(result == 0)
				System.out.println("Table has been dropped");
			else
				System.out.println("Table is not dropped");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
