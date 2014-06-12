package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.Hashtable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;

@Face(displayName="WIKI 에서 검색", ejsPath="dwr/metaworks/org/uengine/codi/mw3/knowledge/MashupKMS.ejs")
public class MashupWiki extends MashupTool {
	
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
					searchResult.setUrl((String)ht.get("TEXT"));
					searchResult.setTitle(((String)ht.get("TITLE")).trim());
					searchResult.setDescription((String)ht.get("XML_S"));
					searchResult.setThumbnail((String)ht.get("IMAGE_URL"));
					searchResult.setTargetNodeId(getTargetNodeId());
					searchResult.setResultType("WIKI");
					getSearchResults().add(searchResult);
				}
				setSearchResults(searchResults);
			}
		}
		
	}
}
