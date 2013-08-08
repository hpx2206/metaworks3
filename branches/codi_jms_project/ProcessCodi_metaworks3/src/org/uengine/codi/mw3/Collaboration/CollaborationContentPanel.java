package org.uengine.codi.mw3.Collaboration;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.webProcessDesigner.ProcessViewWindow;

public class CollaborationContentPanel  implements ContextAware{
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	ProcessViewWindow processViewWindow;
		public ProcessViewWindow getProcessViewWindow() {
			return processViewWindow;
		}
	
		public void setProcessViewWindow(ProcessViewWindow processViewWindow) {
			this.processViewWindow = processViewWindow;
		}

	ProcessNameView processNameView;
		public ProcessNameView getProcessNameView() {
			return processNameView;
		}
		public void setProcessNameView(ProcessNameView processNameView) {
			this.processNameView = processNameView;
		}
		
	public CollaborationContentPanel() throws Exception{
		
	}
	
	public CollaborationContentPanel(Session session) throws Exception{
		// TODO Auto-generated constructor stub
//				Object clipboard = session.getClipboard();
//				ResourceNode node = (ResourceNode)clipboard;
//				String defId = node.getName();
//				ProcessViewWindow defWindow = new ProcessViewWindow();
//				defWindow.setDefId(defId);
//				defWindow.setAlias(node.getPath());
//				defWindow.load();
	}
	
}
