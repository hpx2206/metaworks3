package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.kernel.ValueChain;

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
		
	ValueChainNavigatorPanel valueChainNavigatorPanel;
		public ValueChainNavigatorPanel getValueChainNavigatorPanel() {
			return valueChainNavigatorPanel;
		}
		public void setValueChainNavigatorPanel(
				ValueChainNavigatorPanel valueChainNavigatorPanel) {
			this.valueChainNavigatorPanel = valueChainNavigatorPanel;
		}
		
	ValueChain valueChain;
		public ValueChain getValueChain() {
			return valueChain;
		}
		public void setValueChain(ValueChain valueChain) {
			this.valueChain = valueChain;
		}
	
	MajorProcessListPanel majorProcessListPanel;
		public MajorProcessListPanel getMajorProcessListPanel() {
			return majorProcessListPanel;
		}
		public void setMajorProcessListPanel(MajorProcessListPanel majorProcessListPanel) {
			this.majorProcessListPanel = majorProcessListPanel;
		}
		
	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}
		
	public ValueChainViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	public void findDefnitionView(){
		this.getMetaworksContext().setHow("find");
		valueChainNavigatorPanel = new ValueChainNavigatorPanel();
		Workspace workspace = new Workspace();
		workspace.load();
		this.setWorkspace(workspace);
		
		valueChainNavigatorPanel.load(workspace);
		
		majorProcessListPanel = new MajorProcessListPanel();
		majorProcessListPanel.setDefId(definitionId);
		majorProcessListPanel.setAlias(alias);
		majorProcessListPanel.setViewType("definitionView");
		majorProcessListPanel.setValueChain(valueChain);
		majorProcessListPanel.load();
	}
	public void loadDefnitionView(){
		this.getMetaworksContext().setHow("load");
		majorProcessListPanel = new MajorProcessListPanel();
		majorProcessListPanel.setDefId(definitionId);
		majorProcessListPanel.setAlias(alias);
		majorProcessListPanel.setViewType("definitionView");
		majorProcessListPanel.setValueChain(valueChain);
		majorProcessListPanel.load();
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
