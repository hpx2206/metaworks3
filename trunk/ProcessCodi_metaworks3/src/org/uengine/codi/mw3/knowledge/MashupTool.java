package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import javax.persistence.Id;

import org.metaworks.annotation.ServiceMethod;

public class MashupTool {
	
	public MashupTool(){}
	
	ArrayList<SearchResult> searchResults;
		public ArrayList<SearchResult> getSearchResults() {
			return searchResults;
		}
	
		public void setSearchResults(ArrayList<SearchResult> searchResults) {
			this.searchResults = searchResults;
		}

	String keyword;
	@Id
		public String getKeyword() {
			return keyword;
		}
	
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
	String targetNodeId;
	
		public String getTargetNodeId() {
			return targetNodeId;
		}
	
		public void setTargetNodeId(String targetNodeId) {
			this.targetNodeId = targetNodeId;
		}

	@ServiceMethod(callByContent=true, payload={"keyword", "targetNodeId"})
	public void search() throws Exception{};
}
