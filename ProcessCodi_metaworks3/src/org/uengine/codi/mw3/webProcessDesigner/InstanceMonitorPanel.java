package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.viewer.DefaultActivityViewer;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceMonitorPanel {
	CanvasDTO cell[];
		public CanvasDTO[] getCell() {
			return cell;
		}
		public void setCell(CanvasDTO[] cell) {
			this.cell = cell;
		}
	String graphString;
		public String getGraphString() {
			return graphString;
		}
		public void setGraphString(String graphString) {
			this.graphString = graphString;
		}
	String instanceId;
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
	ProcessInstance instance;
		public ProcessInstance getInstance() {
			return instance;
		}
		public void setInstance(ProcessInstance instance) {
			this.instance = instance;
		}
	ProcessDefinition procDef;
		public ProcessDefinition getProcDef() {
			return procDef;
		}
		public void setProcDef(ProcessDefinition procDef) {
			this.procDef = procDef;
		}
	String tempTracingTag;
		public String getTempTracingTag() {
			return tempTracingTag;
		}
		public void setTempTracingTag(String tempTracingTag) {
			this.tempTracingTag = tempTracingTag;
		}
	public void load(String instanceId) throws Exception {
		this.setInstanceId(instanceId);
		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();
		
		ArrayList<CanvasDTO> cellsList = (ArrayList<CanvasDTO>) procDef.getExtendedAttributes().get("cells");
		DefaultActivityViewer dav = new DefaultActivityViewer();
		if( cellsList != null){
			CanvasDTO []cells = new CanvasDTO[cellsList.size()];
			for(int i = 0; i < cellsList.size(); i++){
				cells[i] = (CanvasDTO)cellsList.get(i);
				if( cells[i] != null && cells[i].getJsonString() != null){
					this.setGraphString(cells[i].getJsonString());
				}
				String tracingTag = cells[i].getTracingTag();
				String status = null;
				if( tracingTag != null && cells[i].getShapeType().equals("GEOM")){	// TODO GEOM 으로 체크하는 로직 삭제
//					Activity activity = procDef.getActivity(tracingTag);
//					instance.getStatus(activity.getTracingTag());
					status = instance.getStatus(tracingTag);
					cells[i].setInstStatus(status);
					cells[i].setBackgroundColor( dav.getStatusColor(status) );
				}
			}
			// canvas setting
			this.setCell(cells);
		}
		
	}
	@ServiceMethod(callByContent=true, target="popup")
	public Popup showActivityInfo() throws Exception{
		Popup popup = new Popup(400,275);
		TaskInfoPanel taskInfo = new TaskInfoPanel();
		
		ProcessInstance instance = processManager.getProcessInstance(instanceId);
		ProcessDefinition procDef = instance.getProcessDefinition();
		
		Activity activity = procDef.getActivity(getTempTracingTag());
		taskInfo.load(getInstanceId(), activity, instance);
		popup.setPanel(taskInfo);
		return popup;
	}
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
}
