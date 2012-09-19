package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.annotation.ServiceMethod;

public class MashupKMS extends MashupTool{

	@ServiceMethod	
	public void search() {
		setSearchResults(new ArrayList<SearchResult>());
		getSearchResults().add(new SearchResult());
		
		setSearchResults(searchResults);
		
	}

}
