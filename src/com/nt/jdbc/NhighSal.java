package com.nt.jdbc;
//java application to find the nth highest salary
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NhighSal {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			System.out.println("Enter the number::: ");
			int num = sc.nextInt();
			//input provided by user
			
			//loading the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//now we are establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","mydb9am","mydb9am");
			
			//creating the statement 
			if(con!=null)
				st = con.createStatement();
			
			//lets create the query to find the nth highest salary from emp
			String query = " SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP E1 WHERE ("+num+" - 1)= (SELECT COUNT(DISTINCT SAL) FROM EMP E2 WHERE E2.SAL>E1.SAL)";
			System.out.println("The query is::: ");
			System.out.println(query);
			
			//now lets put this query to execute
			if(st!=null)
				rs = st.executeQuery(query);
			System.out.println("The "+num+"th highest query is as follows::: ");
			System.out.println();
			
			//processing for resultset records
			if(rs != null) {
				rs.next();
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
			}//if
			System.out.println();
			System.out.println("This is your requested record.");
		}//try
		catch(SQLException se) {       //to handle known exception
			se.printStackTrace();
		}
		catch(Exception e) {           // to handle unknown exception
			e.printStackTrace();
		}
		finally {
			//closing the connection of objects
			try {
				if(rs != null)
					rs.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(st != null)
					st.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(con != null)
					con.close();
			}//try
			catch(SQLException se) {
				se.printStackTrace();
			}//catch
			
			try {
				if(sc != null)
					sc.close();
			}//try
			catch(Exception se) {
				se.printStackTrace();
			}//catch
		}
	}
}
