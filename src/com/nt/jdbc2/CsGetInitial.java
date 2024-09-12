package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
/*import java.sql.JDBCType;*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

//import oracle.jdbc.internal.OracleTypes;

//import oracle.jdbc.OracleTypes;


/*SQL> CREATE OR REPLACE PROCEDURE P_GET_EMP_INITIAL
  (
   INITCHAR IN VARCHAR2
  ,DETAILS OUT SYS_REFCURSOR
  ) AS
  BEGIN
   OPEN DETAILS FOR
    SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE ENAME LIKE INITCHAR;
  END;
  /
*/
public class CsGetInitial {
	private static final String GET_INITIAL_EMP = "{CALL P_GET_EMP_INITIAL(?,?)}";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			String initchar = null;
			if(sc!=null) {
				System.out.println("Enter the initial of employee:: ");
				initchar = sc.next()+"%";
			}//if
			//establing the connection
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				//create Callable statement object containing PL/SQL procedure as pre-compiled query
				CallableStatement cs = con.prepareCall(GET_INITIAL_EMP);	){
				//register out paramet with jdbc datatype
				if(cs!=null) 
					cs.registerOutParameter(2, OracleTypes.CURSOR);
				//set value to In Params
				if(cs!=null)
					cs.setString(1, initchar);
				//Execute PL/SQL query
				if(cs!=null)
					cs.execute();
				//gather the results from out param
				if(cs!=null) {
					try(ResultSet rs = (ResultSet)cs.getObject(2);){
						if(rs!=null) {
							boolean flag = false;
							while(rs.next()) {
								System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
							}
							if(flag == false) {
								System.out.println("Record not found");
							}else {
								System.out.println("Record found");
							}
						}//if
					}//try1
				}//if
			}//try2
		}//try3
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
