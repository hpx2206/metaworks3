package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

public class VisualizationType implements ContextAware{

	public VisualizationType() {
	}
	

	public VisualizationType(WfNode wfNode) {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		setNodeId(wfNode.getId());
	}
	
	String visType;
	@Range(options={"개조식", "마인드맵", "UML", "프레젠테이션"},
			values={"bullet","mindmap","uml", "pt"})

		public String getVisType() {
			return visType;
		}
	
		public void setVisType(String visType) {
			this.visType = visType;
		}

	String nodeId;
		public String getNodeId() {
			return nodeId;
		}
	
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}
		
		
	@ServiceMethod(callByContent=true)
	public void save() throws Exception{
		WfNode theNode = new WfNode();
		theNode.setId(getNodeId());
		theNode.databaseMe().setVisType(getVisType());
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	
}
