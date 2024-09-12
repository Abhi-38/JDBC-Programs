package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsFirstProSum {
	private static final String CALLABLE_QUERY = "{CALL FIRST_PRO(?,?,?)}";
	public static void main(String[] args) {
		//taking input from user
		int first = 0,second = 0;
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Enter first number::: ");
			first = sc.nextInt();
			System.out.println("Enter second number::: ");
			second = sc.nextInt();
			
			//establishing the connection with database
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//creating callablestatemnt obj having query calling pl/sql procedureas pre-compiled query 	
				CallableStatement cs = con.prepareCall(CALLABLE_QUERY);	){
				//register OUT params with jdbc datatype
				if(con!=null && cs != null) 
					cs.registerOutParameter(3, Types.INTEGER);
				//set values of IN params
				if(cs!=null) {
					cs.setInt(1, first);
					cs.setInt(2, second);
				}//if
				//execute/call the pl/sql function
				if(cs!=null)
					cs.execute();
				//gather results from out params
				int result = 0;
				if(cs!=null)
					result = cs.getInt(3);
				System.out.println("The addition result is: "+result);
			}//try2
			
		}//try1
		catch(SQLException se ) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
