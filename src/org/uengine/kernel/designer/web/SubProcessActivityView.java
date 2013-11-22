package org.uengine.kernel.designer.web;

import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;
import org.uengine.kernel.SubProcessActivity;

public class SubProcessActivityView extends ActivityView{
	
	@AutowiredFromClient
	public Session session;
	
	@Override
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showDefinitionMonitor() throws Exception{
		SubProcessActivity activity = (SubProcessActivity)this.getActivity();
		ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		modalWindow.setId(this.getId());
		
		if( "definitionView".equals(this.getViewType() )){
			processViewerPanel.setViewType(this.getViewType());
			if( activity == null || (activity != null && activity.getAlias() == null || "".equals(activity.getAlias()))){
				throw new MetaworksException("서브 프로세스가 정의되어있지 않습니다.");
			}
			if( activity != null && activity.getDefinitionId() != null && !"".equals(activity.getDefinitionId()) && activity.getAlias() != null){
				processViewerPanel.setDefinitionId(activity.getDefinitionId());
				processViewerPanel.setAlias(activity.getAlias());
				processViewerPanel.setOpenerActivity(this.getActivity());
				processViewerPanel.setOpenerActivityViewId(this.getId());	
				processViewerPanel.loadDefinitionView();
				modalWindow.setTitle(activity.getDefinitionId());
			}
		}else{
			processViewerPanel.setViewType("definitionEditor");
			
			if( activity != null && activity.getDefinitionId() != null && !"".equals(activity.getDefinitionId()) && activity.getAlias() != null){
				processViewerPanel.setDefinitionId(activity.getDefinitionId());
				processViewerPanel.setAlias(activity.getAlias());
				processViewerPanel.setOpenerActivity(this.getActivity());
				processViewerPanel.setOpenerActivityViewId(this.getId());
				processViewerPanel.loadDefinitionView();
			}else{
				processViewerPanel.setOpenerActivity(this.getActivity());
				processViewerPanel.setOpenerActivityViewId(this.getId());
				processViewerPanel.session = session;
				processViewerPanel.findDefinitionView();
			}
			modalWindow.setTitle("$SubProcessActivityEdit");
		}
		modalWindow.setPanel(processViewerPanel);
		return modalWindow;  
	 }
}
