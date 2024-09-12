package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsAgeCalculatorOracle {
	private static final String AGE_CALC = "SELECT ROUND((SYSDATE-DOB)/365.25,2) FROM PERSON_INFO_DATES WHERE PID = ?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//taking input from user
			sc = new Scanner(System.in);
			int pid = 0;
			if(sc!=null) {
				System.out.println("Enter the pid of the person::: ");
				pid = sc.nextInt();
			}
			System.out.println("You entered "+pid);
			//loading the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating the PreparedStatement obj to execute the pre-compiled query
			if(con!=null) 
				ps = con.prepareStatement(AGE_CALC);
			
			//set the params of pre-compiled query
			if(ps!=null)
				ps.setInt(1, pid);
			
			//executing the query
			if(ps!=null) 
				rs = ps.executeQuery();
			
			//getting the results proccessed
			if(rs!=null) {
				if(rs.next()) {
					float age = rs.getFloat(1);
					System.out.println("Age of the person is "+age+" years");
				}else {
					System.out.println("Person not found");
				}
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing the objs
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
