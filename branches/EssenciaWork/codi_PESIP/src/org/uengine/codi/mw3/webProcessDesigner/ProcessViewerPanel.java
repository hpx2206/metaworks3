package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.EventActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.SubProcessActivity;

public class ProcessViewerPanel implements ContextAware {
	@AutowiredFromClient
	public Session session;
	
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
		workspace.load(session);
		this.setWorkspace(workspace);
		
		processViewNavigator.load(workspace);
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.load();
	}
	
	public void findValuechainView(){
		this.getMetaworksContext().setHow("valueChainFind");
		processViewNavigator = new ProcessViewNavigator();
		processViewNavigator.loadTree();
		Workspace workspace = new Workspace();
		workspace.load(session);
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
	
	public void loadValuechainView(){
		this.getMetaworksContext().setHow("valueChainLoad");
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

			}else if(activity instanceof EventActivity){
				((EventActivity) activity).setDefinitionId(null);
				((EventActivity) activity).setAlias(null);
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
		}else if(activity instanceof EventActivity){
			((EventActivity) activity).setDefinitionId(defId);
			((EventActivity) activity).setAlias(alias);
		}
		
		ApplyProperties applyProperties = new ApplyProperties(this.getOpenerActivityViewId(), activity);
		applyProperties.setViewType(this.getViewType());
		return new Object[]{applyProperties, new Remover(new ModalWindow() , true) };
	}
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveValueChain() throws Exception{
		String defId = processViewPanel.getDefId();
		String alias = processViewPanel.getAlias();
		if( defId != null && alias != null ){
			Object clipboard = session.getClipboard();
			if(clipboard instanceof MajorProcessDefinitionNode){
				MajorProcessDefinitionNode targetNode = (MajorProcessDefinitionNode)clipboard;
				targetNode.setExpanded(true);
				if(targetNode.getChild() != null){
					// 중복 프로세스 체크
					for(int i=0; i < targetNode.getChild().size(); i++){
						TreeNode childNode = (TreeNode) targetNode.getChild().get(i);
						if( defId.equals(childNode.getName()) ){
							throw new MetaworksException("same process remain!!");
						}
					}
				}
				
				MinorProcessDefinitionNode node = new MinorProcessDefinitionNode();
				node.setName(defId);
				node.setId(targetNode.getId() + File.separatorChar + node.getName());
				node.setPath(alias);
				node.setParentNode(targetNode);
				node.setType(TreeNode.TYPE_FILE_PROCESS);
				node.setFolder(false);
				
				return new Object[]{new Remover(new ModalWindow() , true), new ToAppend(targetNode, node)};
			}else{
				throw new MetaworksException("save error");
			}
		}
		
		return new Object[]{new Remover(new ModalWindow() , true)};
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
			String codebase = GlobalContext.getPropertyString("codebase", "codebase");
			resourceNode.setAlias(alias);
			resourceNode.setPath(codebase + File.separatorChar + alias);
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
			 * TODO 인스턴스 연결
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
//			return new Object[]{new ToAppend(new CloudWindow("editor"), editor) , new Remover(new ModalWindow(), true) , new Refresh(cloudInstanceWindow, true) };
		}
	}
	
	@ServiceMethod(target = ServiceMethodContext.TARGET_APPEND)
	public Object closeModalPopup() throws Exception{
		return new Remover(new ModalWindow() , true);
	}
}
