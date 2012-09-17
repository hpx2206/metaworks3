package org.uengine.codi.activitytypes;

import java.net.URLEncoder;
import java.util.Map;

import org.metaworks.FieldDescriptor;
import org.metaworks.Type;
import org.uengine.codi.mw3.knowledge.KnowledgeTool;
import org.uengine.codi.mw3.model.GenericWorkItem;
import org.uengine.codi.mw3.model.GenericWorkItemHandler;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessInstance;
import org.uengine.persistence.dao.WorkListDAO;
import org.uengine.ui.XMLValueInput;

public class KnowledgeActivity extends HumanActivity{
	
	public static void metaworksCallback_changeMetadata(Type type){
		FieldDescriptor fd;

		fd = type.getFieldDescriptor("KnolNodeId");
		XMLValueInput inputter = new XMLValueInput("/dwr/xstr-rpc?className=org.uengine.codi.mw3.knowledge.WfNode&methodName=loadChildren&object=" + URLEncoder.encode("<org.uengine.codi.mw3.knowledge.WfNode><id>" + "uEngine" + "</id></org.uengine.codi.mw3.knowledge.WfNode>"));
		
		fd.setInputter(inputter);
		
		
	}
	
	String knolNodeId;

		public String getKnolNodeId() {
			return knolNodeId;
		}
	
		public void setKnolNodeId(String knolNodeId) {
			this.knolNodeId = knolNodeId;
		}

	@Override
	protected void addWorkitem(ProcessInstance instance,
			String defaultStatus) throws Exception {
		// TODO Auto-generated method stub
		super.addWorkitem(instance, defaultStatus);
		
		String[] taskIds = getTaskIds(instance);
		
		GenericWorkItemHandler genericWIH = new GenericWorkItemHandler();
		KnowledgeTool knolTool = new KnowledgeTool();
		knolTool.setNodeId(getKnolNodeId());
		
		genericWIH.setTool(knolTool);
		genericWIH.onObject2Relation();
		
		if(taskIds.length > 0){
			
			WorkListDAO wlDAO = (WorkListDAO) instance.getProcessTransactionContext().findSynchronizedDAO("BPM_WORKLIST", "taskId", taskIds[0], WorkListDAO.class);
			wlDAO.set("content", genericWIH.getSerializedTool());
			wlDAO.set("type", "generic");
		}
	}
//
//	@Override
//	public Map createParameter(ProcessInstance instance) throws Exception {
//		// TODO Auto-generated method stub
//		Map parameters = super.createParameter(instance);
//		
//		parameters.put("content", "");
//		parameters.put("type", "generic");
//		
//		return parameters;
//	}
//	
	
	

}
