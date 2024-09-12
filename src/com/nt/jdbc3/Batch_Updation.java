package com.nt.jdbc3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Batch_Updation {
	public static void main(String[] args) {
		
		//loading the jdbc driver class
		try(//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establishing the COnnection
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
			//Creating the jdbc Statement obj
			Statement st = con.createStatement();	){
			
			//now adding the batch queries

			
			st.addBatch("INSERT INTO STUDENT VALUES(3,'MANU','CHENNAI',88.88)");
			st.addBatch("INSERT INTO STUDENT VALUES(4,'JAGGU','DHOLAKPUR',68.86)");
			st.addBatch("UPDATE STUDENT SET SNAME='MAHESH' WHERE SNAME='JAGGU'");
			st.addBatch("INSERT INTO STUDENT VALUES(5,'JADU','KHALBALPUR',78.89)");
			st.addBatch("DELETE FROM STUDENT WHERE sno = 5");

			//now processing the results
			//to process the batch updation results we are creating the array
			int result[] = st.executeBatch();
			int sum = 0;
			//now process the results
			for(int i = 0;i<result.length;i++)
				sum = sum + result[i];
			
			System.out.println(sum+" records are updated");
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
