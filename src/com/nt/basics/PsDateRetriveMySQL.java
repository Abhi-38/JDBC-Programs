package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetriveMySQL {
	private static final String RETRIVE_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			//loading jdbc driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			//establishing the connection
			con = DriverManager.getConnection("jdbc:mysql:///ntaj415","root","root");
			//preparing PreapareStatement obj
			if(con!=null)
				ps = con.prepareStatement(RETRIVE_QUERY);
			//execute the query
			if(ps!=null)
				rs = ps.executeQuery();
			//processing the results
			if(rs!=null) {
				while(rs.next()) {
					int pid = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date sqdob = rs.getDate(3);
					java.sql.Date sqdoj = rs.getDate(4);
					java.sql.Date sqdom = rs.getDate(5);
					//creating simpledateformat obj and converting java.sql.date obj to string
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dob = sdf.format(sqdob);
					String doj = sdf.format(sqdoj);
					String dom = sdf.format(sqdom);
					System.out.println(pid+" "+name+" "+dob+" "+doj+" "+dom);
				}//while
			}//if
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
		}
	}
}
