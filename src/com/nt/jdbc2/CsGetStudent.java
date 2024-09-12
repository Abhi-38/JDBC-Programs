package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsGetStudent {
	private static final String CALLABLE_STUDENT = "{CALL P_GET_STUDENT(?,?,?,?)}";
	public static void main(String[] args) {
		//taking input from user 
		try(Scanner sc = new Scanner(System.in);){
			int sno = 0;
			if(sc!=null) {
				System.out.println("Enter the student id number: ");
				sno = sc.nextInt();
			}//if
			//establish the connection
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//creating CallableStatement obj having PL/SQL procedure as pre-compiled query	
				CallableStatement cs = con.prepareCall(CALLABLE_STUDENT);	){
				//register OUT parameter with jdbc datatypes
				if(cs!=null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}//if
				//set IN parameter values
				if(cs!=null) {
					cs.setInt(1, sno);
					//execute the query
					cs.execute();
				}//if
				//gathering the results
				if(cs!=null) {
					String name = cs.getString(2);
					String add = cs.getString(3);
					float avg = cs.getFloat(4);
					System.out.println("Name: "+name+"\t"+"Address: "+add+"\t"+"Average: "+avg);
				}//if
			}//try1
		}//try2
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
