package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTab {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//takin input from user
			sc = new Scanner(System.in);
			String city1 = null;
			String city2 = null;
			String city3 = null;
			
			if(sc!=null) {
				System.out.println("enter the name of first city ::: ");
				city1 = sc.next();
				System.out.println("enter the name of second city ::: ");
				city2 = sc.next();
				System.out.println("enter the name of third city ::: ");
				city3 = sc.next();
				System.out.println("=================================");
			}
			city1 = "'"+city1+"'";
			city2 = "'"+city2+"'";
			city3 = "'"+city3+"'";
			
			//lets load the jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//creating the connection 
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating statement to execute rthe query
			if(con!=null)
				st =con.createStatement();
			//creating sql query
			//Delete from student where sadd in('city1','city2','city3')
			String query = "DELETE FROM STUDENT WHERE SADD IN("+city1+","+city2+","+city3+")";
			System.out.println(query);
			
			//now execute the query
			int count = 0;
			if(st!=null)
				count = st.executeUpdate(query);
			
			if(count == 0)
				System.out.println("no record updated");
			else
				System.out.println(count+" rows are affected");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("entered wrong col name,table name");
			}else if(se.getErrorCode()==12899){
				System.out.println("enter the data according to the size of column");
			}
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally 
		{
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
	}//main
}//class
