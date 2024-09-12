package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
SQL> CREATE OR REPLACE FUNCTION FX_GET_STUDENT
    (
     NO IN NUMBER
    ,NAME OUT VARCHAR2
    ,ADDRS OUT VARCHAR2
    )RETURN FLOAT AS
     PERCENTAGE FLOAT
    ;
    BEGIN
    SELECT SNAME,SADD,AVG INTO NAME,ADDRS,PERCENTAGE FROM STUDENT WHERE SNO =NO;
    RETURN PERCENTAGE;
   END;
   /
*/
public class CsGetStudentBySno {
	private static final String FUNCTION_QUERY = "{?=call FX_GET_STUDENT(?,?,?)}";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			int no = 0;
			if(sc!=null) {
				System.out.println("Enter the sno of student: ");
				no = sc.nextInt();
			}//if
			//establish the connection
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//create CallableStatement obj having query containing pl/sql function as pre-compiled query
				CallableStatement cs = con.prepareCall(FUNCTION_QUERY);	){
				//register OUT parameter with jdbc datatype
				if(cs!=null) {
					cs.registerOutParameter(1, Types.FLOAT);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.VARCHAR);
				}//if
				//set values to IN params
				if(cs!=null) {
					cs.setInt(2, no);
				}//if
				//execute the pl/sql function
				if(cs!=null) {
					cs.execute();
				}//if
				//gathering the results from OUT parameter
				if(cs!=null) {
					System.out.println("Name of student: "+cs.getString(3));
					System.out.println("Address of student: "+cs.getString(4));
					System.out.println("Percentage of Student: "+cs.getFloat(1));
				}//if
			}//try1
		}//try2
		catch(SQLException se) {
			se.printStackTrace();
		}//catch for checked exception
		catch(Exception e) {
			e.printStackTrace();
		}//catch for un-checked exception
	}//main
}//class
