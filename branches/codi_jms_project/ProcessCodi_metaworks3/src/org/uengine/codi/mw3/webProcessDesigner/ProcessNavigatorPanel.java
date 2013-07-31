package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

public class ProcessNavigatorPanel {
	
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
	ArrayList<HistoryItem> historyList;
		public ArrayList<HistoryItem> getHistoryList() {
			return historyList;
		}
		public void setHistoryList(ArrayList<HistoryItem> historyList) {
			this.historyList = historyList;
		}
		
	public void load() {
		String definitionId = this.defId;
		String[] defnitionArray = definitionId.replace('.','@').split("@");
		
		HistoryItem historyItem = new HistoryItem(); 
		historyItem.setDefId(definitionId);
		historyItem.setDefName(defnitionArray[0]);
		historyItem.setAlias(this.alias);
		
		historyList = new ArrayList<HistoryItem>();
		historyList.add(historyItem);
		
	}
	public void add(HistoryItem historyItem) {
		historyList.add(historyItem);
	}

}
