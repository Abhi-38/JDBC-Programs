package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsBetweenDate {
	private static final String BETWEEN_QUERY = "SELECT PID,PNAME,DOB FROM PERSON_INFO_DATES WHERE DOB>=? AND DOB<=?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			String sdob = null;
			String edob = null;
			if(sc!=null) {
				System.out.println("Enter the starting date(dd-MM-yyyy)::: ");
				sdob = sc.next();
				System.out.println("Enter the ending date(dd-MM-yyyy)::: ");
				edob = sc.next();
			}
			
			//converting String to java.sql.Date class obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date ssqdob = new java.sql.Date(sdf.parse(sdob).getTime());
			java.sql.Date esqdob = new java.sql.Date(sdf.parse(edob).getTime());
			
			//loading driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			if(sc!=null) 
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//Creating PreparedStatement obj
			if(con!=null)
				ps = con.prepareStatement(BETWEEN_QUERY);
			
			//set the param into the query
			if(ps!=null) {
				ps.setDate(1, ssqdob);
				ps.setDate(2, esqdob);
			}
			
			//executing the query
			if(ps!=null)
				rs = ps.executeQuery();
			
			//processing the results
			if(rs!=null) {
				System.out.println("Here are your Results.....");
				System.out.println();
				while(rs.next()) {
					int pid = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date sqdob = rs.getDate(3);
					//converting java.sql.date obj to String
					String dob = sdf.format(sqdob);
					System.out.println(pid+" "+name+" "+dob);
				}//while
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			//closing jdbc objs
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
				if(con!=null) {
					con.close();
				}
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
