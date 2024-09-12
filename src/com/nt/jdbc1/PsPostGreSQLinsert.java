package com.nt.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsPostGreSQLinsert {
	private static final String INSERT_POSTGRESQL = "INSERT INTO public.\"PRODUCT\" VALUES (NEXTVAL('public.\"PID_SEQ\"'), ?, ?, ?, ?)";
	public static void main(String[] args) {
		//taking input from user
		try(Scanner sc = new Scanner(System.in);){
			//int pid = 0;
			String name = null,status = null;
			float price = 0.0f,qty = 0.0f;
			int n = 0;
			System.out.println("Enter number of products you want to enter");
			n = sc.nextInt();
			for(int i = 1;i<=n;i++) {
				if(sc!=null) {
					/*
					 * System.out.println("Enter product id: "); pid = sc.nextInt();
					 */
					System.out.println("Enter name of product: ");
					name = sc.next();
					System.out.println("Enter the price: ");
					price = sc.nextFloat();
					System.out.println("Enter the quantity: ");
					qty = sc.nextFloat();
					System.out.println("Enter the status of the product: ");
					status = sc.next();
				}//if
				//establishing the connection and creating preparedstatement obj
				try(Connection con = DriverManager.getConnection("jdbc:postgresql:NTAJ","postgres","tiger");
					PreparedStatement ps = con.prepareStatement(INSERT_POSTGRESQL);	){
					//set query params 
					if(ps!=null) {
						//ps.setInt(1, pid);
						ps.setString(1, name);
						ps.setFloat(2, price);
						ps.setFloat(3, qty);
						ps.setString(4, status);
					}//if
					//executing the query
					int count = 0;
					if(ps!=null)
						count = ps.executeUpdate();
					
					if(count == 0)
						System.out.println("No Records inserted");
					else
						System.out.println(i+" records inserted");
				}//try1
			}//for
		}//try2
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
