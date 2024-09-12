package com.abhi.prj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTableMySQL {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			//load class driver
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//connection 
			con = DriverManager.getConnection("jdbc:mysql:///abhi","root","root");
			//create jdbc statement obj
			if(con!=null)
				st = con.createStatement();
			//query to retrieve data
			String query = "SELECT * FROM TEST";
			System.out.println(query);//for checking
			//now execute query
			if(st!=null)
				rs = st.executeQuery(query);
			//printing data
			int id = 0;
			String name = null;
			String loc = null;
			while(rs.next()) {
				id = rs.getInt(1);
				name = rs.getString(2);
				loc = rs.getString(3);
				System.out.println(id+" "+name+" "+loc);
			}
				
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) {
					rs.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null) {
					st.close();
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
		}
	}
}
