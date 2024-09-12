package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsCustomersQuery {
	public static String INPUT_CUSTOMERS = "INSERT INTO CUSTOMERS(CID,CNAME,CADD,MBNO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//take input from user
			sc = new Scanner(System.in);
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter the number of customers you want to enter::: ");
				count = sc.nextInt();
			}
			//loading jdbc driver class
			//Class.forName("com.mysql.cj.jdbc.driver");
			
			//establishing the connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
			
			//create PreparedStatement obj
			//pass the query to prepareStatement()
			if(con!=null)
				ps = con.prepareStatement(INPUT_CUSTOMERS);
			//taking input values from enduser
			//executing precompiled query multiple times
			if(sc!=null && ps!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter customer id::: ");
					int cid = sc.nextInt();
					System.out.println("Enter customer name::: ");
					String cname = sc.next();
					System.out.println("Enter customer address::: ");
					String add = sc.next();
					System.out.println("Enter mobile number::: ");
					float num = sc.nextFloat();
					//set each customer details as precompiled query params
					ps.setInt(1, cid);ps.setString(2, cname);ps.setString(3, add);ps.setFloat(4, num);
					//process execution result	
					
					int result =0;
					result = ps.executeUpdate();
					if(result == 0)
						System.out.println(i+" customers are not inserted");
					else
						System.out.println(i+" customers are inserted");
				}//for
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing jdbc objects
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
		}//finally
	}//main
}//class
