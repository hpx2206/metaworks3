package org.uengine.codi.mw3.knowledge;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;

public class SearchResult {
	
	String title;
	String description;
	String thumbnail;
	String url;
	String targetNodeId;
	String resultType;

	
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
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] choose() throws Exception{
		
		WfNode wfNode = new WfNode();
		wfNode.load(targetNodeId);
		wfNode.session = session;
		wfNode.setNameNext(title);
		wfNode.setTypeNext("iframe");	// TODO 임시 - 추후에 메서드로 빼서 받아와야함
		
		return wfNode.add();
	}
	
	@AutowiredFromClient
	public Session session;
}
