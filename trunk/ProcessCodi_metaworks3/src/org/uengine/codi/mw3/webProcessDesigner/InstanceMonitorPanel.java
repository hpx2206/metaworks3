package org.uengine.codi.mw3.webProcessDesigner;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.uengine.codi.mw3.model.Session;
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
		if( cellsList != null){
			CanvasDTO []cells = new CanvasDTO[cellsList.size()];
			for(int i = 0; i < cellsList.size(); i++){
				cells[i] = (CanvasDTO)cellsList.get(i);
				if( cells[i] != null && cells[i].getJsonString() != null){
					this.setGraphString(cells[i].getJsonString());
				}
			}
			// canvas setting
			this.setCell(cells);
		}
		
	}
	@AutowiredFromClient
	public Session session;
	
	public ProcessManagerRemote processManager;
}
