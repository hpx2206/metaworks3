package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;

public class MashupKMS extends MashupTool{

	@ServiceMethod(payload={"keyword", "targetNodeId"})
	public void search() {
		setSearchResults(new ArrayList<SearchResult>());
		
		SearchResult searchResult = new SearchResult();
		searchResult.setTargetNodeId(getTargetNodeId());
		searchResult.setTitle("XXXXX");
		
		getSearchResults().add(searchResult);
		
		setSearchResults(searchResults);
		
	}

}
