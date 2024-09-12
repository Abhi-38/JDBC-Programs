package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class PsDateRetrieve {
	public static final String RETRIEVE_DATE_QUERY = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//loading jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//create PreparedStatement obj
			if(con!=null) {
				ps = con.prepareStatement(RETRIEVE_DATE_QUERY);
			}
			
			//executing the query
			if(ps!=null) {
				rs = ps.executeQuery();
			}
			
			/*//processing the resultset
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
				}//while
			}//if*/
			
			//Creating SimpleDateFormat obj
			if(rs!=null) {
				while(rs.next()) {
					int pid = rs.getInt(1);
					String name = rs.getString(2);
					java.sql.Date sqdob = rs.getDate(3);
					java.sql.Date sqdoj = rs.getDate(4);
					java.sql.Date sqdom = rs.getDate(5);
					//converting java.sql.Date class obj to String
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dob = sdf.format(sqdob);
					String doj = sdf.format(sqdoj);
					String dom = sdf.format(sqdom);
					System.out.println(pid+" "+name+" "+dob+" "+doj+" "+dom);
				}//if
			}//while
			
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
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
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
		}//finally
	}//main
}//class
