package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;

public class ProcessMap extends Database<IProcessMap> implements IProcessMap {
	
	public ProcessMap(){
		setIconFile(new MetaworksFile());		
		setIconColor(new ProcessMapColor("배경선택"));
	}
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}

	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

	MetaworksFile iconFile;	
		public MetaworksFile getIconFile() {
			return iconFile;
		}
		public void setIconFile(MetaworksFile iconFile) {
			this.iconFile = iconFile;
		}

	ProcessMapColor iconColor;	
		public ProcessMapColor getIconColor() {
			return iconColor;
		}
		public void setIconColor(ProcessMapColor iconColor) {
			this.iconColor = iconColor;
		}
		
	public Object[] create() throws Exception {
		if(getIconFile().getFileTransfer() != null && !getIconFile().getFileTransfer().getFilename().isEmpty())
			getIconFile().upload();
		else
			getIconFile().setUploadedPath("");
		
		createDatabaseMe();
		flushDatabaseMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load();
		
		return new Object[]{processMapList, new Remover(new Popup(this))};
	}
	
	public Object[] save() throws Exception {
		if(getIconFile().getDeletedPath() != null)
			getIconFile().remove();
		
		if(getIconFile().getFileTransfer() != null && !getIconFile().getFileTransfer().getFilename().isEmpty())
			getIconFile().upload();
		else
			getIconFile().setUploadedPath("");
		
		syncToDatabaseMe();
		flushDatabaseMe();
		
		ProcessMapList processMapList = new ProcessMapList();
		processMapList.load();
		
		return new Object[]{processMapList, new Remover(new Popup(this))};
	}

	public Popup modify() throws Exception {
		getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		Popup popup = new Popup(560, 430);
		popup.setPanel(this);
		
		return popup;		
	}

	public Remover close() throws Exception {		
		return new Remover(new Popup(this));
	}
		
	public boolean confirmExist() {
		try{
			databaseMe();
		}catch(Exception e){
			return true;
		}
		
		return false;
	}
	
	public static IProcessMap loadList() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT *");
		sb.append("  FROM processMap");
		sb.append(" ORDER BY no");

		IProcessMap processMap = (IProcessMap)sql(ProcessMap.class, sb.toString()); 
		processMap.select();
		
		return processMap;
	}
	
}

