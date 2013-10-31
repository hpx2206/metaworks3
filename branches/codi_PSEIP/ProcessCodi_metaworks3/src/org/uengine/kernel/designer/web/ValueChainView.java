package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ValueChainViewerPanel;
import org.uengine.kernel.ValueChain;
import org.uengine.util.UEngineUtil;

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
		
		String valueChainName = UEngineUtil.isNotEmpty(valueChain.getName() != null ? valueChain.getName().getText() : "") ? valueChain.getName().getText() : "valuechain"; 
		
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
