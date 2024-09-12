package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//this is first app using jdbc

public class FirstApp {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(System.in);
			String intChar = null;
			
			if(sc != null) {
				System.out.println("Enter the initial of employee: ");
				intChar = sc.next().toUpperCase();    						//taking user input here
			}
			intChar = "'"+intChar+"%'";									 // we have done this to get suitable with sql query
			
			System.out.println("Now we are moving to our query ");
			//we are now loading our class
			//Class.forName("oracle.jdbc.driver.OracleDriver);
			
																		//establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			
			if(con!=null)
				st = con.createStatement();     						//statement is created
			
			String query = "SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP WHERE ENAME LIKE "+intChar;  //creating a query
			System.out.println(query);        							 // to check the query
			System.out.println("Here are the records.....");
			if(st!=null) {
				rs = st.executeQuery(query);							//providing query to execute the executeQuery(-)
				boolean flag= false;
				if(rs != null) {
					while(rs.next()!=false) {
						flag = true;
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getInt(4));   //getting the data from table
					}													//end of while
					if(flag == false) {
						System.out.println("No records found");
					}													//end of if(data)
				}												//end of if(rs)
			}											//end of if(st)
			
		}											//end of try 
		catch(SQLException se) {										//catch block to handle known exception
			if(se.getErrorCode()>=900 && se.getErrorCode()<999) 
				System.out.println("Invalid column or table name");
			se.printStackTrace();
		}			
		
		catch(Exception e) {											//catch block to handle unknown exception
			e.printStackTrace();
		}
		
		finally {
			try {
				if(rs!=null)
					rs.close();			//closing ResultSet object connection
			}												//end of try
			catch(SQLException se) {
				se.printStackTrace();
			}												//end of catch	
			//////////////////////
			try {
				if(st!=null)
					st.close();			//closing statement object connection
			}												//end of try
			catch(SQLException se) {
				se.printStackTrace();	
			}												//end of catch
			//////////////////////
			try {
				if(con !=null)
					con.close();		//closing connection object connection
			}												//end of try
			catch(SQLException se){
				se.printStackTrace();
			}												//end of catch
			//////////////////////
			try {
				if(sc != null)
					sc.close();			//closing the scanner object connection
			}												//end of try
			catch(Exception e) {
				e.printStackTrace();
			}												//end of catch
		}
	}
}
