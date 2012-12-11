package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.Popup;
import org.uengine.codi.mw3.model.Session;
import org.uengine.kernel.Activity;
import org.uengine.kernel.viewer.ActivityViewer;
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
		
	public void load(String instanceId) throws Exception {
		org.uengine.kernel.ProcessInstance instance = processManager.getProcessInstance(instanceId);
		org.uengine.kernel.ProcessDefinition procDef = processManager.getProcessInstance(instanceId).getProcessDefinition();
		System.out.println(procDef.getAlias());
		
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
	@ServiceMethod(callByContent=true)
	public Popup showActivityInfo() throws Exception{
		
		return new Popup();
	}
	@AutowiredFromClient
	public Session session;
	
	public ProcessManagerRemote processManager;
}
