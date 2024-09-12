package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectPost {
	private static final String INSERT_POST = "INSERT INTO public.\"PRODUCT\" VALUES(NEXTVAL('public.\"PID_SEQ\"'),?,?,?,?)";
	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in);){
			String name =null,status =null;
			float price = 0.0f,qty = 0.0f;
			if(sc!=null) {
				System.out.println("Name:: ");
				name = sc.next();
				System.out.println("price:::");
				price = sc.nextFloat();
				System.out.println("qty:: ");
				qty = sc.nextFloat();
				System.out.println("Status:::");
				status = sc.next();
			}
			try(Connection con = DriverManager.getConnection("jdbc:postgresql:NTAJ","postgres","tiger");
				PreparedStatement ps = con.prepareStatement(INSERT_POST);	){
				ps.setString(1, name);
				ps.setFloat(2, price);
				ps.setFloat(3, qty);
				ps.setString(4, status);
				
				int count = 0;
				if(ps!=null)
					count = ps.executeUpdate();
				
				if(count == 0 ) {
					System.out.println("No record inserted");
				}else {
					System.out.println("Record inserted");
				}
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
