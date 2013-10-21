package org.uengine.codi.mw3.knowledge;

import org.metaworks.component.SelectBox;
import org.metaworks.dao.Database;

public class FilepathInfo extends Database<IFilepathInfo> implements IFilepathInfo{
	
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	int reflectVer;
		public int getReflectVer() {
			return reflectVer;
		}
		public void setReflectVer(int reflectVer) {
			this.reflectVer = reflectVer;
		}
	
	int releaseVer;
		public int getReleaseVer() {
			return releaseVer;
		}
		public void setReleaseVer(int releaseVer) {
			this.releaseVer = releaseVer;
		}
	
	String warPath;
		public String getWarPath() {
			return warPath;
		}
		public void setWarPath(String warPath) {
			this.warPath = warPath;
		}
	
	String sqlPath;
		public String getSqlPath() {
			return sqlPath;
		}
		public void setSqlPath(String sqlPath) {
			this.sqlPath = sqlPath;
		}
	String fileType;
		public String getFileType() {
			return fileType;
		}
		public void setFileType(String fileType) {
			this.fileType = fileType;
		}
	int id;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		
	public int findReflectVersion(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from filepathinfo where projectId = ?id) a order by reflectVer desc limit 1;");
		
		IFilepathInfo findListing = (IFilepathInfo) Database.sql(IFilepathInfo.class, sql.toString());
		findListing.set("id", id);
		findListing.select();
		
		if(findListing.next())
			return findListing.getReflectVer();
		else
			return 0;
	}
	
	public int findReleaseVersion(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from filepathinfo where projectId = ?id) a order by releaseVer desc limit 1;");
		
		IFilepathInfo findListing = (IFilepathInfo) Database.sql(IFilepathInfo.class, sql.toString());
		findListing.set("id", id);
		findListing.select();
		
		if(findListing.next())
			return findListing.getReleaseVer();
		else
			return 0;
	}
	
	public SelectBox findReleaseVersions(String id) throws Exception{
		SelectBox sb = new SelectBox();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from filepathinfo where projectId = ?id and reflectVer != 0) a order by reflectVer desc;");
		
		IFilepathInfo findListing = (IFilepathInfo) Database.sql(IFilepathInfo.class, sql.toString());
		findListing.set("id", id);
		findListing.select();
		
		while(findListing.next()){
			sb.add(String.valueOf(findListing.getReflectVer()), String.valueOf(findListing.getId()));
			System.out.println(String.valueOf(findListing.getId()));
		}
		
		return sb;
	}
	
}
