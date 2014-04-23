package org.uengine.codi.mw3.marketplace;

import org.metaworks.ContextAware;
import org.metaworks.EventContext;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.SelectBox;
import org.uengine.codi.mw3.knowledge.IProjectNode;
import org.uengine.codi.mw3.knowledge.ProjectNode;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessSelectPanel;

public class AppTypePanel implements ContextAware{
	
	@AutowiredFromClient
	transient public Session session;
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String selectedAppType;
		public String getSelectedAppType() {
			return selectedAppType;
		}
		public void setSelectedAppType(String selectedAppType) {
			this.selectedAppType = selectedAppType;
		}
	String selectedAppDetail;	
		public String getSelectedAppDetail() {
			return selectedAppDetail;
		}
		public void setSelectedAppDetail(String selectedAppDetail) {
			this.selectedAppDetail = selectedAppDetail;
		}

	String projectId;
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}

	SelectBox appType;
		public SelectBox getAppType() {
			return appType;
		}
		public void setAppType(SelectBox appType) {
			this.appType = appType;
		}
	
	Object appTypeDetail;
		public Object getAppTypeDetail() {
			return appTypeDetail;
		}
		public void setAppTypeDetail(Object appTypeDetail) {
			this.appTypeDetail = appTypeDetail;
		}
	
	public AppTypePanel(){
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		
		appType = new SelectBox();
		appTypeDetail = new SelectBox();
	}
	
	public void load() throws Exception{
		appType.add(App.APP_TYPE_PROJECT, App.APP_TYPE_PROJECT);
		appType.add(App.APP_TYPE_PROCESS, App.APP_TYPE_PROCESS);
		if( selectedAppType == null ){
			appType.setSelected(App.APP_TYPE_PROJECT);
		}else{
			appType.setSelected(selectedAppType);
		}
		this.makeAppChoiceChild();
	}
	
	@ServiceMethod( callByContent=true, eventBinding=EventContext.EVENT_CHANGE, bindingFor = "appType")
	public void makeAppChoiceChild() throws Exception{
		String selectedType = appType.getSelected();
		this.setSelectedAppType(selectedType);
		if( App.APP_TYPE_PROJECT.equals(selectedType)){
			appTypeDetail = new SelectBox();
			
			IProjectNode projectList = ProjectNode.load(session);	
			int j = 0;
			if(projectList.size() > 0) {
				while(projectList.next()){
					String projectId = projectList.getId();
					String projectName = projectList.getName();
					((SelectBox)appTypeDetail).add(projectName, projectId);
				}
			}
		}else if( App.APP_TYPE_PROCESS.equals(selectedType)){
			appTypeDetail = new ProcessSelectPanel();
			((ProcessSelectPanel)appTypeDetail).setId(this.getClass().getSimpleName());
			if( this.getAppTypeDetail() != null ){
				((ProcessSelectPanel)appTypeDetail).setDefinitionId(this.getSelectedAppDetail());
			}
		}
		
	}
	
	@ServiceMethod( callByContent=true, eventBinding=EventContext.EVENT_CHANGE, bindingFor = "appTypeDetail")
	public void appTypeDetailSelect() throws Exception{
		String selectedType = appType.getSelected();
		if( App.APP_TYPE_PROJECT.equals(selectedType)){
			this.setProjectId(((SelectBox)appTypeDetail).getSelected());
		}else if( App.APP_TYPE_PROCESS.equals(selectedType)){
			
		}
	}
}
