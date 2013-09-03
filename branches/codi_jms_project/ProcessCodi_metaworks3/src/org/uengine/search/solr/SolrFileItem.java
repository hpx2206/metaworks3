package org.uengine.search.solr;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class SolrFileItem {

	@Field
	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
	@Field("title")
	String[] title;
		public String[] getTitle() {
			return title;
		}
		public void setTitle(String[] title) {
			this.title = title;
		}
	
	@Field("content")
	String[] content;
		public String[] getContent() {
			return content;
		}
		public void setContent(String[] content) {
			this.content = content;
		}
	
	@Field("author")
	String author;
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}

	@Field("last_modified")
	Date last_modified;
		public Date getLast_modified() {
			return last_modified;
		}
		public void setLast_modified(Date last_modified) {
			this.last_modified = last_modified;
		}
		
}