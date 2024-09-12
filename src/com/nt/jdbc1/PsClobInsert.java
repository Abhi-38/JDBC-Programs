package com.nt.jdbc1;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobInsert {
	private static final String INSERT_CLOB_QUERY = "INSERT INTO JOBSEEKER VALUES(JSID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		//Taking input from user
		try(Scanner sc = new Scanner(System.in);){
			//enter the details of the candidate
			String name = null,add = null,resume = null;
			if(sc!=null) {
				System.out.println("Enter the name of the candidate::: ");
				name = sc.next();
				System.out.println("Enter the address ::: ");
				add = sc.next();
				System.out.println("Enter location of resume ::: ");
				resume = sc.next();
			}
			//create input stream pointing to the resume location
			try(Reader read = new FileReader(resume);){
				//establishing the connection
				try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","tiger");
					//creating preparedstatement obj to execute the query	
					PreparedStatement ps = con.prepareStatement(INSERT_CLOB_QUERY);	){
					//set values to query params
					if(ps!=null) {
						ps.setString(1, name);
						ps.setString(2, add);
						ps.setCharacterStream(3, read);
					}
					//executing the query and processing the results
					int count = 0;
					if(ps!=null) {
						count = ps.executeUpdate();
						if(count==0) {
							System.out.println("Record not inserted");
						}//if
						else {
							System.out.println("Record inserted sucessfully");
						}//else
					}//if
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record insertion failed");
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
