package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.contexts.ComplexType;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.Condition;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.kernel.Role;
import org.uengine.kernel.SwitchActivity;
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
	
	DefineTab defineTab;
		public DefineTab getDefineTab() {
			return defineTab;
		}
		public void setDefineTab(DefineTab defineTab) {
			this.defineTab = defineTab;
		}
	
	public ProcessDesignerWebContentPanel() throws Exception{
		defineTab = new DefineTab();
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
		
	/*
	 * 조건들의 정보를 담고있다 
	 * 조건은 line 에서 셋팅을 하며 여러 조건들이 있으니 map 으로 구성
	 * <line Id , json Array> 형식으로 쓰임
	 */
	HashMap<String, String> conditionMap;
		public HashMap<String, String> getConditionMap() {
			return conditionMap;
		}
		public void setConditionMap(HashMap<String, String> conditionMap) {
			this.conditionMap = conditionMap;
		}
		
	/*
	 * 라인정보를 셋팅하기 위하여 해당 id를 임시적으로 들고있다
	 */
	String tempElementId;
	@Hidden
		public String getTempElementId() {
			return tempElementId;
		}
		public void setTempElementId(String tempElementId) {
			this.tempElementId = tempElementId;
		}
	
	String tempElementName;
		@Hidden
		public String getTempElementName() {
			return tempElementName;
		}
		public void setTempElementName(String tempElementName) {
			this.tempElementName = tempElementName;
		}
	String tempElementType;
		@Hidden
		public String getTempElementType() {
			return tempElementType;
		}
		public void setTempElementType(String tempElementType) {
			this.tempElementType = tempElementType;
		}
	String tempElementTypeId;
		@Hidden
		public String getTempElementTypeId() {
			return tempElementTypeId;
		}
		public void setTempElementTypeId(String tempElementTypeId) {
			this.tempElementTypeId = tempElementTypeId;
		}

	String tempConditionArray;
		@Hidden
		public String getTempConditionArray() {
			return tempConditionArray;
		}
		public void setTempConditionArray(String tempConditionArray) {
			this.tempConditionArray = tempConditionArray;
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
		ConditionPanel conditionPanel = new ConditionPanel();
		conditionPanel.setMetaworksContext(new MetaworksContext());
		conditionPanel.getMetaworksContext().setWhen("edit");
		String conditionString = null;
		if( conditionMap != null ){
			conditionString = conditionMap.get(this.getTempElementId());
		}else if( tempConditionArray != null && !tempConditionArray.equals("[]")){
			conditionString = tempConditionArray.toString();
		}
		
		conditionPanel.setRoleList(defineTab.rolePanel.getRoles());
		conditionPanel.setPrcsValiableList(defineTab.prcsValiablePanel.getPrcsValiables());
		
		conditionPanel.setConditionString(conditionString);
		conditionPanel.setConditionId(this.getTempElementId());
		conditionPanel.setConditionLabel(this.getTempElementName());
		conditionPanel.load();
		return new ModalWindow(conditionPanel , 600, 450,  "조건분기" );
	}
	@ServiceMethod(callByContent=true)
	public PrcsValiablePanel addValiable() throws Exception{
		ArrayList<PrcsValiable> prcsValiable = defineTab.prcsValiablePanel.getPrcsValiables();
		PrcsValiable designerValiable = new PrcsValiable();
		designerValiable.setName(tempElementName);
		designerValiable.setTypeId(tempElementTypeId);
		if( tempElementType != null && "wfNode".equals(tempElementType) ){
			designerValiable.getDataType().setSelected("knowledgelType");		// TODO 임시 셋팅
		}else if( tempElementType != null && "class".equals(tempElementType) ){
			designerValiable.getDataType().setSelected("complexType");
		}
		prcsValiable.add(designerValiable);
		defineTab.prcsValiablePanel.setPrcsValiables(prcsValiable);
		
		return defineTab.prcsValiablePanel;
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
			Role[] roles = new Role[1];
					// default role
					Role initiator = new Role();
					initiator.setName("initiator");
					roles[0] = initiator;
			Activity[] ac = new Activity[0];
			
			if( title == null ){
				throw new Exception("title is null. please set process title");
			}
			def.setName(title);
			def.setRoles(roles);
			def.setChildActivities(ac);
			// 변수정의
			ProcessVariable pvs[] = makeProcessValiable();
			if( pvs != null ){
				def.setProcessVariables(pvs);
			}
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
            int tracingCnt = 0;
            while (iterator.hasNext()) {
				CanvasDTO cv = iterator.next();
				
				if( "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
					// 엑티비티 생성
					GeomShape geom = new GeomShape(cv);
					geom.setPvs(pvs);
					if( cv.getParent() != null ){
						String groupId = geom.getParent();
						CanvasDTO groupCanvas = getCanvasMap().get(groupId);
						Role role = new Role();
						role.setName(groupCanvas.getLabel());
						geom.setRole(role);
						def.addRole(role);
					}else{
						geom.setRole(initiator);
					}
					Activity activity = geom.makeActivity();
					if ( activity != null ){
						activity.setTracingTag(  String.valueOf(tracingCnt++) );
						activityMap.put(cv.getId() , activity);
					}
				}else if( "GROUP".equalsIgnoreCase(cv.getShapeType()) ){
				}else if( "EDGE".equalsIgnoreCase(cv.getShapeType()) ){
					// 조건저장
					LineShape line = new LineShape(cv);
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
					Activity fromAct = activityMap.get(fromId);
					Activity toAct = activityMap.get(toId);
					if( fromAct != null && toAct != null){
						Transition ts = new Transition(fromAct.getTracingTag()  , toAct.getTracingTag() );
						// 트렌지션 생성
						def.addTransition(ts);
					}
					if( fromAct != null && fromAct instanceof SwitchActivity){
						// 스위치 엑티비티의 조건 분기 컨디션 설정
						SwitchActivity switchActivity = (SwitchActivity)fromAct;
						LineShape line = new LineShape(cv);
						Condition conditionNode = line.makeCondition();
						Condition[] conditions ;
						int index = 0;
						if( switchActivity.getConditions() != null){
							Condition[] cond = switchActivity.getConditions();
							conditions = new Condition[cond.length+1];
							System.arraycopy(cond, 0, conditions, 0, cond.length);
							index = cond.length;
						}else{
							conditions = new Condition[1];
							index = 0;
						}
						// childActivity 와 condition 의 index를 동일하게 맞추어준다.
						switchActivity.addChildActivity(toAct, index);
						conditions[index] = conditionNode;
						switchActivity.setConditions(conditions);
					}
				}
            }
			CanvasDTO jsonString = new CanvasDTO();
			jsonString.setJsonString(graphString);
			cells.add(jsonString);
			Collection<Activity> coll = activityMap.values();
	        Iterator<Activity> iter = coll.iterator();
	        while(iter.hasNext()){
	        	def.addChildActivity(iter.next());
	        }
			def.setExtendedAttribute( "cells", cells );
			
		}
		
		processManager.addProcessDefinition( title , 0, "description", false, GlobalContext.serialize(def, ProcessDefinition.class), "", "/"+title+".process2", "/"+title+".process2", "process2");
		
	}
	
	public ProcessVariable[] makeProcessValiable() throws Exception{
		ProcessVariable pvs[] = null;
		ArrayList<PrcsValiable> prcsValiable = defineTab.prcsValiablePanel.getPrcsValiables();
		if( prcsValiable != null && prcsValiable.size() > 0 ){
			pvs = new ProcessVariable[prcsValiable.size()];
			for (int i = 0; i < prcsValiable.size(); i++ ) {
				PrcsValiable prcsv = prcsValiable.get(i);
				String nameAttr = prcsv.getName();
				String typeIdAttr = prcsv.getTypeId();
				String typeAttr = prcsv.getDataType().getSelected();
				
				TextContext text = new TextContext();
				text.setText(nameAttr);

				ProcessVariable pv = new ProcessVariable();
				pv.setName(nameAttr);
				pv.setDisplayName(text);
				if( "complexType".equals(typeAttr)){
					ComplexType complexType = new ComplexType();
					complexType.setTypeId("["+typeIdAttr+"]");
					
					pv.setType(complexType.getClass());
					pv.setDefaultValue(complexType);
				}
				pvs[i] = pv;
			}
			return pvs;
		}
		return null;
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
				ArrayList<org.uengine.codi.mw3.webProcessDesigner.Role> role = defineTab.rolePanel.getRoles();
				for(int i =0 ; i < roles.length; i++){
					org.uengine.codi.mw3.webProcessDesigner.Role designerRole = new org.uengine.codi.mw3.webProcessDesigner.Role();
					designerRole.setName(roles[i].getName());
					designerRole.setMetaworksContext(new MetaworksContext());
					designerRole.getMetaworksContext().setWhen("view");
					role.add(designerRole);
				}
				defineTab.rolePanel.setRoles(role);
			}
			ProcessVariable pvs[] = def.getProcessVariables();
			if( pvs != null && pvs.length != 0){
				ArrayList<PrcsValiable> prcsValiable = defineTab.prcsValiablePanel.getPrcsValiables();
				for(int i =0 ; i < pvs.length; i++){
					ProcessVariable pv = pvs[i];
					
					PrcsValiable designerValiable = new PrcsValiable();
					designerValiable.setName(pv.getName());
					
					if( pv.getDefaultValue() instanceof ComplexType ){
						ComplexType v = (ComplexType)pv.getDefaultValue();
						designerValiable.getDataType().setSelected("complexType");
						designerValiable.setTypeId(v.getTypeId().replace("[", "").replace("]", ""));
					}else if( pv.getDefaultValue() instanceof String ){
						designerValiable.getDataType().setSelected("string");
					}else if( pv.getDefaultValue() instanceof Number ){
						designerValiable.getDataType().setSelected("number");
					}// TODO others
					
					prcsValiable.add(designerValiable);
				}
				defineTab.prcsValiablePanel.setPrcsValiables(prcsValiable);
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
