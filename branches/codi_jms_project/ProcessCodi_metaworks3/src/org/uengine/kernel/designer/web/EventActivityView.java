package org.uengine.kernel.designer.web;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewerPanel;
import org.uengine.kernel.EventActivity;

public class EventActivityView extends ActivityView{
		
		@Override
		@ServiceMethod(callByContent=true , target=ServiceMethodContext.TARGET_POPUP)
		public ModalWindow showDefinitionMonitor() throws Exception{
			EventActivity activity = (EventActivity)this.getActivity();
			ProcessViewerPanel processViewerPanel = new ProcessViewerPanel();
			if( activity != null && activity.getDefinitionId() != null && !"".equals(activity.getDefinitionId())){
				processViewerPanel.setDefinitionId(activity.getDefinitionId());
				processViewerPanel.loadDefnitionView();
			}else{
				processViewerPanel.findDefnitionView();
			}
			
			ModalWindow modalWindow = new ModalWindow(processViewerPanel);
			modalWindow.setWidth(700);
			modalWindow.setHeight(500);
			
			return modalWindow;  
		 }
}
