package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;

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

	String itemType;
		public String getItemType() {
			return itemType;
		}
		public void setItemType(String itemType) {
			this.itemType = itemType;
		}

	String itemId;
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
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
		
		updateMe.setEmpCode(this.getEmpCode());
		updateMe.setUpdateDate(this.getUpdateDate());
		updateMe.setItemId(this.getItemId());
		updateMe.setItemType(this.getItemType());
		updateMe.update();
		
		return updateMe;
		
	}
	
	public IRecentItem findMe() throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from recentItem where empcode=?empCode and itemType=?itemType and itemId=?itemId");
		
		IRecentItem findMe = (IRecentItem) Database.sql(IRecentItem.class, sql.toString());
		
		findMe.setEmpCode(this.getEmpCode());
		findMe.setItemId(this.getItemId());
		findMe.setItemType(this.getItemType());
		findMe.select();
		
		if(findMe.next())
			return findMe;
		else
			return null;
	}
	
}
