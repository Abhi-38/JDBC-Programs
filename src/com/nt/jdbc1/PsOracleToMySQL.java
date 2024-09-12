package com.nt.jdbc1;
//JAVA app to copy oracle table data into mysql table 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class PsOracleToMySQL {
	private static final String MYSQL_INSERT = "INSERT INTO STUDENT(SNAME,LOC,AVERAGE) VALUES(?,?,?)";
	private static final String ORACLE_SELECT = "SELECT SNAME,SADD,AVG FROM STUDENT";
	public static void main(String[] args) {
		Connection oracon = null;
		Connection mycon = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//loading jdbc driver classes
			Class.forName("com.mysql.cj.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing the connection
			mycon = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
			oracon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating Statement obj
			if(oracon!=null)
				st = oracon.createStatement();
			//creating preparedstatement obj for pre-compiled query
			if(mycon!=null)
				ps = mycon.prepareStatement(MYSQL_INSERT);
			//executing the select query
			if(oracon!=null && st!= null)
				rs = st.executeQuery(ORACLE_SELECT);
			//processing the results of oracle table 
			//and inserting the values to mysql table
			if(rs!=null && ps!=null) {
				while(rs.next()) {
					String sname = rs.getString(1);
					String sadd = rs.getString(2);
					float avg = rs.getFloat(3);
					//set pre-compiled query params
					ps.setString(1, sname);
					ps.setString(2, sadd);
					ps.setFloat(3, avg);
					//executing the pre-compiled query
					ps.executeUpdate();
				}//while
				System.out.println("records are copied from oracle to mysql");
			}//if
		}//try
		catch(SQLException se) {
			System.out.println("Records are not copied from table");
			se.printStackTrace();
		}
		catch(Exception e) {
			System.out.println("problems in app execution");
			e.printStackTrace();
		}
		finally {
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
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(mycon!=null)
					mycon.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(oracon!=null)
					oracon.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
