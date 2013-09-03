package org.uengine.search.solr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;

public class SolrSearch {
	
	String keyword;
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
	
	SolrFileItem solrFileItem;
		public SolrFileItem getSolrFileItem() {
			return solrFileItem;
		}
		public void setSolrFileItem(SolrFileItem solrFileItem) {
			this.solrFileItem = solrFileItem;
		}
	public SolrSearch(){
		
	}
	
	public void search() throws Exception{
		
		SolrServer server = SolrData.solrServer;
		SolrQuery query = new SolrQuery();
//		query.setQuery("text:즐겨찾기");
		query.setQuery("text:"+keyword);
		query.setHighlight(true);
		

//		ModifiableSolrParams query = new ModifiableSolrParams();
//		query.set("q", "database");
//		query.set("df", "manu");
//		query.set("timestamp", ":[* TO NOW]");
		QueryResponse queryResponse = server.query(query);
		List<SolrFileItem> beans = queryResponse.getBeans(SolrFileItem.class);
		
//		SolrDocumentList docs = queryResponse.getResults();
		Iterator<SolrDocument> iter = queryResponse.getResults().iterator();

	    while (iter.hasNext()) {
	      SolrDocument resultDoc = iter.next();
	      Object obj = resultDoc.getFieldValue("content");
	      if( obj instanceof String){
	    	  String content = (String) resultDoc.getFieldValue("content");
	    	  
	    	  String id = (String) resultDoc.getFieldValue("id"); //id is the uniqueKey field
	    	  System.out.println("=========== Query Result2222 { ");
	    	  System.out.println(id);
	    	  System.out.println(content);
	    	  System.out.println(" } =======================");
	      }else if( obj instanceof ArrayList){
	    	  ArrayList list = (ArrayList)resultDoc.getFieldValue("content");
	    	  for( int i = 0 ; i < list.size(); i++){
	    		  System.out.println(list.get(i));
	    	  }
	      }
//	      if (queryResponse.getHighlighting().get(id) != null) {
//	        List<String> highlightSnippets = queryResponse.getHighlighting().get(id).get("content");
//	      }
	    }
		
		System.out.println("=========== Query Result { ");
		
		for(SolrFileItem bean : beans){
			System.out.println(bean.getId() + "==>");
			System.out.println(bean.toString());
		}
		
		System.out.println(" } =======================");

		
	}

}
