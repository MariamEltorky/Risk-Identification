/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectmanagement;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mariam Eltorky
 */
public class DBConnection {
     Connection conn=null;
     
     public static Connection connectDB () {
        try{
            Class.forName("org.sqlite.JDBC");
           Connection conn=DriverManager.getConnection("jdbc:sqlite:projectmanagement.db");
          System.out.println("Connection Sucessful");
           return conn;
       }catch(Exception ex) {
            System.out.println("Connection Failed");
            return null;
      }
    }
  
}
