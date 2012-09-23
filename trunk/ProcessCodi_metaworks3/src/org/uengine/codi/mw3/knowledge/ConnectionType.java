package org.uengine.codi.mw3.knowledge;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;

public class ConnectionType implements ContextAware{

	public ConnectionType() {
	}
	

	public ConnectionType(WfNode wfNode) {
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		setNodeId(wfNode.getId());
	}
	
	String connType;
	@Face(displayName="하위 연결 방식")
	@Range(options={"관계", "포함", "상속", "구체화"},
			values={"related","composed-of","inherit", "realize"})
		public String getConnType() {
			return connType;
		}
		public void setConnType(String connType) {
			this.connType = connType;
		}
	


	String nodeId;
	@Hidden
		public String getNodeId() {
			return nodeId;
		}
	
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}
		
		
	@ServiceMethod(callByContent=true)
	@Face(displayName="OK")
	public void save() throws Exception{
		WfNode theNode = new WfNode();
		theNode.setId(getNodeId());
		theNode.databaseMe().setConnType(getConnType());
		
		MetaworksRemoteService.pushClientObjects(new Object[]{new Refresh(theNode.databaseMe())});
	}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	
	
}
