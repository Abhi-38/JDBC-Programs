package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//java app to delete record of given student
public class DeleteRecord {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			String city = null;
			//taking input from user
			sc = new Scanner(System.in);
			if(sc!=null)
				System.out.println("Enter the name city ::: ");
			city = sc.next().toUpperCase();  //user enters name of city
			city = "'"+city+"'";  //converting the string in sql string format 
			//loading the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//now we are establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating the statement 
			if(con!=null)
				st = con.createStatement();
			
			//now lets prepare sql query
			//DELETE FROM STUDENT WHERE SADD=&SADD;
			String query = "DELETE FROM STUDENT WHERE SADD="+city;
			System.out.println(query);   //for checking the query
			
			//send and execute the query
			int count = 0;
			if(st != null)
				count = st.executeUpdate(query);
			
			//to process the result
			if(count == 0)
				System.out.println("No records found");
			else
				System.out.println(count+ " rows are affected");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid cloumn name or table name");
			se.printStackTrace();
		}//handling known exception
		catch(Exception e) {
			e.printStackTrace();
		}//handling unknown exception
		finally {
			//closing jdbc objs
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc != null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
