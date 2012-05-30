package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.dao.Database;
import org.metaworks.website.MetaworksFile;

public class ProcessMap extends Database<IProcessMap> implements IProcessMap {
	
	@AutowiredFromClient
	public ProcessMapPanel processMapPanel;
	
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
		System.out.println(getIconColor().getValue());
		getIconFile().setUploadedPath("");
		
		createDatabaseMe();
		flushDatabaseMe();
		
		return new Object[]{new ProcessMapList(), new Remover(new Popup(this))};
	}
	
	public Remover close() throws Exception {		
		return new Remover(new Popup(this));
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

