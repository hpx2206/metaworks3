package org.uengine.codi.mw3.ide.dictionary;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;

public class DictionaryTopMenu {
	
	NewEntryPanel newEntryPanel;
		public NewEntryPanel getNewEntryPanel() {
			return newEntryPanel;
		}
		public void setNewEntryPanel(NewEntryPanel newEntryPanel) {
			this.newEntryPanel = newEntryPanel;
		}

	public void search() {
		
	}
	
	@ServiceMethod(callByContent = true,  target = ServiceMethodContext.TARGET_POPUP)
	public Object newEntry() throws Exception {
		
		Popup popup = new Popup();
		if(newEntryPanel == null)
			newEntryPanel = new NewEntryPanel();
		newEntryPanel.load();
		
		popup.setPanel(newEntryPanel);
		popup.setWidth(700);
		popup.setHeight(700);
		
		return popup;
	}
	
	public void export(){
		
	}

}
