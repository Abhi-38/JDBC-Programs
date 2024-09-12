package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Scrollable_Ps_test {
	private static final String SCROLLABLE_PS_TEST = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			PreparedStatement ps = con.prepareStatement(SCROLLABLE_PS_TEST,ResultSet.
														TYPE_SCROLL_SENSITIVE,
														ResultSet.CONCUR_UPDATABLE);	
			ResultSet rs = ps.executeQuery();	){
			if(rs!=null) {
				System.out.println("From 1st to last");
				while(rs.next()) {
					System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				System.out.println("---------------------------------------------------------------------");
				System.out.println("REcord from last to first");
				rs.afterLast();
				while(rs.previous()) {
					System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				System.out.println("---------------------------------------------");
				rs.absolute(3);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				boolean flag = rs.isFirst();       //returns true if cursor is on first row
				System.out.println(flag);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.last();
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.absolute(-2);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.first();
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				flag = rs.isFirst();
				System.out.println(flag);
			}
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
