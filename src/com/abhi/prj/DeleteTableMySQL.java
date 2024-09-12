package com.abhi.prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class DeleteTableMySQL {
	public static String query = "DELETE FROM TEST WHERE ID = ?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			System.out.println("Enter id number:: ");
			int id = sc.nextInt();
			//load class driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection 
			con = DriverManager.getConnection("jdbc:mysql:///abhi","root","root");
			//create jdbc statement obj
			if(con!=null)
				ps = con.prepareStatement(query);
			//execute the query and check the results
			if(ps!=null) {
				ps.setInt(1, id);
			}
			int result = 0;
			if(ps!=null) {
				result = ps.executeUpdate();
			}
			//check records
			if(result == 0)
				System.err.println("records are not deleted");
			else
				System.out.println("records are  deleted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
				if(sc!=null) {
					sc.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
