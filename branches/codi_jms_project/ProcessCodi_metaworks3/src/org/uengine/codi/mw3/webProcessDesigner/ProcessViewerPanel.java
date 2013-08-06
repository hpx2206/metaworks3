package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudInstanceWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.InstanceViewThreadPanel;
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
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
		@AutowiredFromClient
	public ProcessViewWindow processViewWindow; 

		public ProcessViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	public void findDefinitionView(){
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

		
	public void loadDefinitionView(){
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType(this.getViewType());
		processViewPanel.load();
	}
	
	public void loadDefinitionEditor(){
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setViewType(this.getViewType());
		processViewPanel.load();
	}
		
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] removeLink(){
			Activity activity = this.getOpenerActivity();
			if( activity instanceof SubProcessActivity){
				((SubProcessActivity) activity).setDefinitionId(null);
				((SubProcessActivity) activity).setAlias(null);
			}
			ApplyProperties applyProperties = new ApplyProperties(this.getOpenerActivityViewId(), activity);
			applyProperties.setViewType(this.getViewType());
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
		applyProperties.setViewType(this.getViewType());
		return new Object[]{applyProperties, new Remover(new ModalWindow() , true) };
	}
	
	
	@ServiceMethod(callByContent=true, target = ServiceMethodContext.TARGET_APPEND)
	public Object[] openLink() throws Exception{
		String alias = processViewPanel.getAlias();
		String defId = processViewPanel.getDefId();
		if( "definitionView".equals(this.getViewType() )){
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
		}else{
			ResourceNode resourceNode = new ResourceNode();
			resourceNode.setPath(alias);
			resourceNode.setType(ResourceNode.TYPE_FILE_PROCESS);
			resourceNode.setId(defId);
			resourceNode.setName(defId);
			Editor editor = new ProcessEditor(resourceNode);
			try {
				editor.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			InstanceViewThreadPanel instanceViewThreadPanel = new InstanceViewThreadPanel();
			if(  editor instanceof ProcessEditor ){
				if( ((ProcessEditor)editor).getProcessDesignerInstanceId() != null ){
					instanceViewThreadPanel.session = session;
					instanceViewThreadPanel.setInstanceId(((ProcessEditor)editor).getProcessDesignerInstanceId());
					instanceViewThreadPanel.load();
				}
			}
			CloudInstanceWindow cloudInstanceWindow = new CloudInstanceWindow();
			cloudInstanceWindow.setPanel(instanceViewThreadPanel);
			*/
			return new Object[]{new ToAppend(new CloudWindow("editor"), editor) , new Remover(new ModalWindow(), true) };
//			return new Object[]{new ToAppend(new CloudWindow("editor"), editor) , new Refresh(cloudInstanceWindow, true) };
		}
	}
}
