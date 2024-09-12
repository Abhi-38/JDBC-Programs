package com.nt.jdbc;
//java app to insert records into the table 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTab {
	public static void main(String[] args) {
		
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		
		try {
			//taking input from user
			sc = new Scanner(System.in);
			int sno = 0;
			String sname = null;
			String sadd = null;
			float avg = 0.0f;
			
			System.out.println("Welecome to student table");
			if(sc!=null) {
				System.out.println("Enter student id number ::: ");
				sno = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter name of student ::: ");
				sname = sc.nextLine().toUpperCase();
				System.out.println("Enter the address ::: ");
				sadd = sc.next().toUpperCase();
				System.out.println("Enter the percentage ::: ");
				avg = sc.nextFloat();
			}
			
			sname = "'"+sname+"'";
			sadd = "'"+sadd+"'";
			
			//lets load the jdbc driver class
			//Class.forName("oracle.jdb.driver.OracleDriver");
			
			//lets create connection 
			if(sc!=null)
				con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creation of statement
			if(con!=null)
				st = con.createStatement();
			
			//prepare sql query
			//INSERT INTO STUDENT VALUES(105,'ABC','ZIGZAG',99.99);
			String query = "INSERT INTO STUDENT VALUEx("+sno+","+sname+","+sadd+","+avg+")";
			System.out.println(query);//checking query
			
			//executing the query
			int count = 0;
			if(st!=null)
				count = st.executeUpdate(query);  //returns int value
			
			if(count == 0)
				System.out.println("No records updated");
			else
				System.out.println(count+" rows affected");
		}
		catch(SQLException se) {
			if(se.getErrorCode()==1) {
				System.out.println("Duplicates not allowed for primary key values");
			}else if(se.getErrorCode()==1400) {
				System.out.println("null not allowed in primary key values");
			}else if(se.getErrorCode()>=900 && se.getErrorCode()<=999) {
				System.out.println("Entered wrong col name or table name or SQL keyword");
			}else if(se.getErrorCode()==12899) {
				System.out.println("Dont insert more than col size data");
			}
			System.out.println("Problem in record insertion");
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
