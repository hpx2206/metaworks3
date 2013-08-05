package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.kernel.Activity;
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
	
	Activity openerActivity;
		public Activity getOpenerActivity() {
			return openerActivity;
		}
		public void setOpenerActivity(Activity openerActivity) {
			this.openerActivity = openerActivity;
		}
	String openerActivityViewId;
		public String getOpenerActivityViewId() {
			return openerActivityViewId;
		}
		public void setOpenerActivityViewId(String openerActivityViewId) {
			this.openerActivityViewId = openerActivityViewId;
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
	Workspace workspace;
		public Workspace getWorkspace() {
			return workspace;
		}
		public void setWorkspace(Workspace workspace) {
			this.workspace = workspace;
		}	
		
		@AutowiredFromClient
	public ProcessViewWindow processViewWindow; 

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

		
	public void loadDefnitionView(){
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType("definitionView");
		processViewPanel.load();
	}
		
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] removeLink(){
//			this.definitionId = null;
//			this.alias = null;
			Activity activity = this.getOpenerActivity();
			((SubProcessActivity) activity).setDefinitionId(null);
			((SubProcessActivity) activity).setAlias(null);
			ApplyProperties applyProperties = new ApplyProperties(this.getOpenerActivityViewId(), activity);
			applyProperties.setViewType("definitionView");
			return new Object[]{applyProperties, new Remover(new ModalWindow() , true) };
		}
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveLink(){
		String defId = processViewPanel.getDefId();
		String alias = processViewPanel.getAlias();
		Activity activity = this.getOpenerActivity();
		if( activity instanceof SubProcessActivity){
			((SubProcessActivity) activity).setDefinitionId(defId);
			((SubProcessActivity) activity).setAlias(alias);
		}
		
		ApplyProperties applyProperties = new ApplyProperties(this.getOpenerActivityViewId(), activity);
		applyProperties.setViewType("definitionView");
		return new Object[]{applyProperties, new Remover(new ModalWindow() , true) };
	}
	
	
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
