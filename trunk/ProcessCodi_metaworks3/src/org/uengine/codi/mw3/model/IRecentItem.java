package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.uengine.codi.mw3.knowledge.TopicNode;

@Table(name="recentItem")
public interface IRecentItem extends IDAO{

	@Id
	public String getEmpCode();
	public void setEmpCode(String empCode);

	public Date getUpdateDate();
	public void setUpdateDate(Date updateDate);
	
	@ORMapping(
		databaseFields = { "itemType", "itemId" },
		objectFields = { "type", "id" })
	public TopicNode getTopicNode();
	public void setTopicNode(TopicNode topicNode);
	
	@ServiceMethod
	public void add() throws Exception;
	
	public IRecentItem updateDate() throws Exception;
	
	public IRecentItem findMe() throws Exception;
	
}
