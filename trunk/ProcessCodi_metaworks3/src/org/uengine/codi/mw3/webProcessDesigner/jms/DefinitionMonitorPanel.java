package org.uengine.codi.mw3.webProcessDesigner.jms;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;

public class DefinitionMonitorPanel implements ContextAware {
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
	DefinitionNaivgator	definitionNaivgator;
		public DefinitionNaivgator getDefinitionNaivgator() {
			return definitionNaivgator;
		}
		public void setDefinitionNaivgator(DefinitionNaivgator definitionNaivgator) {
			this.definitionNaivgator = definitionNaivgator;
		}
	DefinitionMonitor definitionMonitor;
		public DefinitionMonitor getDefinitionMonitor() {
			return definitionMonitor;
		}
		public void setDefinitionMonitor(DefinitionMonitor definitionMonitor) {
			this.definitionMonitor = definitionMonitor;
		}
		
	public void removeLink(){
		this.definitionId = null;
	}
	
	public Object[] saveLink(){
		// TODO 
		return null;
	}
}
