package org.uengine.codi.mw3.marketplace;

import org.metaworks.dao.Database;

public class AppMapping extends Database<IAppMapping> implements IAppMapping {
	
	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}
	
	String comCode;
		public String getComCode() {
			return comCode;
		}
		public void setComCode(String comCode) {
			this.comCode = comCode;
		}
	
	String appName;
		public String getAppName() {
			return appName;
		}
		public void setAppName(String appName) {
			this.appName = appName;
		}
	
	boolean isDeleted;
		public boolean getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
	boolean selected;
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
	public IAppMapping findMe() throws Exception {

		IAppMapping findApp = (IAppMapping) Database.sql(IAppMapping.class, "select * from appmapping where appid=?appId and comcode=?comCode");
		
		findApp.setAppId(this.getAppId());
		findApp.setComCode(this.getComCode());
		findApp.select();
		
		return findApp;
	}
	
	public IAppMapping findMyApps() throws Exception {
		
		IAppMapping findApp = (IAppMapping) Database.sql(IAppMapping.class, "select * from appmapping where comcode=?comCode and isdeleted=?isDeleted");
		
		findApp.setComCode(this.getComCode());
		findApp.setIsDeleted(this.getIsDeleted());
		findApp.select();
		
		return findApp;
	}
	
	
}
