package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsAgeCalculateMySQL {
	private static final String AGE_CALC= "SELECT TIMESTAMPDIFF(DAY,DOB,CURDATE())/365.25 FROM PERSON_INFO_DATES WHERE PID = ?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//taking inputs from user
			sc = new Scanner(System.in);
			int pid = 0;
			if(sc!=null) {
				System.out.println("Enter the pid of person::: ");
				pid = sc.nextInt();
			}
			System.out.println("You entered "+pid);
			//loading the jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.driver");
			//establising the connection 
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:mysql:///ntaj415","root","root");
			//creating preparredstatement obj for pre-compiled query
			if(con!=null)
				ps = con.prepareStatement(AGE_CALC);
			//set pre-compiled query params
			if(ps!=null)
				ps.setInt(1, pid);
			//executing the pre-compiled query
			if(ps!=null)
				rs = ps.executeQuery();
			//processing the results
			if(rs!=null) {
				if(rs.next()) {
					float age = rs.getFloat(1);
					System.out.println("Age of person is "+age+" years");
				}//if
				else {
					System.out.println("Person not found");
				}//else
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing the jdbc obj
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
				if(rs!=null)
					rs.close();
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
