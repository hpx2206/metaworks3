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
		
	public String geomInfoStart(int geomX ,int geomY ) throws Exception{
		// shapeId = "OG.shape.bpmn.E_Start";
		// shapeId = "OG.shape.bpmn.E_End";
		StringBuffer geomInfo = new StringBuffer();
		geomInfo.append("{");
			geomInfo.append("type:'Circle',");
			geomInfo.append("center:["+geomX+", "+geomY+"],");
			geomInfo.append("radius:15");
		geomInfo.append("}");
		return null;
	}
	public String geomInfoSwitch(int geomX ,int geomY ) throws Exception{
		// String shapeId = "OG.shape.bpmn.G_Gateway";
		StringBuffer geomInfo = new StringBuffer();
		geomInfo.append("{");
			geomInfo.append("type:'Polygon',");
			geomInfo.append("vertices:[");
				geomInfo.append("["+(geomX-  10)+", "+geomY+"],");
				geomInfo.append("["+geomX+", "+(geomY+10)+"],");
				geomInfo.append("["+(geomX + 10) +", "+geomY+"],");
				geomInfo.append("["+geomX+", "+(geomY-10)+"],");
				geomInfo.append("["+(geomX - 10) +", "+geomY+"]");
			geomInfo.append("]");
		geomInfo.append("}");
		
		
		return null;
	}
	public String geomInfoTask(int geomX ,int geomY , int geomWidth, int geomHeight ) throws Exception{
		// String shapeId = "OG.shape.bpmn.A_Task";
		StringBuffer geomInfo = new StringBuffer();
		geomInfo.append("{");
			geomInfo.append("type:'Rectangle',");
			geomInfo.append("upperLeft:["+( geomX - geomWidth/2 ) +", "+( geomY - geomHeight/2 )+"],");
			geomInfo.append("width:"+geomWidth+",");
			geomInfo.append("height:"+geomHeight+",");
			geomInfo.append("angle:0");
		geomInfo.append("}");
		return null;
	}
		
		
	public Activity makeProcVal(Activity activity) {
		if("OG.shape.bpmn.A_Task".equals(this.getShapeId()) ){
			if(this.getData() != null){
				String data = WpdContentPanel.unescape(this.getData());
				JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data);
				if( jsonArray != null && jsonArray.size() > 0){
					// 혹시 role 이 배열로 들어있을지 모르니, role 을 먼저 체크하는 loof를 돌린다
//					for( int i = 0; i < jsonArray.size() ; i++){
//						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
//						String customName = jsonObj.getString("customName");
//						String customType = jsonObj.getString("customType");
//						if( customType != null && "role".equalsIgnoreCase(customType) ){
//							Role role = new Role();
//							role.setName(customName);
////							role.setRoleResolutionContext(context)
//							this.setRole(role);
//						}
//					}
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
						// 지식노드 - KnowledgeActivity 생성
						if( customType != null && "wfNode".equalsIgnoreCase(customType)){
							KnowledgeActivity knowledgeActivity = (KnowledgeActivity)activity;
							String customId = jsonObj.getString("customId");
							knowledgeActivity.setName(customName);
							knowledgeActivity.setKnolNodeId(customId);
						}
						// 클레스 - HumanActivity 생성
						if( customType != null ){
							HumanActivity humanActivity = (HumanActivity)activity;
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
							}
						}
					}
				}
			}
		}	// end if("OG.shape.bpmn.A_Task".equals(this.getShapeId()) )
		return activity;
	}
	
	public void viewActivityInfo(Activity activity) throws Exception{
		String activityName = activity.getClass().getName();
		if( activity instanceof HumanActivity){
			String roleName = ((HumanActivity) activity).getRole().getName();
		}
	}
}
