package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTestTWR {
	private static final String PRODUCT_SQL = "SELECT \"PID\", \"PNAME\", \"PRICE\", \"QTY\", \"STATUS\"\r\n"
			+ "	FROM public.\"PRODUCT\";"; 
	public static void main(String[] args) {
		//Establishing the connection and creating PreparedStatement obj for executing pre-compiled query
		try (Connection con = DriverManager.getConnection("jdbc:postgresql:NTAJ","postgres","tiger");
				PreparedStatement ps = con.prepareStatement(PRODUCT_SQL);  	){ /*creating the prepareedstatement obj*/
			//executing the query
			if(con!=null && ps!=null) {
				try(ResultSet rs = ps.executeQuery();){
					//if
					System.out.println("The product details are......");
					//processing the results
					if(rs!=null) {
						while(rs.next()!=false) {
							System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getFloat(3)+" "+rs.getFloat(4)+" "+rs.getString(5));
						}//while
					}//if
				}//try2
			}
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
