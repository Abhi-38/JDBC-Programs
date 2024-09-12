package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddPer {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			
			String city1 = null;
			String city2 = null;
			String city3 = null;
			float per = 0.0f;
			
			if(sc!=null) {
				System.out.println("Enter the details to update the table");
				System.out.println();
				System.out.println("Enter the city1 name ::: ");
				city1 = sc.next().toUpperCase();
				System.out.println("Enter the city2 name :::");
				city2 = sc.next().toUpperCase();
				System.out.println("Enter the city3 name ::: ");
				city3 = sc.next().toUpperCase();
				System.out.println("Enter percentage to add ::: ");
				per = sc.nextFloat();
			}
			
			city1 = "'"+city1+"'";
			city2 = "'"+city2+"'";
			city3 = "'"+city3+"'";
			
			//loading the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//now we are establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating the statement 
			if(con!=null)
				st = con.createStatement();
			
			//now lets prepare sql query
			//UPDATE STUDENT SET AVG = AVG + (AVG * (&AVG/100)) WHERE SADD IN ('city1','city2','city3');
			String query = "UPDATE STUDENT SET AVG = AVG + (AVG * ("+per+"/100)) WHERE SADD IN("+city1+","+city2+","+city3+")";
			System.out.println(query);//printing the query to check
			
			//now executing the query
			int count = 0; //updateExecute() returns int value
			if(st!=null)
				count = st.executeUpdate(query);
			
			//processing the results
			if(count == 0)
				System.out.println("No records were edited.");
			else
				System.out.println(count+" rows were edited");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Entered wrong column name or table name");
			}
			else if (se.getErrorCode()==12899) {
				System.out.println("Entered data more than column data size");
			}
			else
				se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing jdbc objs
			try {
				if(st != null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con != null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc != null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//MAIN
}//class
