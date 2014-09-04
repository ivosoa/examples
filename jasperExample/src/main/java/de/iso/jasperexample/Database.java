/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.iso.jasperexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivo
 */
public class Database {
  
  public static Connection getConnection(){
    Connection connection = null;
    
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver");
      connection = DriverManager.getConnection("jdbc:derby://localhost:1527/sample", "app", "app");
    } catch (ClassNotFoundException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Class not Found: ", ex);
    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Database Connection Error: ", ex);
    }
    
    return connection;
  }
  
}
