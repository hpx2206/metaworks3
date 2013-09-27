package org.uengine.codi.mw3.processexplorer;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewWindow;

public class ProcessNameView implements ContextAware{
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
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
		
	public ProcessNameView() throws Exception{

	}
	
	
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
		
		FavoritePanel favoritePanel = new FavoritePanel();
		favoritePanel.session = session;
		favoritePanel.load();
		
		return new Object[]{new Refresh(favoritePanel)};
	}
	
}
