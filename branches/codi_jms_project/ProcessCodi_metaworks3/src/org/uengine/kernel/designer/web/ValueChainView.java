package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ValueChainViewerPanel;
import org.uengine.kernel.ValueChain;

public class ValueChainView extends ActivityView{
	transient ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
		}
		
	@Override
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showDefinitionMonitor() throws Exception{
		ValueChain valueChain = this.getValueChain();
		ValueChainViewerPanel valueChainViewerPanel = new ValueChainViewerPanel();
		if( valueChain != null && valueChain.getDefinitionId() != null ){
			valueChainViewerPanel.setDefinitionId(valueChain.getDefinitionId());
			valueChainViewerPanel.loadDefnitionView();
		}else{
			valueChainViewerPanel.findDefnitionView();
		}
		
		ModalWindow modalWindow = new ModalWindow(valueChainViewerPanel);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		return modalWindow;  
	 }
	
	@Override
	public Object showProperties() throws Exception{
		return null;
	}
}
