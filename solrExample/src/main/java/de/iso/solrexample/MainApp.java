/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.iso.solrexample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Ivo
 */
public class MainApp {

  public static void main(String[] args) throws SQLException {

    Database database = new Database();
    Solr solr = new Solr();
    
    ResultSet rs = database.getResult();
    while (rs.next()){
      Map map = new HashMap();
      map.put("id", rs.getString("customer_id"));
      map.put("name", rs.getString("name"));
      map.put("city", rs.getString("city"));
      solr.addDoc(map);
    }

    solr.search("Jumbo Eagle Corp");

  }

}
