package org.uengine.codi.mw3.webProcessDesigner.jms;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;

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
	ProcessViewNaivgator	processViewNaivgator;
		public ProcessViewNaivgator getDefinitionNaivgator() {
			return processViewNaivgator;
		}
		public void setDefinitionNaivgator(ProcessViewNaivgator processViewNaivgator) {
			this.processViewNaivgator = processViewNaivgator;
		}
	ProcessViewer processViewer;
		public ProcessViewer getDefinitionMonitor() {
			return processViewer;
		}
		public void setDefinitionMonitor(ProcessViewer processViewer) {
			this.processViewer = processViewer;
		}
		
	@Autowired
	ProcessViewPanel processViewPanel;
	
	public ProcessViewerPanel(){
		metaworksContext = new MetaworksContext();
	}
	public void removeLink(){
		this.definitionId = null;
	}
	
	public Object[] saveLink(){
		// TODO 
		return null;
	}
	
	@ServiceMethod()
	public Object[] openLink(){
		processViewPanel.refresh(definitionId, definitionName);
		
		return new Object[]{processViewPanel, new Remover(new ModalWindow() , true) };
	}
}
