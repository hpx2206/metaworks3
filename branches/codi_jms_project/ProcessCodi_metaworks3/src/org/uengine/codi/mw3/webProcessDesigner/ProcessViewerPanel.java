package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class ProcessViewerPanel implements ContextAware {
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
	
	ProcessViewNaivgator	processViewNaivgator;
		public ProcessViewNaivgator getProcessViewNaivgator() {
			return processViewNaivgator;
		}
		public void setProcessViewNaivgator(ProcessViewNaivgator processViewNaivgator) {
			this.processViewNaivgator = processViewNaivgator;
		}
	ProcessViewPanel processViewPanel;
		public ProcessViewPanel getProcessViewPanel() {
			return processViewPanel;
		}
		public void setProcessViewPanel(ProcessViewPanel processViewPanel) {
			this.processViewPanel = processViewPanel;
		}
		
	public ProcessViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	public void findDefnitionView(){
		this.getMetaworksContext().setHow("find");
		processViewNaivgator = new ProcessViewNaivgator();
		processViewNaivgator.loadTree();
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.load();
	}
	public void loadDefnitionView(){
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
		
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
		processViewPanel.refresh(definitionId, definitionName);
		
		return new Object[]{processViewPanel, new Remover(new ModalWindow() , true) };
	}
}
