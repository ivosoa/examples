/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.iso.luceneexample;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.queryparser.classic.ParseException;

/**
 *
 * @author Ivo
 */
public class Main {
  public static void main(String[] args){
    
    Database db = new Database();
    String search = "Jumbo Eagle Corp";
    try {
      Indexer ind = new Indexer();
      ind.doIndex(db.getResult());
      ind.doSearch(search, true);    
    } catch (IOException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
      Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
    }
    
  }  
}
