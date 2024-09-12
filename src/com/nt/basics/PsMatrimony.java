package com.nt.basics;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

//jdbc app to insert matrimony information
public class PsMatrimony {
	private static final String INSERT_MATRIMONY = "INSERT INTO MATRIMONY VALUES(MAT_CID.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			String cname = null,gender = null,dob = null,
					photo=null,resume=null,doj=null,biodata=null,audio=null,video=null;
			if(sc!=null) {
				System.out.println("Enter the name of candidate: ");
				cname = sc.nextLine();
				System.out.println("Enter the gender: ");
				gender = sc.next();
				System.out.println("Enter date of birth(dd-mm-yyyy): ");
				dob = sc.next();
				System.out.println("Enter location of photo: ");
				photo = sc.next();
				System.out.println("Enter the location of resume: ");
				resume = sc.next();
				System.out.println("Enter the first date of joining: ");
				doj = sc.next();
				System.out.println("Enter biodata location: ");
				biodata = sc.next();
				System.out.println("Enter audio location: ");
				audio = sc.next();
				System.out.println("Enter video location: ");
				video = sc.next();

				//converting dob and doj to java.sql.Date format
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				java.sql.Date sqdob = new java.sql.Date(sdf.parse(dob).getTime());
				java.sql.Date sqdoj = new java.sql.Date(sdf.parse(doj).getTime());

				//converting photo to binaryStream
				try(InputStream photoof = new FileInputStream(photo);
						//converting resume to characterStream	
						Reader rs1 = new FileReader(resume);
						//converting biodata to characterStream	
						Reader rs2 = new FileReader(biodata);
						//converting audio to BinaryStream	
						InputStream audioof = new FileInputStream(audio);
						//converting video to BinaryStream
						InputStream videoof = new FileInputStream(video);	){
					//establishing the connection
					try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
							//creating PreparedStatement obj
							PreparedStatement ps = con.prepareStatement(INSERT_MATRIMONY);	){
						if(con!=null && ps!=null) {
							//set query params of Pre-compiled query
							ps.setString(1, cname);
							ps.setString(2, gender);
							ps.setDate(3, sqdob);
							ps.setBinaryStream(4,photoof);
							ps.setCharacterStream(5, rs1);
							ps.setDate(6, sqdoj);
							ps.setCharacterStream(7, rs2);
							ps.setBinaryStream(8, audioof);
							ps.setBinaryStream(9, videoof);
						}
						//now execute the query
						int record = 0;
						if(ps!=null) {
							record = ps.executeUpdate();
						}
						//processing the result
						if(record == 0)
							System.out.println("Record not inserted.");
						else
							System.out.println(record+" row is inserted.");
					}//try1
				}//try2
			}//if	
		}//try3
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record Insertion Failed.");
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
