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
		
		this.shapeType = "GEOM";
		
		this.id = cv.id  ;			
		this.parent = cv.parent  ;	
		this.shapeId = cv.shapeId  ;	
		this.x = cv.x  ;	
		this.y = cv.y  ;	
		this.width = cv.width  ;	
		this.height = cv.height  ;	
		this.style = cv.style  ;	
		this.from = cv.from  ;	
		this.to = cv.to  ;	
		this.fromEdge = cv.fromEdge  ;	
		this.toEdge = cv.toEdge  ;	
		this.label = cv.label  ;	
		this.angle = cv.angle  ;	
		this.value = cv.value  ;	
		this.data = cv.data  ;
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
					// 혹시 role 이 배열로 들어있을지 모르니, role 을 먼저 체크하는 loof를 돌린다
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
						if( customType != null && "role".equalsIgnoreCase(customType) ){
							Role role = new Role();
							role.setName(customName);
//							role.setRoleResolutionContext(context)
							this.setRole(role);
						}
					}
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
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
								ParameterContext pc[] = new ParameterContext[0];
								int k = 0;
								for(int j=0; j < pvs.length ; j++){
									if( customName.equals(pvs[j].getName()) ){
										if( k == 0){
											pc = new ParameterContext[1];
										}else{
											// TODO 오류 체크 해봐야함
											ParameterContext pc2[] = new ParameterContext[k+1];
											System.arraycopy(pc, 0, pc2, 0, k+1);
											pc = pc2;
										}
										
										pc[k] = new ParameterContext();
										pc[k].setArgument(pvs[j].getDisplayName());
										pc[k].setVariable(pvs[j]);
										k++;
									}
								}
								humanActivity.setParameters(pc);
								humanActivity.setRole(this.getRole());
								return humanActivity;
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
	
	public void viewActivityInfo(Activity activity) throws Exception{
		String activityName = activity.getClass().getName();
		if( activity instanceof HumanActivity){
			String roleName = ((HumanActivity) activity).getRole().getName();
		}
	}
}
