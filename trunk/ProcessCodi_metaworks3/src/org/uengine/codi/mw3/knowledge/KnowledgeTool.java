package org.uengine.codi.mw3.knowledge;

import java.io.Serializable;

import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class KnowledgeTool implements ITool, Serializable{
	
	String nodeId; 
		public String getNodeId() {
			return nodeId;
		}
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}

	//very important in serializable object
	transient WfPanel wfPanel;
		public WfPanel getWfPanel() {
			return wfPanel;
		}
		public void setWfPanel(WfPanel wfPanel) {
			this.wfPanel = wfPanel;
		}
		
		
	@Override
	public void onLoad() {
		try {
			wfPanel = new WfPanel();
			wfPanel.load(nodeId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub
		
	}

}
