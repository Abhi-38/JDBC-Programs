package com.nt.jdbc2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CsMySQL {
    private static final String PROCEDURE_SQL = "{CALL COUNT_CITY}";

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection("jdbc:mysql:///sales", "root", "root");
             // create Callable statement object containing PL/SQL procedure as pre-compiled query
             CallableStatement cs = con.prepareCall(PROCEDURE_SQL)) {
            
            // execute the stored procedure
            boolean hasResult = cs.execute();

            if (hasResult) {
                // get the result set
                ResultSet rs = cs.getResultSet();

                // move the cursor to the first row
                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("The City Count is :: " + count);
                }
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
