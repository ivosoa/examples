package de.iso.solrexample;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

/**
 *
 * @author Ivo
 */
public class Solr {

  private final String core = "/solrExample";
  private final HttpSolrServer solr = new HttpSolrServer("http://localhost:8983/solr" + core);

  public void addDoc(Map map) {
    try {
      SolrInputDocument doc = new SolrInputDocument();
      doc.addField("id", map.get("id"));
      doc.addField("name", map.get("name"));
      doc.addField("city", map.get("city"));
      solr.add(doc);
      solr.commit();
    } catch (SolrServerException | IOException ex) {
      Logger.getLogger(Solr.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void search(String queryStr) {
    try {
      SolrQuery query = new SolrQuery();
      query.setQuery(queryStr);
      //query.setQuery( "*:*" );
      query.setFields("name", "city");
      query.setStart(0);

      QueryResponse response = solr.query(query);
      SolrDocumentList results = response.getResults();
      System.out.println("---" + results.size());
      for (int i = 0; i < results.size(); ++i) {
        System.out.println(results.get(i));
      }
    } catch (SolrServerException ex) {
      Logger.getLogger(Solr.class.getName()).log(Level.SEVERE, null, ex);
    }

  }

}
