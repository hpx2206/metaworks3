package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

public class ProcessNavigatorPanel {
	
	String defId;
		public String getDefId() {
			return defId;
		}
		public void setDefId(String defId) {
			this.defId = defId;
		}
		
	String defName;
		public String getDefName() {
			return defName;
		}
		public void setDefName(String defName) {
			this.defName = defName;
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
		historyItem.setAlias(historyItem.alias);
		
		if(historyList == null) {
			historyList = new ArrayList<HistoryItem>();
		}
		historyList.add(historyItem);
		
		HistoryItem historyItem2 = new HistoryItem(); 
		historyItem2.setDefId("dd.wpd");
		historyItem2.setDefName("dd");
		historyItem2.setAlias("D:/codi/codebase\\codi\\root\\dd.wpd");
		historyList.add(historyItem2);
		
		HistoryItem historyItem3 = new HistoryItem(); 
		historyItem3.setDefId("qwe.wpd");
		historyItem3.setDefName("qwe");
		historyItem3.setAlias("D:/codi/codebase\\codi\\root\\qwe.wpd");
		historyList.add(historyItem3);
//		
//		// 아래부터는 Test 히스토리를 3개 둔다. 
//		definitionId = "qwe.wpd";
//		historyItem.setDefId(definitionId);
//		historyItem.setDefName("qwe");
//		historyItem.setAlias("D:/codi/codebase\\codi\\root\\qwe.wpd");
//		historyList.add(historyItem);
//		
//
//		definitionId = "dd.wpd";
//		historyItem.setDefId("dd.wpd");
//		historyItem.setDefName("dd");
//		historyItem.setAlias("D:/codi/codebase\\codi\\root\\dd.wpd");
//		historyList.add(historyItem);
		
		
	}
	public void add(HistoryItem historyItem) {
		historyList.add(historyItem);
	}
	
	@AutowiredFromClient
	public ProcessViewPanel processViewPanel;
	
	
	// 이 부분에서 히스토리를 클릭했을 때 processViewPanel에서 미리보기 되는 작업까지 같이 해야한다.
	@ServiceMethod(callByContent = true , target = ServiceMethodContext.TARGET_POPUP)
	public Object[] changeViewPanel() throws Exception {
		
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		
		processViewPanel.load();
		
//		// 강제 테스트
//		processViewPanel.setDefId(this.defId);
//		processViewPanel.setAlias(alias);
//		
//		processViewPanel.load();
	
		return new Object[] { new Refresh(processViewPanel) };

	}

}
