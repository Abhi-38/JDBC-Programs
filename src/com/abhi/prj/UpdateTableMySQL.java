package com.abhi.prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class UpdateTableMySQL {
	public static String query = "INSERT INTO TEST VALUES(?,?,?)";
	public static void main(String[] args) {
		int id = 0;
		String name = null;
		String loc = null;
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//insert the data
			sc = new Scanner(System.in);
			System.out.println("Enter id number: ");
			id = sc.nextInt();
			System.out.println("Enter name: ");
			name = sc.next();
			System.out.println("Enter loc: ");
			loc = sc.next();

			//loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//Connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:mysql:///abhi","root","root");
			//now creating the jdbc statement
			if(con!=null)
				ps = con.prepareStatement(query);
			
			//now inserting the data
			if(ps!=null) {
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setString(3, loc);
			}
			int result = 0;
			if(ps!=null)
				result = ps.executeUpdate();
			//checking
			if(result==0) {
				System.out.println("Record not inserted");
			}//if
			else {
				System.out.println("Record inserted");
			}//else
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(ps!=null) {
					ps.close();
				}
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
				if(sc!=null) {
					sc.close();
				}
			}catch(Exception se) {
				se.printStackTrace();
			}
		}
	}
}
