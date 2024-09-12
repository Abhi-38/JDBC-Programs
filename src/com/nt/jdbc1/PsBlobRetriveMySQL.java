package com.nt.jdbc1;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBlobRetriveMySQL {
	private static final String RETRIEVE_ARTIST_MYSQL = "SELECT AID,NAME,ADDRESS,PHOTO FROM ARTIST_INFO WHERE AID=?";
	public static void main(String[] args) {
		//Taking input from user
		try(Scanner sc = new Scanner(System.in);){
			int aid = 0;
			if(sc!=null) {
				//taking user input
				System.out.println("Enter the aid::: ");
				aid = sc.nextInt();
			}
			//establishing the connection and creating PreparedStatement obj
			try(Connection con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
				PreparedStatement ps = con.prepareStatement(RETRIEVE_ARTIST_MYSQL);	){
				//setting the pre-compiled query params
				if(con!=null && ps!=null) 
					ps.setInt(1, aid);
				
				//executing the query
				if(ps!=null) {
					try(ResultSet rs = ps.executeQuery()) {
						//processing the results
						if(rs!=null) {
							if(rs.next()) {
								aid = rs.getInt(1);
								String name = rs.getString(2);
								String add = rs.getString(3);
								System.out.println(aid+" "+name+" "+add);
								//processing the photo in inputstream
								try(InputStream is = rs.getBinaryStream(4);
										//outputStream pointing to destination file
									OutputStream os = new FileOutputStream("retrived_images");){
									//copy blob col value to destination file
									IOUtils.copy(is,os);
									System.out.println("Blob value is retrived and stored in file");
								}//try4
							}//if
							else {
								System.out.println("Record not found");
							}//else
						}//if
					}//try3
				}//if
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
