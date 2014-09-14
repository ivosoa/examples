/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groovyxmlexample

import groovy.xml.MarkupBuilder

/**
 *
 * @author Ivo
 */
class GroovyXmlTransformer {
  
  def transformToHtml(File xmlFile){
    def sl = new XmlSlurper()
    def inXml = new XmlSlurper().parse(xmlFile)
    def writer = new StringWriter()
    def writer0 = new FileWriter(new File("xmlOutput/test.html"))
    def outXml = new MarkupBuilder(writer0)
 
    outXml.html {
      head {
        title {'list of items'}
      }
      body {
        h1 {'items'}
        ul {
          inXml.item.each {
            li(it.name.text())
          }
        }
      }
    }
    writer0.toString()
  }
}

