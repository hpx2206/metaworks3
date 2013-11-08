package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.ValueChain;

public class ValueChainViewerPanel implements ContextAware{
	@AutowiredFromClient
	public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String valueChainName;
		public String getValueChainName() {
			return valueChainName;
		}
		public void setValueChainName(String valueChainName) {
			this.valueChainName = valueChainName;
		}
	ValueChain openerValueChain;
		public ValueChain getOpenerValueChain() {
			return openerValueChain;
		}
		public void setOpenerValueChain(ValueChain openerValueChain) {
			this.openerValueChain = openerValueChain;
		}
	String openerValueChainViewId;
		public String getOpenerValueChainViewId() {
			return openerValueChainViewId;
		}
		public void setOpenerValueChainViewId(String openerValueChainViewId) {
			this.openerValueChainViewId = openerValueChainViewId;
		}
	ValueChainNavigatorPanel valueChainNavigatorPanel;
		public ValueChainNavigatorPanel getValueChainNavigatorPanel() {
			return valueChainNavigatorPanel;
		}
		public void setValueChainNavigatorPanel(
				ValueChainNavigatorPanel valueChainNavigatorPanel) {
			this.valueChainNavigatorPanel = valueChainNavigatorPanel;
		}
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}	
	public ValueChainViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	public void load(){
		valueChainNavigatorPanel = new ValueChainNavigatorPanel();
		valueChainNavigatorPanel.setValueChainName(valueChainName);
		valueChainNavigatorPanel.setValueChain(openerValueChain);
		valueChainNavigatorPanel.load();
	}
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public void removeLink(){
	}
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveLink(){
		ValueChain valueChain = this.getOpenerValueChain();
		MajorProcessDefinitionNode node = (MajorProcessDefinitionNode) valueChainNavigatorPanel.getMajorProcessDefinitionTree().getNode();
		node.removeNullChild(node.getChild());
		valueChain.setMajorProcessDefinitionNode(node);
		TextContext name = new TextContext();
		name.setText(node.getName());
		valueChain.setName(name);
		
		ApplyProperties applyProperties = new ApplyProperties(this.getOpenerValueChainViewId(), valueChain);
		return new Object[]{applyProperties, new Remover(new Popup() , true) };
	}
	
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] openLink(){
		//processViewPanel.refresh(definitionId, definitionName);
		//return new Object[]{processViewPanel, new Remover(new ModalWindow() , true) };
		return null;
	}	
}
