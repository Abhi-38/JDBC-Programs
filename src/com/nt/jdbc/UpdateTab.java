package com.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
//update the student table record by entering sno,sname,add
import java.util.Scanner;

public class UpdateTab {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			int sno = 0;
			String name = null;
			String add = null;
			float avg = 0.0f;
			if(sc!=null) {
				System.out.println("Enter the details to update the table");
				System.out.println();
				System.out.println("Enter the Student id number ::: ");
				sno = sc.nextInt();
				System.out.println("Enter the name ::: ");
				sc.nextLine();
				name = sc.nextLine().toUpperCase();
				System.out.println("Enter the city name ::: ");
				add = sc.nextLine().toUpperCase();
				System.out.println("Enter average value ::: ");
				avg = sc.nextFloat();
			}
			
			name = "'"+name+"'";
			add = "'"+add+"'";
			
			//loading the driver class
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//now we are establishing the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			
			//creating the statement 
			if(con!=null)
				st = con.createStatement();
			
			//now lets prepare sql query
			//update student set sname='Abhishek',sadd='kokan',avg=91.80 where sno = 104;
			String query = "UPDATE STUDENT SET SNAME="+name+",SADD="+add+",AVG="+avg+" WHERE SNO ="+sno;
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
				System.out.println("Entered wrong cloumn name or table name");
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
	}//main
}//class
