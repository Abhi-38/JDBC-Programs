package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SeqStudentIn {
	private static final String STUDENT_INSERT_QUERY = "INSERT INTO STUDENT VALUES(SQ1.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			int count = 0;
			if(sc!=null) {
				System.out.println("Enter number of student you want to enter::: ");
				count = sc.nextInt();
			}
			//now loading the jdbc driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establishing the connection
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
	
			//create PreparedStatement obj
			//pass sql query to prepareStatement()
			if(con!=null)
				ps = con.prepareStatement(STUDENT_INSERT_QUERY);
			//read input values from enduser set them to query param values and 
			//execute sql query for multiple times
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("Enter name of student::: ");
					String name = sc.next();
					System.out.println("Enter student address::: ");
					String add = sc.next();
					System.out.println("Enter student's average::: ");
					float avg = sc.nextFloat();
					//set each student details as pre-compiled query params
					ps.setString(1, name);ps.setString(2, add);ps.setFloat(3, avg);
					//execute precompiled sql query each time
					int result = ps.executeUpdate();
					//process execution results each time
					if(result==0)
						System.out.println(i+" student details not inserted");
					else
						System.out.println(i+" student details are inserted");
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
