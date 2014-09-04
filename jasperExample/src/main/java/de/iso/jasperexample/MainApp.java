/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.iso.jasperexample;

/**
 *
 * @author Ivo
 */
public class MainApp {
  
  public static void main(String[] args){
    Report report = new Report("test", Database.getConnection());
    report.compileJasperReports();
    report.generateReport();
  }
  
}
