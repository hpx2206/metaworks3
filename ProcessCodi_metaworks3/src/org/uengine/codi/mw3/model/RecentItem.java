package org.uengine.codi.mw3.model;

import java.util.Calendar;
import java.util.Date;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.knowledge.TopicNode;

public class RecentItem extends Database<IRecentItem> implements IRecentItem{
	
	public RecentItem() {
		// TODO Auto-generated constructor stub
	}

	String empCode;
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
		
	TopicNode topicNode;
		public TopicNode getTopicNode() {
			return topicNode;
		}
		public void setTopicNode(TopicNode topicNode) {
			this.topicNode = topicNode;
		}

	Date updateDate;
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		
	@AutowiredFromClient
	public Session session;
	
	public void add() throws Exception {
		
		this.setEmpCode(session.getEmployee().getEmpCode());
		this.setUpdateDate(Calendar.getInstance().getTime());
		
		IRecentItem findMe = this.findMe();
		
		if(findMe == null){
			createDatabaseMe();
			flushDatabaseMe();
		}else {
			updateDate();
		}
		
	}
	
	public IRecentItem updateDate() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("update recentItem set updateDate=?updateDate where empcode=?empCode and itemType=?itemType and itemId=?itemId");
		
		IRecentItem updateMe = (IRecentItem) Database.sql(IRecentItem.class, sql.toString());
		
		updateMe.setEmpCode(session.getEmployee().getEmpCode());
		updateMe.setUpdateDate(this.getUpdateDate());
		updateMe.set("itemType", this.getTopicNode().getType());
		updateMe.set("itemId", this.getTopicNode().getId());
		updateMe.update();
		
		return updateMe;
		
	}
	
	public IRecentItem findMe() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from recentItem where empcode=?empCode and itemType=?itemType and itemId=?itemId");
		
		IRecentItem findMe = (IRecentItem) Database.sql(IRecentItem.class, sql.toString());
		
		findMe.setEmpCode(session.getEmployee().getEmpCode());
		findMe.set("itemType", this.getTopicNode().getType());
		findMe.set("itemId", this.getTopicNode().getId());
		findMe.select();
		
		if(findMe.next())
			return findMe;
		else
			return null;
	}
	
}
