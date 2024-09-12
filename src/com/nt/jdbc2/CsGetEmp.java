package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE PROCEDURE P_GET_EMP_DETAILS
  (
   ENO IN NUMBER
  ,NAME OUT VARCHAR2
  ,DESG OUT VARCHAR2
  ,SALARY OUT NUMBER
  ) AS
  BEGIN
   SELECT ENAME,JOB,SAL INTO NAME,DESG,SALARY FROM EMP;
  END;
  /
*/
public class CsGetEmp {
	private static final String CALLABLE_EMP = "{CALL P_GET_EMP_DETAILS(?,?,?,?)}";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			int eno = 0;
			if(sc!=null) {
				System.out.println("Enter the employee number: ");
				eno = sc.nextInt();
			}//if
			//establish t6he connection
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//creating CallableStatement obj having query as pl/sql procedure as pre-compiuled query
				CallableStatement cs = con.prepareCall(CALLABLE_EMP);	){
				//register out param values with jdbc datatypes
				if(cs!=null) {
					cs.registerOutParameter(2, Types.VARCHAR);
					cs.registerOutParameter(3, Types.VARCHAR);
					cs.registerOutParameter(4, Types.FLOAT);
				}//if
				//set IN param values
				if(cs!=null) {
					cs.setInt(1, eno);
					//execute the query
					cs.execute();
				}//if
				
				//gather results from out params
				if(cs!=null) {
					String name = cs.getString(2);
					String job = cs.getString(3);
					float sal = cs.getFloat(4);
					System.out.println("Name: "+name+" "+"Job: "+job+" "+"Salary: "+sal);
				}//if
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("RECORD INSERTION FAILED");
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
