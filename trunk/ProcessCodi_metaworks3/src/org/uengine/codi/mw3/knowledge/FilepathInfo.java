package org.uengine.codi.mw3.knowledge;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.metaworks.component.SelectBox;
import org.metaworks.dao.DAOFactory;
import org.metaworks.dao.Database;
import org.metaworks.dao.KeyGeneratorDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;

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
		
	Date moddate;
		public Date getModdate() {
			return moddate;
		}
		public void setModdate(Date moddate) {
			this.moddate = moddate;
		}
	
	String comment;
		public String getComment() {
			return comment;
		}
		public void setComment(String comment) {
			this.comment = comment;
		}

	String distributor;
		public String getDistributor() {
			return distributor;
		}
		public void setDistributor(String distributor) {
			this.distributor = distributor;
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
	
	public SelectBox findReflectVersions() throws Exception{
		SelectBox sb = new SelectBox();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from filepathinfo where projectId = ?id and releaseVer = 0) a order by reflectVer;");
		
		IFilepathInfo findListing = (IFilepathInfo) Database.sql(IFilepathInfo.class, sql.toString());
		findListing.set("id", this.getProjectId());
		findListing.select();
		
		while(findListing.next()){
			sb.add(String.valueOf(findListing.getReflectVer()) + "  " + findListing.getComment() + " " + findListing.getDistributor(), String.valueOf(findListing.getId()));
		}
		
		if(findListing.size() == 0){
			sb.add("1" , "0");
		}
		
		return sb;
	}
	
	public SelectBox findReleaseVersions(String id) throws Exception{
		SelectBox sb = new SelectBox();
		StringBuffer sql = new StringBuffer();
		sql.append("select * from (select * from filepathinfo where projectId = ?id and releaseVer != 0) a order by releaseVer;");
		
		IFilepathInfo findListing = (IFilepathInfo) Database.sql(IFilepathInfo.class, sql.toString());
		findListing.set("id", id);
		findListing.select();
		
		while(findListing.next()){
			sb.add(String.valueOf(findListing.getReleaseVer()), String.valueOf(findListing.getId()));
		}
		
		return sb;
	}
	
	public int createNewId() throws Exception{
		Map options = new HashMap();
		options.put("useTableNameHeader", "false");
		options.put("onlySequenceTable", "true");
		
		KeyGeneratorDAO kg = DAOFactory.getInstance(TransactionContext.getThreadLocalInstance()).createKeyGenerator("filepathinfo", options);
		kg.select();
		kg.next();
		
		Number number = kg.getKeyNumber();
		
		return number.intValue();
	}
	
}
