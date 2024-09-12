package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SensitiveRs {
	private static final String STUDENT_SCROLL = "SELECT SID,SNAME,LOC,AVERAGE FROM STUDENT";
	public static void main(String[] args) {
	try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = st.executeQuery(STUDENT_SCROLL);	){
		if(rs!=null) {
			System.out.println("Rs records from top to bottom");
			while(rs.next()) {
			int count = 0;
			if(count == 0) 
				Thread.sleep(5000);  //sleeping the thread so that we can make chnages in table in the given time
			rs.refreshRow();
			count++;
			
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
			}//while
			
		}//if
	}
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}
}
