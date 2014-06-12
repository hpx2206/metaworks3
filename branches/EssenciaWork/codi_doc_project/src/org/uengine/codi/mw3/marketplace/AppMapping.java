package org.uengine.codi.mw3.marketplace;

import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;
import org.uengine.cloud.saasfier.TenantContext;

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
	
	String projectName;
		public String getProjectName() {
			return projectName;
		}
		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}
		
	MetaworksFile logoFile;
		public MetaworksFile getLogoFile() {
			return logoFile;
		}
		public void setLogoFile(MetaworksFile logoFile) {
			this.logoFile = logoFile;
		}
		
	public IAppMapping findMe() throws Exception {

		IAppMapping findApp = (IAppMapping) Database.sql(IAppMapping.class, "select appmapping.appId, appmapping.comcode, appmapping.appname, appmapping.isdeleted, bpm_knol.name projectName from appmapping appmapping, app, bpm_knol where appmapping.appId = app.appId and app.projectId = bpm_knol.id and appmapping.appid=?appId and appmapping.comcode=?comCode");
		
		findApp.setAppId(this.getAppId());
		findApp.setComCode(this.getComCode());
		findApp.select();
		
		if(findApp.next())
			return findApp;
		else
			return null;
	}
	
	public IAppMapping findMyApps() throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select appmapping.appId, appmapping.comcode, appmapping.appname, appmapping.isdeleted, bpm_knol.name projectName ")
		   .append(" from appmapping appmapping, app, bpm_knol ")
		   .append(" where appmapping.appId = app.appId ")
		   .append("	and app.projectId = bpm_knol.id ")
		   .append("	and appmapping.comcode=?comCode ")
		   .append("	and appmapping.isdeleted=?isdeleted");
		
		IAppMapping findApp = (IAppMapping) Database.sql(IAppMapping.class, sql.toString());
		
		findApp.setComCode(this.getComCode());
		findApp.setIsDeleted(this.getIsDeleted());
		findApp.select();
		
		return findApp;
	}
	
	public void findProject(String appId) throws Exception {
		String projectName = null;
		
		this.setProjectName(projectName);
	}
	
	
}

