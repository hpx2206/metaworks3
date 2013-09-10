package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessNavigatorPanel;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewWindow;

public class FavoriteFile extends Database<IFavoriteFile> implements IFavoriteFile {
	
	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	String fileId;
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		
	String fileName;
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
	String filePath;
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		
	Date moddate;
		public Date getModdate() {
			return moddate;
		}
		public void setModdate(Date moddate) {
			this.moddate = moddate;
		}
		
	@AutowiredFromClient
	public Session session;
	
	@AutowiredFromClient
	public ProcessViewWindow processViewWindow;
	
	ProcessNameView processNameView;
		public ProcessNameView getProcessNameView() {
			return processNameView;
		}
		public void setProcessNameView(ProcessNameView processNameView) {
			this.processNameView = processNameView;
		}
	
	@Override
	@ServiceMethod(callByContent=true)
	public IFavoriteFile addFavoriteFile() throws Exception {
		// TODO Auto-generated method stub
		IFavoriteFile iFavoriteFile = this.loadByProcess();
		if(iFavoriteFile != null && iFavoriteFile.size() > 0)
			return null;
		else
			return createDatabaseMe();
		
	}
	
	
	//이미 있는것은 별표시
	@Override
	public IFavoriteFile loadByProcess() throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from favoritefile");
		sb.append(" where userId=?userId");
		sb.append(" and fileId=?fileId");
		
		IFavoriteFile favoriteFile = (IFavoriteFile) sql(FavoriteFile.class, sb.toString());
		
		favoriteFile.set("userId", userId);
		favoriteFile.set("fileId", fileId);
		
		favoriteFile.select();
		
		return favoriteFile;
	}
	public static IFavoriteFile loadList(Session session) throws Exception {
		// TODO Auto-generated method stub
		
//		DAOUtil daoutil = new DAOUtil();
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from favoritefile");
		
		IFavoriteFile favoriteFile = (IFavoriteFile) sql(FavoriteFile.class, sb.toString());
		favoriteFile.select();
		
		return favoriteFile;
	}
	@Override
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)
	public IFavoriteFile removeFavoriteFile() throws Exception {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append("delete");
		sb.append(" from favoritefile");
		sb.append(" where userId=?userId");
		sb.append(" and fileId=?fileId");
//		sb.append("update favoritefile");
//		sb.append(" set ")
		
		IFavoriteFile favoriteFile = (IFavoriteFile) sql(FavoriteFile.class, sb.toString());
		
		favoriteFile.set("userId", this.getUserId());
		favoriteFile.set("fileId", this.getFileId());
		
		
		favoriteFile.update();
		

//		return new Object[]{new Refresh(new FavoritePanel())};
		
		//		deleteDatabaseMe();
		return favoriteFile;
		
	}
	ProcessNavigatorPanel processNavigatorPanel;
		public ProcessNavigatorPanel getProcessNavigatorPanel() {
			return processNavigatorPanel;
		}
		public void setProcessNavigatorPanel(ProcessNavigatorPanel processNavigatorPanel) {
			this.processNavigatorPanel = processNavigatorPanel;
		}
	
	@Override
	public Object[] loadFile() throws Exception {

		String title = this.getFileName();
		processViewWindow = new ProcessViewWindow();
		Object[] returnObject = processViewWindow.loadFile(session,getFileId(),getFileName(),getFilePath(),title);
		
		return returnObject;
	}
}
