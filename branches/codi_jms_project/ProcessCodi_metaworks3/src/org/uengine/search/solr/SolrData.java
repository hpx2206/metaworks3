package org.uengine.search.solr;

import java.io.File;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.request.ContentStreamUpdateRequest;
import org.apache.solr.common.SolrInputDocument;
import org.metaworks.website.MetaworksFile;

public class SolrData {
	
	static SolrServer solrServer;
	
	public void init(){
		if( solrServer == null ){
			solrServer = new HttpSolrServer("http://localhost:8983/solr/");
		}
	}
	public SolrData(){
	}

	public void insertText(String id , String type , String text){
		this.init();
		try {
			SolrInputDocument doc = new SolrInputDocument();
			doc.addField("id", type + "_" + id);
			doc.addField("name", text );
			
			solrServer.add(doc);
			solrServer.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFile(MetaworksFile file) throws Exception{
		this.init();
		
		String fileName = file.overrideUploadPathPrefix() + "/" + file.getUploadedPath();
		String contentType = file.getMimeType();
		File uploadFile = new File(fileName);
		
		ContentStreamUpdateRequest req = new ContentStreamUpdateRequest("/update/extract");
		
		req.addFile(uploadFile , contentType);
		req.setParam("literal.id", fileName);
		req.setParam("literal.name", file.getFilename());
		
		req.setAction(ContentStreamUpdateRequest.ACTION.COMMIT, true, true);
		req.process(solrServer);
	}
}
