package org.uengine.codi.mw3.marketplace;

import org.metaworks.dao.Database;
import org.uengine.codi.mw3.model.ICompany;

public class AppMapping extends Database<IAppMapping> implements IAppMapping {
	
	int appId;
		public int getAppId() {
			return appId;
		}
		public void setAppId(int appId) {
			this.appId = appId;
		}
	
	ICompany comCode;
		public ICompany getComCode() {
			return comCode;
		}
		public void setComCode(ICompany comCode) {
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
	
	
}
