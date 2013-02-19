package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;
import java.util.List;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;

import com.benfante.jslideshare.SlideShareAPIImpl;
import com.benfante.jslideshare.SlideShareConnectorImpl;
import com.benfante.jslideshare.messages.Slideshow;
import com.benfante.jslideshare.messages.Tag;

@Face(displayName="슬라이드 검색", ejsPath="genericfaces/CleanObjectFace.ejs", options={"hideEditBtn"}, values={"true"})
public class MashupSlideshare extends MashupTool {
	
	@Hidden
	@ServiceMethod(callByContent=true, payload={"keyword", "targetNodeId"})
	public void search() throws Exception{
		if( getKeyword() != null){
			SlideShareAPIImpl sImpl = new SlideShareAPIImpl(new SlideShareConnectorImpl( "ru6hIw7A" , "cK20Q4ep" ));
					
			Tag tag = null;
			
			try{
				tag = sImpl.getSlideshowByTag(keyword);
			}catch(Exception e){				
			}
			
			if( tag != null){
				setSearchResults(new ArrayList<SearchResult>());
				
				List<Slideshow> showList = tag.getSlideshows();
				for(int i = 0; i <showList.size(); i++){
					Slideshow slideshow = (Slideshow)showList.get(i);
					SearchResult searchResult = new SearchResult();
					searchResult.setTitle(slideshow.getTitle());
					searchResult.setDescription(slideshow.getEmbedCode());
					searchResult.setThumbnail(slideshow.getThumbnail());
					searchResult.setTargetNodeId(getTargetNodeId());
					searchResult.setResultType("slideshare");
					getSearchResults().add(searchResult);
				}
				setSearchResults(searchResults);
			}
		}
	}
	
	@Hidden
	@Override
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public void addItem() throws Exception{
		
	}
}
