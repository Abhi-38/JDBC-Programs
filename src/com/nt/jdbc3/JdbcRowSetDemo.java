package com.nt.jdbc3;

import java.sql.SQLException;
//import javax.sql.rowset.*;
import oracle.jdbc.rowset.OracleJDBCRowSet;

public class JdbcRowSetDemo {
	public static void main(String[] args) {
		try(OracleJDBCRowSet jrwset = new OracleJDBCRowSet();){
			jrwset.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
			jrwset.setUsername("system");
			jrwset.setPassword("tiger");
			jrwset.setCommand("SELECT * FROM STUDENT");
			jrwset.execute();
			while(jrwset.next()) {
				System.out.println(jrwset.getInt(1)+" "+jrwset.getString(2)+" "+jrwset.getString(3)+" "+jrwset.getString(4));
			}//while
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
