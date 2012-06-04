package org.uengine.codi.mw3.model;

import javax.persistence.Id;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.metaworks.widget.Window;
import org.uengine.codi.mw3.admin.PageNavigator;

public class NewChildContentPanel  {
	
	String parentFolder;
	@Id
	@Hidden
		public String getParentFolder() {
			return parentFolder;
		}
		public void setParentFolder(String parentFolder) {
			this.parentFolder = parentFolder;
		}
		
	@AutowiredFromClient
	public Session session;

	@AutowiredFromClient
	public PageNavigator pageNavigator;	
	
	@ServiceMethod
	public EntityDesignerContentPanel newEntity() throws Exception{
		EntityDesignerContentPanel entityDesignerContentPanel = new EntityDesignerContentPanel();		
		entityDesignerContentPanel.getMetaworksContext().setHow(pageNavigator.getPageName());
		entityDesignerContentPanel.newEntity(session.getUser(), getParentFolder().toString());
						
		return entityDesignerContentPanel;
	}
		
	@ServiceMethod
	public ClassDesignerContentPanel newJavaClass() throws Exception{
		ClassDesignerContentPanel classDesigner = new ClassDesignerContentPanel();
		classDesigner.getMetaworksContext().setHow(pageNavigator.getPageName());
		classDesigner.newClass(getParentFolder().toString());
		
		return classDesigner;
	}

	@ServiceMethod
	public ProcessDesignerWindow newProcess() throws Exception{
		ProcessDesignerWindow processDesigner = new ProcessDesignerWindow();
		processDesigner.getMetaworksContext().setHow(pageNavigator.getPageName());
		processDesigner.newProcessDefinition(getParentFolder().toString());
		
		return processDesigner;
	}

//	@ServiceMethod
//	public RuleDesignerContentPanel newRule() throws Exception{
//		RuleDesignerContentPanel designer = new RuleDesignerContentPanel();
//		designer.newDefinition(getParentFolder().toString());
//		
//		return designer;
//	}

	@ServiceMethod
	public FormDesignerContentPanel newForm(){
		FormDesignerContentPanel formDesigner = new FormDesignerContentPanel();
		formDesigner.getMetaworksContext().setHow(pageNavigator.getPageName());
		formDesigner.newForm(getParentFolder().toString());
		
		return formDesigner;
	}
	
	@ServiceMethod(target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow newFolder(){
		NewFolder nf = new NewFolder();
		nf.setParentFolderDefId(getParentFolder());

		ModalWindow newFolderWindow = new ModalWindow(nf, 300, 200, "폴더 추가");
		
		
		return newFolderWindow;
	}
	
	@ServiceMethod
	public RuleDesignerWindow newRule() throws Exception{
		
		RuleDesignerWindow ruleDesignerWindow = new RuleDesignerWindow();
		ruleDesignerWindow.newRule(getParentFolder().toString());
		ruleDesignerWindow.getMetaworksContext().setHow(pageNavigator.getPageName());
		
		return ruleDesignerWindow;
	}
	
	@ServiceMethod
	public WebServiceAdapterContentPanel newWebServiceAdapter() throws Exception{
		
		WebServiceAdapterContentPanel webServiceAdapterContentPanel = new WebServiceAdapterContentPanel();
		webServiceAdapterContentPanel.newWebServiceAdapter(getParentFolder().toString());
		webServiceAdapterContentPanel.getMetaworksContext().setHow(pageNavigator.getPageName());
		
		return webServiceAdapterContentPanel;
	}
	
	

}
