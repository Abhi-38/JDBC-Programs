package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//jdbc app for doing transactions
public class TransferMoneyMgmt {
	public static void main(String[] args) {
		//taking input from user
		int srcAcc = 0,destAcc = 0;
		double amount = 0;
		try(Scanner sc = new Scanner(System.in);){
			System.out.println("Enter the source account number: ");
			srcAcc = sc.nextInt();
			System.out.println("Enter the Destination sccount number: ");
			destAcc = sc.nextInt();
			System.out.println("Enter the amount: ");
			amount = sc.nextDouble();
		}//try1
		catch(Exception e) {
			e.printStackTrace();
		}//catch
		//establishing the Connection
		try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//creating the jdbc statement obj	
			Statement st = con.createStatement();	){
			//disabling the autocommit
			if(con!=null)
				con.setAutoCommit(false);
			
			if(st!=null) {
				//add queries to the batch
				//add query to withdraw money
				st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE = BALANCE - "+amount+" WHERE ACNO ="+srcAcc);
				//now add query to credit the money
				st.addBatch("UPDATE JDBC_ACCOUNT SET BALANCE = BALANCE + "+amount+" WHERE ACNO ="+destAcc);
				//execute the batch queries
				int result[] = st.executeBatch();
				boolean flag = true;
				//processing the results
				for(int i=0;i<result.length;++i) {
					if(result[i] == 0) {
						flag = false;
						break;
					}//if
				}//for
				if(flag == true) {
					con.commit();//commit the transaction
					System.out.println("Transaction is commited, Money is transferred");
				}//if
				else {
					con.rollback();
					System.out.println("Transaction is failed,Money is not transferred");
				}//else
			}//if
		}//try2
		catch(SQLException se) {
			se.printStackTrace();
		}//catch
		catch(Exception e) {
			e.printStackTrace();
		}//catch
	}//main
}//class
