package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ValueChainViewerPanel implements ContextAware{

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	String definitionId;
		public String getDefinitionId() {
			return definitionId;
		}
		public void setDefinitionId(String definitionId) {
			this.definitionId = definitionId;
		}
	String definitionName;
		public String getDefinitionName() {
			return definitionName;
		}
		public void setDefinitionName(String definitionName) {
			this.definitionName = definitionName;
		}
	String alias;
		public String getAlias() {
			return alias;
		}
		public void setAlias(String alias) {
			this.alias = alias;
		}
		
	ValueChainViewNavigator	valueChainViewNaivgator;
		public ValueChainViewNavigator getValueChainViewNaivgator() {
			return valueChainViewNaivgator;
		}
		public void setValueChainViewNaivgator(ValueChainViewNavigator valueChainViewNaivgator) {
			this.valueChainViewNaivgator = valueChainViewNaivgator;
		}
	ValueChainViewPanel valueChainViewPanel;
		public ValueChainViewPanel getValueChainViewPanel() {
			return valueChainViewPanel;
		}
		public void setValueChainViewPanel(ValueChainViewPanel valueChainViewPanel) {
			this.valueChainViewPanel = valueChainViewPanel;
		}
		
	public ValueChainViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	public void findDefnitionView(){
		this.getMetaworksContext().setHow("find");
		valueChainViewNaivgator = new ValueChainViewNavigator();
		valueChainViewNaivgator.loadTree();
		
		valueChainViewPanel = new ValueChainViewPanel();
		valueChainViewPanel.load();
	}
	public void loadDefnitionView(){
		this.getMetaworksContext().setHow("load");
		valueChainViewPanel = new ValueChainViewPanel();
		valueChainViewPanel.setDefId(definitionId);
		valueChainViewPanel.setAlias(alias);
		valueChainViewPanel.setViewType("definitionView");
		valueChainViewPanel.load();
		
	}
	@ServiceMethod()
	public void removeLink(){
		this.definitionId = null; 
	}
	
	@ServiceMethod()
	public Object[] saveLink(){
		// TODO 
		return null;
	}
	
	@ServiceMethod()
	public Object[] openLink(){
		//processViewPanel.refresh(definitionId, definitionName);
		
		//return new Object[]{processViewPanel, new Remover(new ModalWindow() , true) };
		return null;
	}
	
}
