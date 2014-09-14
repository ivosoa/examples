/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package groovyxmlexample

import groovy.xml.XmlUtil
import groovy.xml.MarkupBuilder
import groovy.xml.StreamingMarkupBuilder

/**
 *
 * @author Ivo
 */
class GroovyXmlTransformerWithScript {
	
  def transformToXml(File scriptFile, File xmlFile){
    def binding = new Binding()
    def shell = new GroovyShell(binding)
    
    def sl = new XmlSlurper()
    def writer = new FileWriter(new File("xmlOutput/test.xml"))
    def outXml = new MarkupBuilder(writer)
    def outStreamingXML = new StreamingMarkupBuilder()
        
    binding.input = new XmlSlurper().parse(xmlFile)
    
    //use normal Markupbuilder
    binding.builder = outXml 
    binding.buildXml = {cl ->
      //outXml.root cl
      outXml.with(cl)
    }
    
    //using StreamingMarkupBuilder (usefull for long XML files)
    binding.transform = { cl ->
      def fWriter = new FileWriter(new File("xmlOutput/test.xml"))
      Writable w = outStreamingXML.bind cl
      
      //pretty print with declaration
      XmlUtil.serialize(w, fWriter)
      //w.writeTo(fWriter)
    }
    
    if(scriptFile.exists()) {
      scriptFile.withReader('UTF-8') { Reader reader ->
        shell.evaluate(reader, scriptFile.name)
      }
    }   
    
  }
}

