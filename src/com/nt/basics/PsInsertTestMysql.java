package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertTestMysql {
	public static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_INFO_DATES(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			//taking inputs from user
			sc = new Scanner(System.in);
			String pname = null,dob=null,doj=null,dom=null;
			if(sc!=null) {
				System.out.println("Enter the name :::");
				pname = sc.next();
				System.out.println("Enter the dob (dd-MM-YYYY)::: ");
				dob = sc.next();
				System.out.println("Enter the doj(yyyy-MM-dd)::: ");
				doj = sc.next();
				System.out.println("Enter the dom(MMM-dd-yyyy)::: ");
				dom = sc.next();
			}
			
			//dob
			//converting the dob to SimpleDateFormate obj
			SimpleDateFormat sdob = new SimpleDateFormat("dd-MM-yyyy");
			//converting sdob obj to java.util.Date obj
			java.util.Date udob = sdob.parse(dob);
			//converting udob to long Date i.e.milliseconds
			long ms = udob.getTime();
			//converting ms to java.sql.Date obj
			java.sql.Date sqdob = new java.sql.Date(ms);
			
			//doj
			//converting the date directly into java.sql.Date obj
			java.sql.Date sqdoj = java.sql.Date.valueOf(doj);
			
			//dom
			//converting dom to SimpleDateFormat
			SimpleDateFormat sdom = new SimpleDateFormat("MMM-dd-yyyy");
			//converting sdom to java.util.date
			java.util.Date udom = sdom.parse(dom);
			//converting udom to long ms
			ms = udom.getTime();
			//passing the ms to java.sql.Date obj
			java.sql.Date sqdom = new java.sql.Date(ms);
			
			//loading driver class
			//Class.forName("com.mysql.cj.jdbc.driver");
			
			//establishing the connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:mysql:///ntaj415","root","root");

			//creating PreparedStatement obj
			if(con!=null)
				ps = con.prepareStatement(INSERT_DATE_QUERY);
			
			//set values to query params
			if(ps!=null) {
				ps.setString(1, pname);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			
			//processing the results
			int count = 0;
			if(ps!=null)
				count = ps.executeUpdate();
			
			if(count==0)
				System.out.println("No results updated");
			else
				System.out.println(count+" results updated");
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
