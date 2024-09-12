package com.nt.jdbc3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

//jdbc app for scrollable resultset
public class Scrollable_Test_With_Prop {
	private static final String GET_SCROLL = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP";
	public static void main(String[] args) {
		Properties props = null;
		try(InputStream is  = new FileInputStream("src/com/nt/properties/jdbc.properties");){
			props = new Properties();
			props.load(is);
		}catch(FileNotFoundException fne) {
			fne.printStackTrace();	
		}catch(Exception e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection(props.getProperty("jdbc.url"),
														 props.getProperty("jdbc.username"),
														 props.getProperty("jdbc.password"));
			Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = st.executeQuery(GET_SCROLL);	){
			if(rs!=null) {
				System.out.println("Record from top to bottom");
				while(rs.next()) {
					System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}//while
				System.out.println("--------------------------------------------");
				System.out.println("REcord from last to first");
				rs.afterLast();
				while(rs.previous()) {
					System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
				System.out.println("---------------------------------------------");
				rs.absolute(3);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				boolean flag = rs.isFirst();       //returns true if cursor is on first row
				System.out.println(flag);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.last();
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.absolute(-2);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.first();
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.relative(3);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.relative(5);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.relative(-3);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
				rs.absolute(-6);
				System.out.println(rs.getRow()+"======>"+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				System.out.println("---------------------------------------------------------------------");
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
