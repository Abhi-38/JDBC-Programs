package com.nt.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsoleApp {
public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//taking input from user 
			Console cons = System.console();
			String user = null;
			String pwd = null;
			if(cons!=null) {
				System.out.println("Enter the user name::: ");
				user = cons.readLine();
				System.out.println("Enter the password::: ");
				pwd = new String(cons.readPassword());
			}
			user = "'"+user+"'";
			pwd = "'"+pwd+"'";
			
			//lets load the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			if(cons!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating jdbc statement
			if(con!=null)
				st = con.createStatement();
			
			//preparing the sql query to execute
			String query = "SELECT COUNT(*) FROM IRCTC WHERE UNAME="+user+" AND PWD="+pwd;
			System.out.println(query);
			
			//executing the query
			int count = 0;
			if(st!=null) 
				rs = st.executeQuery(query);
			
			//processing the results
			if(rs!=null) {
				rs.next();//shifting the cursor from bfr to 1st record
				count = rs.getInt(1);//get 1st col value of the 1st record
				if(count==0)
					System.out.println("invalid credentials");
				else
					System.out.println("valid credentials");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();          //catching known exception
		}
		catch(Exception e) {
			e.printStackTrace();           //catching unknown exception
		}
		finally {
			//closing the jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}
