package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlob {
	private static final String INSERT_BLOB_QUERY = "INSERT INTO ARTIST_INFO VALUES(AID_SEQ.NEXTVAL,?,?,?)";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in)){
			System.out.println("Enter the details of the artist");
			String name = null,add = null,photolocation = null;
			if(sc!=null) {
				System.out.println("Enter name of the artist::: ");
				name = sc.nextLine();
				System.out.println("Enter the address::: ");
				add = sc.next();
				System.out.println("Enter the location of photo:::");
				photolocation = sc.next();
			}//if
			//create inputfilestream to insert the photo
			try(InputStream is = new FileInputStream(photolocation)){
				//establising the connection
				try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
					PreparedStatement ps = con.prepareStatement(INSERT_BLOB_QUERY);	){
					//setting precompiled query params
					if(con!=null && ps!=null) {
						ps.setString(1, name);
						ps.setString(2, add);
						ps.setBinaryStream(3, is);
					}
					//executing the pre-compiled query
					int count = 0;
					if(ps!=null) 
						count = ps.executeUpdate();
					
					//checking the data insertion
					if(count == 0)
						System.out.println("Data is not inserted");
					else
						System.out.println("Data inserted Sucessfully");
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Problem in record insertion");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
}//class
