package org.uengine.codi.mw3.webProcessDesigner;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.uengine.codi.activitytypes.KnowledgeActivity;
import org.uengine.kernel.Activity;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.SwitchActivity;

public class GeomShape extends CanvasDTO {
	
	public GeomShape(){
		this.shapeType = "GEOM";
	}
	public GeomShape(CanvasDTO cv){
		this.shapeId = cv.shapeId;
		this.shapeType = "GEOM";
		this.data = cv.data;
	}
	ProcessVariable pvs[];
		public ProcessVariable[] getPvs() {
			return pvs;
		}
		public void setPvs(ProcessVariable[] pvs) {
			this.pvs = pvs;
		}
	Role role;	
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		
	public Activity makeActivity() {
		if("OG.shape.bpmn.A_Task".equals(this.getShapeId()) ){
			if(this.getData() != null){
				String data = ProcessDesignerWebContentPanel.unescape(this.getData());
				JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data);
				if( jsonArray != null && jsonArray.size() > 0){
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
						if( customType != null && "role".equalsIgnoreCase(customType) ){
							Role role = new Role();
							role.setName(customName);
							this.setRole(role);
						}
						// 지식노드 - KnowledgeActivity 생성
						if( customType != null && "wfNode".equalsIgnoreCase(customType)){
							KnowledgeActivity knowledgeActivity = new KnowledgeActivity();
							String customId = jsonObj.getString("customId");
							knowledgeActivity.setName(customName);
							knowledgeActivity.setKnolNodeId(customId);
							knowledgeActivity.setRole(this.getRole());
							return knowledgeActivity;
						// 클레스 - HumanActivity 생성
						}else 	if( customType != null && "class".equalsIgnoreCase(customType) ){
							HumanActivity humanActivity = new HumanActivity();
							ProcessVariable pvs[] = getPvs();
							if( pvs != null){
								for(int j=0; j < pvs.length ; j++){
									if( customName.equals(pvs[j].getName()) ){
										ParameterContext pc[] = new ParameterContext[1];
										pc[0] = new ParameterContext();
										pc[0].setArgument(pvs[j].getDisplayName());
										pc[0].setVariable(pvs[j]);
										humanActivity.setParameters(pc);
										humanActivity.setRole(this.getRole());
										return humanActivity;
									}
								}
							}
						}
					}
				}
			}else{
				HumanActivity humanActivity = new HumanActivity();
				humanActivity.setRole(this.getRole());
				return humanActivity;
			}
		}	// end if("OG.shape.bpmn.A_Task".equals(this.getShapeId()) )
		else if("OG.shape.bpmn.G_Gateway".equals(this.getShapeId()) ){
			SwitchActivity switchActivity = new SwitchActivity();
			
			return switchActivity;
		}
		return null;
	}
}
