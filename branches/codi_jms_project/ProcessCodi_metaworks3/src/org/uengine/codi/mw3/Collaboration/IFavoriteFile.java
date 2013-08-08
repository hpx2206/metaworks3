package org.uengine.codi.mw3.Collaboration;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.dao.IDAO;

public interface IFavoriteFile extends IDAO {

	
	
	@Id
	public String getUserId();
	public void setUserId(String userId);
	public String getUserName();
	public void setUserName(String userName);
	public String getFileId();
	public void setFileId(String fileId);
	public String getFileName();
	public void setFileName(String fileName);
	public String getFilePath();
	public void setFilePath(String filePath);
	public Date getModdate();
	public void setModdate(Date moddate);
	
	
	public Object addFavoriteFile();
}
