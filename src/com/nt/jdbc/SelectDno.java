package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//app to take deptno from user and give the table details
import java.util.Scanner;

public class SelectDno {
	public static void main(String[] args) {
		//for taking input from user we are using scanner
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//create scanner object
			
			sc = new Scanner(System.in);
			
			System.out.println("Enter department number::: ");
			//taking input from user
			int dno = sc.nextInt();
			
			//loading the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			// we are commenting this because there is autoloading of driver in ojdbc8
			
			//establishing the connection
			System.out.println("we are establishing the connection please wait");
			if(sc != null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			
			Thread.sleep(3000);
			System.out.println("Connection has been established !!!");
			
			//creating the statement
			if(con != null)
				st = con.createStatement();
			//statement is created
			
			//preparing the sql query
			String query = "SELECT DEPTNO,DNAME,LOC FROM DEPT WHERE DEPTNO ="+dno;
			System.out.println(query);
			//now execute the query using executeQuery(-)
			
			if(st != null) 
				rs = st.executeQuery(query);
			System.out.println("The details are::: ");
			//process resultset record
			if(rs != null) {
				if(rs.next())
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
				else
					System.out.println("records not found");
			}//if
		}//try
		catch(SQLException se) {    //handling known exception
			se.printStackTrace();
		}
		catch(Exception e) {        //handling unknown exception
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
			
			try {
				if(sc!=null)
					rs.close();
			}//try
			catch(Exception e) {
				e.printStackTrace();
			}//catch
		}//finally
	}//main
}//SelectDno
