package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//using PreparedStatement to execute the select query
public class PsSelectSQL {
	private static final String EMP_SQL = "SELECT EMPNO,ENAME,SAL,DEPTNO FROM EMP"; 
	public static void main(String[] args) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//loading the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating the prepareedstatement obj
			if(con!=null)
				ps = con.prepareStatement(EMP_SQL);
			//now creating the resultset obj
			if(con!=null && ps!=null) {
				rs = ps.executeQuery();
			}//if
			System.out.println("The employee details are......");
			//processing the results
			if(rs!=null) {
				while(rs.next()!=false) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getInt(4));
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
			//closing the jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
