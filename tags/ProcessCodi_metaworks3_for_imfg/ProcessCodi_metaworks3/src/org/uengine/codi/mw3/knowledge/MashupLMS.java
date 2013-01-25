package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Hashtable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(displayName="LMS에서 검색", options={"hideEditBtn"}, values= {"true"})
public class MashupLMS extends MashupTool{

	@ServiceMethod(callByContent=true, payload={"keyword", "targetNodeId"})
	public void search() throws Exception{
		
		if( getKeyword() != null){
			setSearchResults(new ArrayList<SearchResult>());
			QueryMaker QueryMaker = new QueryMaker();
			ArrayList result = QueryMaker.getResult( getKeyword() );
			if( result != null){
				for(int i = 0; i < result.size(); i++){
					Hashtable ht = (Hashtable)result.get(i);
					SearchResult searchResult = new SearchResult();
					searchResult.setUrl((String)ht.get("URL"));
					searchResult.setTitle((String)ht.get("TITLE"));
					searchResult.setThumbnail((String)ht.get("THUMBNAIL"));
					searchResult.setDescription((String)ht.get("INTRODUCTION"));
					searchResult.setTargetNodeId(getTargetNodeId());
					searchResult.setResultType("LMS");
					getSearchResults().add(searchResult);
				}
				setSearchResults(searchResults);
			}
		}
		
	}

}
