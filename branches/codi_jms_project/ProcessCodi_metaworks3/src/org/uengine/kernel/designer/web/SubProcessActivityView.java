package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;
import org.uengine.kernel.SubProcessActivity;

public class SubProcessActivityView extends ActivityView{
	
	@Override
	@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow showDefinitionMonitor() throws Exception{
		SubProcessActivity activity = (SubProcessActivity)this.getActivity();
		ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
		if( activity != null && activity.getDefinitionId() != null && !"".equals(activity.getDefinitionId()) && activity.getAlias() != null){
			processViewerPanel.setDefinitionId(activity.getDefinitionId());
			processViewerPanel.setAlias(activity.getAlias());
			processViewerPanel.setOpenerActivity(this.getActivity());
			processViewerPanel.setOpenerActivityViewId(this.getId());
			processViewerPanel.loadDefnitionView();
		}else{
			processViewerPanel.setOpenerActivity(this.getActivity());
			processViewerPanel.setOpenerActivityViewId(this.getId());
			processViewerPanel.findDefnitionView();
		}
		
		ModalWindow modalWindow = new ModalWindow(processViewerPanel);
		modalWindow.setWidth(700);
		modalWindow.setHeight(500);
		
		return modalWindow;  
	 }
}
