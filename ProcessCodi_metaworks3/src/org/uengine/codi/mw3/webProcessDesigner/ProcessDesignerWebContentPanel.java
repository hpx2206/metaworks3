package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.activitytypes.KnowledgeActivity;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.contexts.ComplexType;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.ComplexActivity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ParameterContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.graph.Transition;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ProcessDesignerWebContentPanel extends ContentWindow implements ContextAware {
	
	MetaworksContext metaworksContext;
	public MetaworksContext getMetaworksContext() {
		return metaworksContext;
	}
	public void setMetaworksContext(MetaworksContext metaworksContext) {
		this.metaworksContext = metaworksContext;
	}
	RolePanel rolePanel	;
	public RolePanel getRolePanel() {
		return rolePanel;
	}
	public void setRolePanel(RolePanel rolePanel) {
		this.rolePanel = rolePanel;
	}
	
	public ProcessDesignerWebContentPanel(){
		rolePanel = new RolePanel();
	}
	
	String alias;
	@Id
	@Hidden
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
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
		
	HashMap<String, CanvasDTO> canvasMap;
		public HashMap<String, CanvasDTO> getCanvasMap() {
			return canvasMap;
		}
		public void setCanvasMap(HashMap<String, CanvasDTO> canvasMap) {
			this.canvasMap = canvasMap;
		}
		
	String valiableString;
		public String getValiableString() {
			return valiableString;
		}
		public void setValiableString(String valiableString) {
			this.valiableString = valiableString;
		}
		
	String tempElementName;
		@Hidden
		public String getTempElementName() {
			return tempElementName;
		}
		public void setTempElementName(String tempElementName) {
			this.tempElementName = tempElementName;
		}
	
	String processName;
		@Hidden
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow doSave() throws Exception{
		ProcessDesignerTitle dTitle = new ProcessDesignerTitle();
		dTitle.setMetaworksContext(new MetaworksContext());
		dTitle.getMetaworksContext().setWhen("edit");
		dTitle.setTitle(getProcessName());
		return new ModalWindow(dTitle , 300, 200,  "프로세스명 입력" );
	}
	
	@ServiceMethod(callByContent=true, target="popup")
	public ModalWindow gateCondition() throws Exception{
		ConditionPanel conditionPanel = new ConditionPanel(tempElementName);
		conditionPanel.setMetaworksContext(new MetaworksContext());
		conditionPanel.getMetaworksContext().setWhen("edit");
		conditionPanel.setValiableString(getValiableString());
		conditionPanel.load();
		return new ModalWindow(conditionPanel , 600, 450,  "조건분기" );
	}
//	@ServiceMethod(callByContent=true, target="popup")
//	public ModalWindow dataMapping() throws Exception{
//		MappingPanel conditionPanel = new MappingPanel();
//		conditionPanel.setMetaworksContext(new MetaworksContext());
//		conditionPanel.getMetaworksContext().setWhen("edit");
//		return new ModalWindow(conditionPanel , 800, 500,  "데이터매핑" );
//	}
	
	public void saveTemp() throws Exception{
		if( cell != null){
			HashMap<String, CanvasDTO> canvasMap = new HashMap<String, CanvasDTO>();
			for(int i = 0; i < cell.length; i++){
				CanvasDTO cv = cell[i];
				canvasMap.put(cv.getId(), cv);
			}
			setCanvasMap(canvasMap);
		}
	}
		
	@ServiceMethod(callByContent=true)
	public void save(String title) throws Exception{
		ArrayList<CanvasDTO> cells = new ArrayList<CanvasDTO>();
		ProcessDefinition def = new ProcessDefinition();
		if( cell != null){
			Role[] roles = new Role[0];
			Activity[] ac = new Activity[0];
			
			if( title == null ){
				throw new Exception("title is null. please set process title");
			}
			def.setName(title);
			def.setRoles(roles);
			def.setChildActivities(ac);
			
			HashMap<String, CanvasDTO> canvasMap = new HashMap<String, CanvasDTO>();
			HashMap<String, Activity> activityMap = new HashMap<String, Activity>();
			for(int i = 0; i < cell.length; i++){
				CanvasDTO cv = cell[i];
				cells.add(cv);
				canvasMap.put(cv.getId(), cv);
			}
			setCanvasMap(canvasMap);
			
			Collection<CanvasDTO> ct = canvasMap.values();
            Iterator<CanvasDTO> iterator = ct.iterator();
            while (iterator.hasNext()) {
				CanvasDTO cv = iterator.next();
				Activity activity = addActivity(cv , def);
				if( activity != null ){
					if( activity instanceof HumanActivity){
						def.addRole(((HumanActivity)activity).getRole());
					}
					// 엑티비티 생성
					def.addChildActivity(activity);
					activityMap.put(cv.getId(), activity);
				}
			}
            Iterator<CanvasDTO> iterator2 = ct.iterator();
            while (iterator2.hasNext()) {
	            CanvasDTO cv = iterator2.next();
				if( cv != null && "EDGE".equalsIgnoreCase(cv.getShapeType()) ){
					String formStr = cv.getFrom();
					String toStr = cv.getTo();
					String fromId = formStr.substring(0, formStr.indexOf("_TERMINAL"));
					String toId = toStr.substring(0, toStr.indexOf("_TERMINAL"));
					if( activityMap.get(fromId) != null && activityMap.get(toId) != null){
						Transition ts = new Transition(activityMap.get(fromId).getTracingTag()  , activityMap.get(toId).getTracingTag());
						// 트렌지션 생성
						def.addTransition(ts);
					}
				}
            }
			CanvasDTO jsonString = new CanvasDTO();
			jsonString.setJsonString(graphString);
			cells.add(jsonString);
			def.setExtendedAttribute( "cells", cells );
			
		}
		
		processManager.addProcessDefinition( title , 0, "description", false, GlobalContext.serialize(def, ProcessDefinition.class), "", "/"+title+".process2", "/"+title+".process2", "process2");
		
	}
	
	public Activity addActivity(CanvasDTO cv , ProcessDefinition def)  throws Exception{
		
		Activity activity = null;
		if( cv != null && "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
			// 휴먼 엑티비티 추가 TODO 여러가지 상황에 맞추어서 추후 변경됨
			if(cv.getData() != null){
				String data = unescape(cv.getData());
				JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data);
				if( jsonArray != null && jsonArray.size() > 0){
					KnowledgeActivity knowledgeActivity = new KnowledgeActivity();
					HumanActivity humanActivity = new HumanActivity();
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
						// 지식노드 - KnowledgeActivity 생성
						if( customType != null && "wfNode".equalsIgnoreCase(customType)){
							String customId = jsonObj.getString("customId");
							knowledgeActivity.setName(customName);
							knowledgeActivity.setKnolNodeId(customId);
							
							activity = knowledgeActivity;
						// 역할 - HumanActivity 생성
						}else 	if( customType != null && "role".equalsIgnoreCase(customType) ){
							Role role = new Role();
							role.setName(customName);
							knowledgeActivity.setRole(role);
							
							activity = knowledgeActivity;
						// 클레스 - HumanActivity 생성
						}else 	if( customType != null && "class".equalsIgnoreCase(customType) ){
//							String resourceBase = CodiClassLoader.getMyClassLoader().sourceCodeBase();
//							String fullName = resourceBase  + customName;
							
							
							
							TextContext text = new TextContext();
							text.setText("complexAct");

							ComplexType complexType = new ComplexType();
							complexType.setTypeId("["+customName+"]");
							
							ProcessVariable pv = new ProcessVariable();
							pv.setName("aaaa");
							pv.setDisplayName(text);
							pv.setType(complexType.getClass());
							pv.setDefaultValue(complexType);
							
							ParameterContext pc[] = new ParameterContext[1];
							for(int j=0; j < pc.length ; j++){
								pc[j] = new ParameterContext();
								pc[j].setArgument(text);
								pc[j].setVariable(pv);
							}
							ProcessVariable pvs[] = def.getProcessVariables();
							if( pvs != null && pvs.length > 0){
								ProcessVariable pvsTemp[] = new ProcessVariable[pvs.length + 1];
								System.arraycopy(pvs, 0, pvsTemp, 0, pvs.length);
								pvsTemp[pvs.length] = pv;
								pvs = pvsTemp;
							}else{
								pvs = new ProcessVariable[1];
								pvs[0] = pv;
							}
							def.setProcessVariables(pvs);
							humanActivity.setParameters(pc);
							activity = humanActivity;
						}
					}
					
					
				}
			}
			if( cv.getParent() != null){
				if( activity instanceof HumanActivity){
					String groupId = cv.getParent();
					CanvasDTO groupCanvas = getCanvasMap().get(groupId);
					Role role = new Role();
					role.setName(groupCanvas.getLabel());
					((HumanActivity)activity).setRole(role);
				}
			}
		}
		return activity;
	}
	public void load() throws Exception{
		
		String processName = getAlias().substring(0, getAlias().indexOf("."));
		setProcessName(processName);
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + processName + ".process2");
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString("UTF-8"));
			ArrayList<CanvasDTO> cellsList = (ArrayList<CanvasDTO>) def.getExtendedAttributes().get("cells");
			if( cellsList != null){
				CanvasDTO []cells = new CanvasDTO[cellsList.size()];
				for(int i = 0; i < cellsList.size(); i++){
					cells[i] = (CanvasDTO)cellsList.get(i);
					if( cells[i] != null && cells[i].getJsonString() != null){
						this.setGraphString(cells[i].getJsonString());
					}
				}
				this.setCell(cells);
			}
			Role[] roles = def.getRoles();
			if( roles != null && roles.length != 0){
				ArrayList<org.uengine.codi.mw3.webProcessDesigner.Role> role = rolePanel.getRoles();
				for(int i =0 ; i < roles.length; i++){
					org.uengine.codi.mw3.webProcessDesigner.Role designerRole = new org.uengine.codi.mw3.webProcessDesigner.Role();
					designerRole.setName(roles[i].getName());
					designerRole.setMetaworksContext(new MetaworksContext());
					designerRole.getMetaworksContext().setWhen("view");
					role.add(designerRole);
				}
				rolePanel.setRoles(role);
			}
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(src
							.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(src
							.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	@Autowired
	public ProcessManagerRemote processManager;
}
