package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.kernel.SubProcessActivity;

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
	
	ProcessViewNavigator processViewNavigator;
		public ProcessViewNavigator getProcessViewNaivgator() {
			return processViewNavigator;
		}
		public void setProcessViewNaivgator(ProcessViewNavigator processViewNavigator) {
			this.processViewNavigator = processViewNavigator;
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
		processViewNavigator = new ProcessViewNavigator();
		processViewNavigator.loadTree();
		Workspace workspace = new Workspace();
		workspace.load();
		this.setWorkspace(workspace);
		
		processViewNavigator.load(workspace);
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.load();
	}
	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}
		
		
	public void loadDefnitionView(){
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
	}
	@ServiceMethod(callByContent=true)
	public void removeLink(){
		this.definitionId = null; 
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] saveLink(){
		
		return new Object[]{new Remover(new ModalWindow() , true) };
	}
	
	@AutowiredFromClient
	public ProcessViewWindow processViewWindow; 
	
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] openLink() throws Exception{
		
		String alias = processViewPanel.getAlias();
		String defId = processViewPanel.getDefId();
		if( processViewWindow != null ){
			HistoryItem historyItem = new HistoryItem();
			historyItem.setDefId(defId);
			historyItem.setAlias(alias);
			
			String[] defnitionArray = defId.replace('.','@').split("@");
			historyItem.setDefName(defnitionArray[0]);
			
			processViewWindow.loadByProcess(historyItem);
			return new Object[]{ new Remover(new ModalWindow(), true), new Refresh (processViewWindow)};
		}else{
			return null;
		}
	}
}
