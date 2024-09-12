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

//java app to retrieve blob data from artist_info
public class PsBlobRetrieve {
	private static final String RETRIEVE_ARTIST = "SELECT AID,NAME,ADDRESS,PHOTO FROM ARTIST_INFO WHERE AID = ?";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			//taking aid value from user
			int aid = 0;
			if(sc!=null) {
				System.out.println("Enter aid of artist");
				aid = sc.nextInt();
			}
			//establishing the connection with jdbc
			try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","System","tiger");
				PreparedStatement ps = con.prepareStatement(RETRIEVE_ARTIST);	){
				//set query params
				if(ps!= null && con != null)
					ps.setInt(1, aid);
				//creating resultset obj to get the results
				try(ResultSet rs = ps.executeQuery();){
					//processing the results
					if(rs != null) {
						if(rs.next()) {
							aid = rs.getInt(1);
							String name = rs.getString(2);
							String add = rs.getString(3);
							System.out.println(aid+" "+name+" "+add);
							//get inputstream pointing to blob values
							try(InputStream is = rs.getBinaryStream(4);
								//create outputStream pointing to destination
								OutputStream os = new FileOutputStream("Retrive_image.jpg");)
							{
								//Copy blob value to destination file
								IOUtils.copy(is,os);
								System.out.println("Blob value is retrieved and stored in file");
							}//try4
						}//if
						else {
							System.out.println("Record not found");
						}//else
					}//if
				}//try3
			}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record insertion failed !!!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
