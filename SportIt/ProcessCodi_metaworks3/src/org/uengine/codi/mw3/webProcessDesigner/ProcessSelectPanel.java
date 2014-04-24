package org.uengine.codi.mw3.webProcessDesigner;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.ide.Project;
import org.uengine.kernel.Activity;

public class ProcessSelectPanel {
	String id;
		@Id
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}

	String definitionId;
	    public String getDefinitionId() {
	      return definitionId;
	    }
	    public void setDefinitionId(String l) {
	      definitionId = l;
	    }
	    
	Activity openerActivity;
		public Activity getOpenerActivity() {
			return openerActivity;
		}
		public void setOpenerActivity(Activity openerActivity) {
			this.openerActivity = openerActivity;
		}
	    
    @AutowiredFromClient
	public Project project;
	
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showDefinitionMonitor() throws Exception{
		ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		modalWindow.setId(this.getId());
		
		if( definitionId != null ){
			processViewerPanel.setViewType("definitionView");
			processViewerPanel.setDefinitionId(this.getDefinitionId());
			processViewerPanel.setAlias(this.getDefinitionId());
			processViewerPanel.setOpenerActivity(this.getOpenerActivity());
			processViewerPanel.setUseClassLoader(true);
			processViewerPanel.loadDefinitionView();
			modalWindow.setTitle(getDefinitionId());
		}else{
			processViewerPanel.setViewType("definitionEditor");
			processViewerPanel.setOpenerActivity(this.getOpenerActivity());
			processViewerPanel.project = project;
			processViewerPanel.findDefinitionView();
			modalWindow.setTitle("$resource.menu.selectProcess");
		}
		processViewerPanel.setOpenerPopupId(this.getId());
		modalWindow.setPanel(processViewerPanel);
		return modalWindow;  
	 }

}
