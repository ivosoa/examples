package de.iso.solrexample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ivo
 */
public class Database {

  public Connection getConnection() {
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

  public void doSelect() {
    String query = "select * from customer";
    try {
      Statement st = getConnection().createStatement();
      ResultSet rs = st.executeQuery(query);

      while (rs.next()) {
        String n = rs.getString("Name");
        String c = rs.getString("City");
        System.out.println(c + "   " + n);
      }

    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public ResultSet getResult() {
    String query = "select * from customer";
    ResultSet rs = null;
    try {
      Statement st = getConnection().createStatement();
      rs = st.executeQuery(query);

    } catch (SQLException ex) {
      Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
    }
    return rs;
  }

}