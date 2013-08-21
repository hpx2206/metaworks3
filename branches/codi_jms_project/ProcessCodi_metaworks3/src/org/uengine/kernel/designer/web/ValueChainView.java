package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ValueChainViewerPanel;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.ValueChain;

public class ValueChainView extends ActivityView{
	transient ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
		}
		
	@AutowiredFromClient
	transient public Session session;
		
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
	public Popup showValueChainMonitor() throws Exception{
		ValueChain valueChain = this.getValueChain();
		TextContext name = new TextContext();
		name.setText(this.getLabel());
		valueChain.setName(name);
		String valueChainName = (valueChain.getName() != null && valueChain.getName().toString() != null) ? valueChain.getName().toString() : "valuechain"; 
		
		ValueChainViewerPanel valueChainViewerPanel = new ValueChainViewerPanel();
		valueChainViewerPanel.setOpenerValueChain(valueChain);
		valueChainViewerPanel.setOpenerValueChainViewId(this.getId());
		valueChainViewerPanel.setValueChainName(valueChainName);
		valueChainViewerPanel.session = session;
		valueChainViewerPanel.load();
		
		Popup modalWindow = new Popup(valueChainViewerPanel);
		modalWindow.setWidth(300);
		modalWindow.setHeight(350);
		
		return modalWindow;  
	 }
}
