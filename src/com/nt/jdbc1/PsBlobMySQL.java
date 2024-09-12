package com.nt.jdbc1;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsBlobMySQL {
	private static final String INSERT_ARTIST_MYSQL = "INSERT INTO ARTIST_INFO(NAME,ADDRESS,PHOTO) VALUES(?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			//taking input from user
			String name = null,add = null,photoloc = null;
			if(sc!=null) {
				System.out.println("Enter details of artist");
				System.out.println("Enter the name ::: ");
				name = sc.nextLine();
				System.out.println("Enter the address ::: ");
				add = sc.nextLine();
				System.out.println("Enter the photo location ::: ");
				photoloc = sc.next();
			}
			//taking FileInputStream to pass the photo as blob obj
			try(InputStream is = new FileInputStream(photoloc);){
				//establishing the connection
				try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
					PreparedStatement ps = con.prepareStatement(INSERT_ARTIST_MYSQL);	){
					//inserting the params to the pre-compiled sql query
					if(con!=null && ps!=null) {
						ps.setString(1, name);
						ps.setString(2,add);
						ps.setBinaryStream(3, is);
					}
					//executing the query
					int count = 0;
					if(ps!=null)
						count = ps.executeUpdate();
					//processing the results
					if(count == 0)
						System.out.println("Record is not inserted");
					else
						System.out.println("Record inserted sucessfully");
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
	}
}
