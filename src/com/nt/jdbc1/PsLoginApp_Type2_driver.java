package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp_Type2_driver {
	public static final String LOGIN_QUERY = "SELECT COUNT(*) FROM IRCTC WHERE UNAME=? AND PWD=?";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//taking input from user 
			sc = new Scanner(System.in);
			String user=null,pwd=null;
			
			if(sc!=null) {
				System.out.println("Enter user details::: ");
				user = sc.nextLine();
				//sc.nextLine();
				System.out.println("Enter the password::: ");
				pwd = sc.nextLine();
			}
			
			//loading jdbc driver class obj
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection 
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:oci8:@orcl","system","tiger");
			
			//creating jdbc PreparedStatement obj
			//passing the query 
			if(sc!=null && con!=null)
				ps = con.prepareStatement(LOGIN_QUERY);
			//set values to parameters of precompiled query
			if(ps!=null) {
				ps.setString(1, user);
				ps.setString(2, pwd);
			}//if
			//send and execute sql query to db s/w
			if(ps!=null)
				rs = ps.executeQuery();
			//processing the resultset 
			int count = 0;
			if(rs!=null) {
				rs.next();
				count = rs.getInt(1);
			}//if
			
			//processing the results
			if(count==0) {
				System.out.println("INVALID CREDENTIALS");
			}else {
				System.out.println("VALID CREDENTIALS");
			}//else
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
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
		}
	}
}
