package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.processexplorer.ProcessNameView;
import org.uengine.util.UEngineUtil;

public class ProcessNavigatorPanel {
	
	int index;
		public int getIndex() {
			return index;
		}
		public void setIndex(int index) {
			this.index = index;
		}

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
	ProcessNameView processNameView;
		public ProcessNameView getProcessNameView() {
			return processNameView;
		}
		public void setProcessNameView(ProcessNameView processNameView) {
			this.processNameView = processNameView;
		}	
				
		
	public void load() {
		if(UEngineUtil.isNotEmpty(alias)  && UEngineUtil.isNotEmpty(defId)){
			String definitionId = this.defId;
			String[] defnitionArray = definitionId.replace('.','@').split("@");
			
			HistoryItem historyItem = new HistoryItem(); 
			historyItem.setDefId(definitionId);
			historyItem.setDefName(defnitionArray[0]);
			historyItem.setAlias(this.alias);
			
			if(historyList == null) {
				historyList = new ArrayList<HistoryItem>();
			}
			historyList.add(historyItem);
		}
	}
	public void add(HistoryItem historyItem) {
		historyList.add(historyItem);
	}
	
	// 이 부분에서 히스토리를 클릭했을 때 processViewPanel에서 미리보기 되는 작업까지 같이 해야한다.
	@ServiceMethod(callByContent = true , target = ServiceMethodContext.TARGET_POPUP)
	public Object[] changeViewPanel() throws Exception {
		
		ProcessViewPanel processViewPanel = new ProcessViewPanel();
		
		processViewPanel.setDefId(defId);
		processViewPanel.setAlias(alias);
		processViewPanel.load();
		
		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		
		processAttributePanel.setDocumentation(null);
		processAttributePanel.setDefId(defId);
		if( processViewPanel.processViewer != null ){
			processAttributePanel.load(processViewPanel.processViewer.getProcessDesignerContainer());
		}
		int size = historyList.size();
		if(size > 0 && (size > index)) {
			for(int i = size-1 ;  i >= index+1; i--) {
				historyList.remove(i);
			}
		}
	
		return new Object[] { new Refresh(processViewPanel), new Refresh(processAttributePanel), new Refresh(this) };

	}

}
