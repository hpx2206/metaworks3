package org.uengine.codi.mw3.Collaboration;

import java.util.Date;

import org.metaworks.dao.Database;

public class FavoriteFile extends Database<IFavoriteFile> implements IFavoriteFile {
	
	String userId;
	String userName;
	String fileId;
	String fileName;
	String filePath;
	Date moddate;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	@Override
	public Object addFavoriteFile() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
