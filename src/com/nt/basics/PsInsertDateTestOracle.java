package com.nt.basics;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsInsertDateTestOracle {
	private static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_INFO_DATES VALUES(PID_SEQ.NEXTVAL,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null; 
		PreparedStatement ps = null;
		try {
			//taking inputs from user
			sc = new Scanner(System.in);
			String pname=null,dob=null,doj=null,dom=null;
			if(sc!=null) {
				System.out.println("Enter the name of person::: ");
				pname = sc.next();
				System.out.println("Enter date of birth(dd-mm-yyyy)::: ");
				dob = sc.next();
				System.out.println("Enter date of join(yyyy-mm-dd)::: ");
				doj = sc.next();
				System.out.println("Enter date of marraige(mmm-dd-yyyy)::: ");
				dom = sc.next();
			}
			//dob
			//converting the dob string value to SimpleDateFormat obj 
			SimpleDateFormat sdob = new SimpleDateFormat("dd-MM-yyyy");
			//converting sdf obj to java.util.Date class obj
			java.util.Date udob = sdob.parse(dob);
			//converting udob obj to millisecs using getTime(-)
			long ms = udob.getTime();
			//now convertion of ms to java.sql.Date obj
			java.sql.Date sqdob = new java.sql.Date(ms);
			
			//doj
			//converting the doj directly in java.sql.Date format
			java.sql.Date sqdoj = java.sql.Date.valueOf(doj);
			
			//dom
			//converting dom to SimpleDateFormat obj
			SimpleDateFormat sdom = new SimpleDateFormat("MMM-dd-yyyy");
			//converting sdom obj to java.util.Date class obj
			java.util.Date udom = sdom.parse(dom);
			//converting udom obj to long Date (millisecs format)
			ms = udom.getTime();
			//converting millisecs to java.sql.Date obj
			java.sql.Date sqdom = new java.sql.Date(ms);
			
			//loading driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating preparedstatement obj
			if(con!=null) 
				ps = con.prepareStatement(INSERT_DATE_QUERY);
			
			//set values to query params
			if(ps != null) {
				ps.setString(1, pname);
				ps.setDate(2, sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			
			//processing the results
			int count = 0;
			if(ps!=null)
				count = ps.executeUpdate();
			
			//processing the results
			if(count == 0)
				System.out.println("records are not inserted");
			else
				System.out.println(count+" records are inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
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
		}//finally
	}//main
}//class
