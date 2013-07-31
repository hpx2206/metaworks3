package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;

public class ProcessViewPanel {
		
	String defId;
		public String getDefId() {
			return defId;
		}
	
		public void setDefId(String defId) {
			this.defId = defId;
		}
	ProcessViewer processViewer;
		public ProcessViewer getDefinitionMonitor() {
			return processViewer;
		}
		public void setDefinitionMonitor(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}

	@Autowired
	ProcessNavigatorPanel processNavigatorPanel;
	
	// 자기 defId를 가지고 자신을 refresh 한다.
	public Object[] refresh(String defId, String defName) {
		this.setDefId(defId);
		processNavigatorPanel.add(defId, defName);
		
		ProcessAttributePanel processAttributePanel = new ProcessAttributePanel();
		processAttributePanel.setDefId(defId);
		processAttributePanel.load();
		
		return new Object[]{new Refresh(this) ,new Refresh(processNavigatorPanel) ,new Refresh(processAttributePanel)};
		
	}
	
	public void load(){
		processViewer = new ProcessViewer();
		processViewer.setDefinitionId(defId);
		processViewer.load();
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow samplePopup(){
		HistoryItem historyItem = new HistoryItem();
		historyItem.getMetaworksContext().setWhen("edit");
		ModalWindow window = new ModalWindow();
		window.setPanel(historyItem);
		
		return window;
	}
	
	
}
