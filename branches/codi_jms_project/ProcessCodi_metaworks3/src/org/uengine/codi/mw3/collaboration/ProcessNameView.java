package org.uengine.codi.mw3.collaboration;

import java.util.Date;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewWindow;

public class ProcessNameView{
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String fileId;
		public String getFileId() {
			return fileId;
		}
		public void setFileId(String fileId) {
			this.fileId = fileId;
		}
		
	@AutowiredFromClient
	public ProcessViewWindow processViewWindow;
	
	@AutowiredFromClient
	transient public Session session;
	
	boolean favoriteAdded;
		public boolean isFavoriteAdded() {
			return favoriteAdded;
		}
		public void setFavoriteAdded(boolean favoriteAdded) {
			this.favoriteAdded = favoriteAdded;
		}
	
	CollaborationPerspectiveWindow collaborationPerspectiveWindow;
		public CollaborationPerspectiveWindow getCollaborationPerspectiveWindow() {
			return collaborationPerspectiveWindow;
		}
		public void setCollaborationPerspectiveWindow(
				CollaborationPerspectiveWindow collaborationPerspectiveWindow) {
			this.collaborationPerspectiveWindow = collaborationPerspectiveWindow;
		}
		
	public ProcessNameView() throws Exception{

	}
	
	String userId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	String fileName;
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)	
	public Object[] add() throws Exception{
		
		FavoriteFile favoriteFile = new FavoriteFile();
		
		favoriteFile.setFileId(processViewWindow.getDefId());
		favoriteFile.setFileName(processViewWindow.getAlias());
		favoriteFile.setFilePath(processViewWindow.getPath());
		favoriteFile.setModdate(new Date());
		favoriteFile.setUserId(session.getEmployee().getEmpCode());
		favoriteFile.setUserName(session.getEmployee().getEmpName());
		return new Object[]{new ToAppend(new FavoritePanel(),favoriteFile.addFavoriteFile()) };

	}
	public void load() throws Exception{
		FavoriteFile favoriteFile = new FavoriteFile();
		favoriteFile.setUserId(session.getEmployee().getEmpCode());
		favoriteFile.setFileId(this.getFileId());
		IFavoriteFile iFavoriteFile = favoriteFile.loadByProcess();
		
		if( iFavoriteFile.next() ){
			favoriteFile.copyFrom(iFavoriteFile);
			favoriteAdded = true;
		}else{
			favoriteAdded = false;
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_APPEND)	
	public Object[] remove() throws Exception{
		FavoriteFile favoriteFile = new FavoriteFile();
		favoriteFile.setUserId(session.getEmployee().getEmpCode());
		favoriteFile.setFileId(this.getFileId());
		
		favoriteFile.removeFavoriteFile();

		return new Object[]{new  Refresh(new FavoritePanel()) };

		
		
		
	}
	
	
	
}
