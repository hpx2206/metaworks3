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
		
	ArrayList<HistoryItem> historyList;
		public ArrayList<HistoryItem> getHistoryList() {
			return historyList;
		}
		public void setHistoryList(ArrayList<HistoryItem> historyList) {
			this.historyList = historyList;
		}
		
	public void load() {
		HistoryItem historyItem = new HistoryItem(); 
		historyItem.setDefId("aa");
		historyItem.setDefName("나나나");
		
		historyList = new ArrayList<HistoryItem>();
		historyList.add(historyItem);
		
	}
	public void add(String defId, String defName) {
		HistoryItem historyItem = new HistoryItem();
		historyItem.setDefId(defId);
		historyItem.setDefName(defName);
		historyList.add(historyItem);
	}

}
