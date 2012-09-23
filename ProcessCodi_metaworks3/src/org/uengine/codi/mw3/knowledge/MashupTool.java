package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import javax.persistence.Id;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

public class MashupTool {
	
	public MashupTool(){}
	
	ArrayList<SearchResult> searchResults;
	@Face(displayName="검색결과")
		public ArrayList<SearchResult> getSearchResults() {
			return searchResults;
		}
	
		public void setSearchResults(ArrayList<SearchResult> searchResults) {
			this.searchResults = searchResults;
		}

	String keyword;
	@Id
	@Face(displayName="검색어")
		public String getKeyword() {
			return keyword;
		}
	
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
	String targetNodeId;
	@Hidden
		public String getTargetNodeId() {
			return targetNodeId;
		}
	
		public void setTargetNodeId(String targetNodeId) {
			this.targetNodeId = targetNodeId;
		}

	@ServiceMethod(callByContent=true, payload={"keyword", "targetNodeId"})
	@Face(displayName="검색")
	public void search() throws Exception{};
}
