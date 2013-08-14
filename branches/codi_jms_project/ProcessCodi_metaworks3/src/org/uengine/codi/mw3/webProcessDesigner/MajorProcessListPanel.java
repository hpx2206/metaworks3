package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;

public class MajorProcessListPanel {
	
	@AutowiredFromClient
	public ValueChainNavigatorPanel valueChainNavigatorPanel;
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		
	ArrayList<MajorProcessItem> majorProcessItems;
		public ArrayList<MajorProcessItem> getMajorProcessItems() {
			return majorProcessItems;
		}
		public void setMajorProcessItems(ArrayList<MajorProcessItem> majorProcessItems) {
			this.majorProcessItems = majorProcessItems;
		}
		
	public void load(){
		//TODO : value chain view panel load
	}
	
}
