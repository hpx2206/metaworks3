package org.uengine.codi.mw3.webProcessDesigner;

import java.io.File;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.MetaworksException;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.ToEvent;
import org.metaworks.ToOpener;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.TreeNode;
import org.metaworks.metadata.MetadataBundle;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.DefaultProject;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.StartConnectorEventActivity;
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
	String viewType;
		public String getViewType() {
			return viewType;
		}
		public void setViewType(String viewType) {
			this.viewType = viewType;
		}
	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
	boolean useClassLoader;
		public boolean isUseClassLoader() {
			return useClassLoader;
		}
		public void setUseClassLoader(boolean useClassLoader) {
			this.useClassLoader = useClassLoader;
		}	
	String openerPopupId;
		public String getOpenerPopupId() {
			return openerPopupId;
		}
		public void setOpenerPopupId(String openerPopupId) {
			this.openerPopupId = openerPopupId;
		}
	@AutowiredFromClient
	public ProcessViewWindow processViewWindow; 

	@AutowiredFromClient
	public Project project;
	
	public ProcessViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	
	@ServiceMethod(callByContent=true)
	public void findDefinitionView() throws Exception{
		this.getMetaworksContext().setHow("find");
		processViewNavigator = new ProcessViewNavigator();
		processViewNavigator.loadTree();
		if( project == null ){
			project = new DefaultProject();
		}
		
		processViewNavigator.load(project);
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setUseClassLoader(useClassLoader);
		processViewPanel.load();
	}
	
	public void findValuechainView() throws Exception{
		this.getMetaworksContext().setHow("valueChainFind");
		processViewNavigator = new ProcessViewNavigator();
		processViewNavigator.loadTree();
		
		processViewNavigator.load(project);
		
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setUseClassLoader(useClassLoader);
		processViewPanel.load();
	}
	@ServiceMethod(callByContent=true)
	public void loadDefinitionView() throws Exception{
		this.getMetaworksContext().setHow("load");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setProjectId(projectId);
		processViewPanel.setViewType(this.getViewType());
		processViewPanel.setUseClassLoader(useClassLoader);
		processViewPanel.load();
	}
	
	public void loadValuechainView() throws Exception{
		this.getMetaworksContext().setHow("valueChainLoad");
		processViewPanel = new ProcessViewPanel();
		processViewPanel.setDefId(definitionId);
		processViewPanel.setAlias(alias);
		processViewPanel.setProjectId(projectId);
		processViewPanel.setViewType(this.getViewType());
		processViewPanel.setUseClassLoader(useClassLoader);
		processViewPanel.load();
	}
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_APPEND)
	public Object[] saveLink() throws Exception{
		String defId = processViewPanel.getDefId();
		String alias = processViewPanel.getAlias();
		Activity activity = this.getOpenerActivity();
		if( activity != null ){
			if( activity instanceof SubProcessActivity){
				((SubProcessActivity) activity).setDefinitionId(defId);
				((SubProcessActivity) activity).setAlias(defId);
				
				((SubProcessActivity) activity).setSubProcessContext(null);
				((SubProcessActivity) activity).drawInit();
				((SubProcessActivity) activity).getProcessSelectPanel().setDefinitionId(defId);
				
			}else if(activity instanceof StartConnectorEventActivity){
				((StartConnectorEventActivity) activity).setDefinitionId(defId);
				((StartConnectorEventActivity) activity).setAlias(alias);
			}
			ModalWindow popup = new ModalWindow();
			popup.setId(this.getOpenerPopupId());
			return new Object[]{ new Refresh(activity), new Remover(popup , true)};
		}else{
			// TARGET_OPENER 에서  ToOpener 를 필히 구현해 주어야함.
			ModalWindow popup = new ModalWindow();
			popup.setId(this.getOpenerPopupId());
			return new Object[]{ new ToOpener(defId), new ToEvent(ServiceMethodContext.TARGET_OPENER, EventContext.EVENT_CHANGE), new Remover(popup , true)};
		}
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
					for(int i=0; i < targetNode.getChild().size(); i++){
						TreeNode childNode = (TreeNode) targetNode.getChild().get(i);
						if( childNode == null ){
							continue;
						}
						if( defId.equals(childNode.getName()) ){
							throw new MetaworksException("same process remain!!");
						}
					}
				}
				
				MinorProcessDefinitionNode node = new MinorProcessDefinitionNode();
				node.setName(defId);
				node.setId(targetNode.getId() + File.separatorChar + node.getName());
				node.setPath(defId);
				node.setParentNode(targetNode);
				node.setType(TreeNode.TYPE_FILE_PROCESS);
				node.setFolder(false);
				
				return new Object[]{new ToAppend(targetNode, node), new Remover(new ModalWindow() , true)};
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
			
			ProcessNode processNode = new ProcessNode();
			processNode.setAlias(alias);
			processNode.setType(ResourceNode.TYPE_FILE_PROCESS);
			processNode.setId(alias);
			processNode.setName(defId);
			
			// TODO Path 를 정확히 주어야 새로 열린 에디터에서 수정 및 저장이 된다.
			if( projectId == null ){
				projectId = MetadataBundle.getProjectId();
			}
			String path = project.getPath() + File.separatorChar + alias;
			processNode.setProjectId(projectId);
			processNode.setPath(path);
			
			ProcessEditor editor = new ProcessEditor(processNode);
			try {
				editor.getProcessDesigner().load();
//				editor.load();
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
