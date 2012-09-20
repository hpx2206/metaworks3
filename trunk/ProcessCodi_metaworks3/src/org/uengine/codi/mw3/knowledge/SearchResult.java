package org.uengine.codi.mw3.knowledge;

import org.metaworks.annotation.ServiceMethod;

public class SearchResult {
	
	String title;
	String description;
	String thumbnail;
	String url;
	String targetNodeId;

	

	public String getTargetNodeId() {
		return targetNodeId;
	}
	public void setTargetNodeId(String targetNodeId) {
		this.targetNodeId = targetNodeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	@ServiceMethod(callByContent=true)
	public IWfNode choose(){
		return null;
	}
	
}
