/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groovyxmlexample;

import java.io.File;

/**
 *
 * @author Ivo
 */
public class GroovyXMLExample {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    File xmlFile = new File("xmlInput/items.xml");
    GroovyXmlTransformer gxt = new GroovyXmlTransformer();
    
    System.out.println(gxt.transformToHtml(xmlFile));
    GroovyXmlTransformerWithScript gws = new GroovyXmlTransformerWithScript();
    gws.transformToXml(new File("scripts/script1.groovy"), xmlFile);
    
  }
  
}
