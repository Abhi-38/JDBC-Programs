package com.abhi.prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//jdbc app to create table in mysql db
public class CreateTableMySQL {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		try {
			//loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection
			con = DriverManager.getConnection("jdbc:mysql:///abhi","root","root");
			//writing the query to create the table in the Database
			String query = "CREATE TABLE TEST(ID INT,NAME VARCHAR(10),LOCATION VARCHAR(10))";
			System.out.println(query);
			//creating jdbc statement
			if(con!=null)
				st = con.createStatement();
			//now executing the query
			int result = 0;
			if(con!=null)
				result = st.executeUpdate(query);
			//now checking the result to see if the table is created or not
			if(result == 0) {
				System.out.println("Table is created");
			}
			else {
				System.err.println("Table not created");
			}
			
		}//try
		catch(SQLException se) {
			//this block is used to handle known exceptions
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			//this catch block handles unknown exception
			e.printStackTrace();
		}//catch
		finally {
			//closing the statement obj
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
			//closing connection obj
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
