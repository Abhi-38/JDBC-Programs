package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsClobBlobInsert {
	private static final String INSERT_DETAILS = "INSERT INTO JOBSEEKER(JSNAME,JSADD,RESUME,PHOTO) VALUES(?,?,?,?)";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			//Entering the details of the candidate
			String name = null,add = null,resume = null,photo = null;
			if(sc!=null) {
				System.out.println("Enter the name of candidate::: ");
				name = sc.next();
				System.out.println("Enter the address details::: ");
				add = sc.next();
				System.out.println("Enter the location of the resume::: ");
				resume = sc.next();
				System.out.println("Enter the location of the photo::: ");
				photo = sc.next();
			}
			//creating filereading pointing to the resume
			try(Reader read = new FileReader(resume);
				//creating file input stream obj pointing to photo location	
				FileInputStream fs = new FileInputStream(photo);){
				//establishing the connection with db
				try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj415","root","root");
					//creating prepared ststement obj to execute the pre-compiled query
					PreparedStatement ps = con.prepareStatement(INSERT_DETAILS);){
					//setting the query params
					ps.setString(1, name);
					ps.setString(2, add);
					ps.setCharacterStream(3, read);
					ps.setBinaryStream(4, fs);
					//executing the method
					if(ps!=null) {
						int count = 0;
						count = ps.executeUpdate();
						//processing the results
						if(count == 0)
							System.out.println("Record is not inserted");
						else
							System.out.println("Record is inserted");
					}//if
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record insertion failed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
