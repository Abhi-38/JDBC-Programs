package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//java app to hike salary of employees based on given percentage
public class HikeSalEmp {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//taking input from user 
			sc = new Scanner(System.in);
			float per = 0.0f;
			int start = 0;
			int end = 0;
			if(sc!=null) {
				System.out.println("Enter the percentage by which you want to hike the salary ::: ");
				per = sc.nextFloat();
				System.out.println("Enter the starting value ::: ");
				start = sc.nextInt();
				System.out.println("Enter the ending value ::: ");
				end = sc.nextInt();
			}
			
			//loading the jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Creating the connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","tiger");
			
			//creating the statement
			if(con!=null)
				st = con.createStatement();

			//preparing sql query to hike the salary of the employees by given percentageS
			//UPDATE EMP SET SAL=((&N/100)*SAL)+SAL WHERE SAL BETWEEN &SAL AND &SAL;
			String query = "UPDATE EMP SET SAL=(("+per+"/100)*SAL)+SAL WHERE SAL BETWEEN "+start+" AND "+end;
			
			//execute the sql query
			int count = 0;   //to collect executeUpdate method's int value
			if(st!=null)
				count = st.executeUpdate(query);
			
			if(count == 0)
				System.out.println("Records not found");
			else
				System.out.println(count+" rows were edited");

		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Entered wrong cloumn name or table name");
			}
			else if (se.getErrorCode()==12899) {
				System.out.println("Entered data more than column data size");
			}
			else
				se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
	}
}
