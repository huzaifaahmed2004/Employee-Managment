/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EMP;
import java.sql.*;

public class DBConnection {
   public Connection conn;
    Statement stm;
    ResultSet resultSet;
    DBConnection(){
        try {
            // Load the JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            // Establish the connection
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/emp","Huzaifa","H1u2Z3a4I5.");
        
            stm=conn.createStatement();
            
        } catch (Exception ex ) {
            ex.printStackTrace();
        }
    }
   
     public static void main(String[] args) {
     DBConnection db;
     }
}
