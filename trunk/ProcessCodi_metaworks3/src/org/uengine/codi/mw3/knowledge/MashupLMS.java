package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Hashtable;

import org.metaworks.annotation.ServiceMethod;


public class MashupLMS extends MashupTool{

	@ServiceMethod(callByContent=true, payload={"keyword", "targetNodeId"})
	public void search() throws Exception{
		
//		getSearchResults().add(new SearchResult());
		if( getKeyword() != null){
			setSearchResults(new ArrayList<SearchResult>());
			QueryMaker QueryMaker = new QueryMaker();
			ArrayList result = QueryMaker.getResult( getKeyword() );
			if( result != null){
				for(int i = 0; i < result.size(); i++){
					Hashtable ht = (Hashtable)result.get(i);
					SearchResult searchResult = new SearchResult();
					searchResult.setUrl((String)ht.get("URL"));
//					searchResult.setTitle((String)ht.get("TITLE"));
					searchResult.setTitle((String)ht.get("URL"));	// 일단 URL 을 셋팅함 추후 위에를 지우고 삭제함
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
