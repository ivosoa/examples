/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.iso.luceneexample;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author Ivo
 */
public class Indexer {

  private Version LUCENE_VERSION = Version.LUCENE_4_9;
  private StandardAnalyzer analyzer = new StandardAnalyzer(LUCENE_VERSION); 

  public void doIndex(ResultSet rs) throws IOException{
    //Directory idx = new RAMDirectory();
    Directory idx = FSDirectory.open(new File("indexDir"));

    IndexWriterConfig config = new IndexWriterConfig(LUCENE_VERSION, analyzer);

    IndexWriter w = new IndexWriter(idx, config);
    try {
      while (rs.next()) {
        addDoc(w, rs);
      }
    } catch (SQLException ex) {
      Logger.getLogger(Indexer.class.getName()).log(Level.SEVERE, null, ex);
    }
    w.close();
  }

  public void doSearch(String querystr, boolean multiple) throws ParseException, IOException {
    Query q;
    if(multiple){
     MultiFieldQueryParser qp = new MultiFieldQueryParser(LUCENE_VERSION, new String[] {"id", "name", "city"}, analyzer);
     qp.setDefaultOperator(QueryParser.Operator.OR);
     q = qp.parse(querystr);
    }
    else{
      q = new QueryParser(LUCENE_VERSION, "name", analyzer).parse(querystr);
    }
    Directory idx = FSDirectory.open(new File("indexDir"));
    
    //search
    int hitsPerPage = 10;
    try (IndexReader reader = DirectoryReader.open(idx)) {
      IndexSearcher searcher = new IndexSearcher(reader);
      TopScoreDocCollector collector = TopScoreDocCollector.create(hitsPerPage, true);
      searcher.search(q, collector);
      ScoreDoc[] hits = collector.topDocs().scoreDocs;
      
      //display results
      System.out.println("Found " + hits.length + " hits.");
      for (int i = 0; i < hits.length; ++i) {
        int docId = hits[i].doc;
        Document d = searcher.doc(docId);
        System.out.println((i + 1) + ". " + d.get("name") + "\t" + d.get("city"));
      }
    }
  }

  private void addDoc(IndexWriter w, ResultSet rs) throws IOException, SQLException {
    Document doc = new Document();
    doc.add(new StringField("id", rs.getString("customer_id"), Field.Store.YES));
    doc.add(new TextField("name", rs.getString("name"), Field.Store.YES));
    doc.add(new StringField("city", rs.getString("city"), Field.Store.YES));
    w.addDocument(doc);
  }
  
}
