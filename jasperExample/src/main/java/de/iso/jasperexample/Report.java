/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.iso.jasperexample;

import java.sql.Connection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author Ivo
 */
public class Report {
  private JasperReport jasperreport;
  private JasperPrint jasperPrint;
  private Connection connection;
  private final String reportName;
  
  public Report(String reportName, Connection connection){
    this.connection = connection;
    this.reportName = reportName;
  }
  
  public void compileJasperReports(){
    try {
      jasperreport = JasperCompileManager.compileReport(reportName+".jrxml");
    } catch (JRException ex) {
      Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void generateReport(){
    try {
      //Should never happen
      if(connection == null){
        connection = Database.getConnection();
      }
      
      jasperPrint = JasperFillManager.fillReport(jasperreport, new HashMap(), connection);
      
      JasperExportManager.exportReportToPdfFile(jasperPrint, reportName+".pdf");
      
    } catch (JRException ex) {
      Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
}
