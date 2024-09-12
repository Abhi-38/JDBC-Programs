package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsAuthentication {
	private static final String CALLABLE_AUTH = "{CALL P_AUTHENTICATION(?,?,?)}";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			String username = null,password = null;
			if(sc!=null) {
				System.out.println("Enter username::: ");
				username = sc.next();
				System.out.println("Enter password::: ");
				password = sc.next();
			}//if
			//establish the connection
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//create CallableStatement obj having query calling PL/SQL procedure as pre-compiled query
				CallableStatement cs = con.prepareCall(CALLABLE_AUTH);	){
				//register OUT params with jdbc datatypes
				if(cs!=null)
					cs.registerOutParameter(3, Types.VARCHAR);
				//set IN param values
				if(cs!=null) {
					cs.setString(1,username);
					cs.setString(2, password);
				}//if
				//execute PL/SQL procedure
				if(cs!=null)
					cs.execute();
				//gathering the results from OUT param
				String result = null;
				if(cs!=null) {
					result = cs.getString(3);
					System.out.println(result);
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
