package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IFavoriteFile extends IDAO {

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
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public IFavoriteFile addFavoriteFile() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public IFavoriteFile removeFavoriteFile() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] loadFile() throws Exception;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public IFavoriteFile loadByProcess() throws Exception;
}
