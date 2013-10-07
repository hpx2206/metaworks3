package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.ide.editor.process.ProcessEditor;
import org.uengine.codi.mw3.model.ContentWindow;
import org.uengine.codi.mw3.model.Session;
import org.uengine.codi.mw3.processexplorer.ProcessNameView;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.ProcessVariable;
import org.uengine.processmanager.ProcessManagerRemote;
import org.uengine.util.UEngineUtil;

public class ProcessDesignerContentPanel extends ContentWindow implements ContextAware {
	
	public ProcessDesignerContentPanel() throws Exception{
		processDesignerContainer = new ProcessDesignerContainer();
		processNameView = new ProcessNameView();
		processNameView.setMetaworksContext(new MetaworksContext());
		processNameView.getMetaworksContext().setHow("nameChange");
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
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
	ProcessDesignerContainer processDesignerContainer;
		public ProcessDesignerContainer getProcessDesignerContainer() {
			return processDesignerContainer;
		}
		public void setProcessDesignerContainer(
				ProcessDesignerContainer processDesignerContainer) {
			this.processDesignerContainer = processDesignerContainer;
		}

	String processName;
		@Hidden
		public String getProcessName() {
			return processName;
		}
		public void setProcessName(String processName) {
			this.processName = processName;
		}
		
	String basePath;
	@Hidden
		public String getBasePath() {
			return basePath;
		}
		public void setBasePath(String basePath) {
			this.basePath = basePath;
		}
		
	ProcessNameView processNameView;
		public ProcessNameView getProcessNameView() {
			return processNameView;
		}
		public void setProcessNameView(ProcessNameView processNameView) {
			this.processNameView = processNameView;
		}	
	
	public void saveMe(ProcessEditor processEditor) throws Exception{
		ProcessDefinition def = processDesignerContainer.containerToDefinition(processDesignerContainer);
		
		
//		ArrayList<CanvasDTO> cells = new ArrayList<CanvasDTO>();
//		ProcessDefinition def = new ProcessDefinition();
		/*
		if( cell != null){
			Role[] roles = new Role[1];
					// default role
					Role initiator = new Role();
					initiator.setName("Initiator");
					roles[0] = initiator;
			Activity[] ac = new Activity[0];
			
			def.setRoles(roles);
			def.setChildActivities(ac);
			// 변수정의
			ProcessVariable pvs[] = makeProcessValiable();
			if( pvs != null ){
				def.setProcessVariables(pvs);
			}
			HashMap<String, CanvasDTO> canvasMap = new HashMap<String, CanvasDTO>();
//			HashMap<String, Object> activityMap = new HashMap<String, Object>();
//			for(int i = 0; i < cell.length; i++){
//				CanvasDTO cv = cell[i];
//				cells.add(cv);
//				canvasMap.put(cv.getId(), cv);
//			}
//			setCanvasMap(canvasMap);
			
			Collection<CanvasDTO> ct = canvasMap.values();
            Iterator<CanvasDTO> iterator = ct.iterator();
            while (iterator.hasNext()) {
				CanvasDTO cv = iterator.next();
				
				if( "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
					// 엑티비티 생성
					GeomShape geom = new GeomShape(cv);
					geom.setPvs(pvs);
					if(cv.getActivityClass() != null && "org.uengine.kernel.HumanActivity".equals(cv.getActivityClass()) ){
//						HumanActivity activity = (HumanActivity)activityMap.get(cv.getId());
//						activity = (HumanActivity)geom.makeProcVal(activity);
//						if( cv.getParent() != null ){
//							String groupId = geom.getParent();
//							CanvasDTO groupCanvas = getCanvasMap().get(groupId);
//							if( "OG.shape.HorizontalLaneShape".equals(groupCanvas.getShapeId()) 
//									|| "OG.shape.VerticalLaneShape".equals(groupCanvas.getShapeId())){
//								Role role = (Role)getRoleMap().get(groupId);
//								activity.setRole(role);
//							}
//						}else{
//							// 만약 휴먼엑티비티에  role 이 셋팅이 안되어 있다면 default role 로 initator 로 셋팅을 해준다.(서브프로세스의 role셋팅을 위하여)
//							activity.setRole(initiator);
//						}
					}
				}else if( "GROUP".equalsIgnoreCase(cv.getShapeType()) ){
					// 서브프로세스
					if(cv.getActivityClass() != null && "org.uengine.kernel.SubProcessActivity".equals(cv.getActivityClass()) ){
//						SubProcessActivity activity = (SubProcessActivity)activityMap.get(cv.getId());
//						Role role = (Role)getRoleMap().get(cv.getParent());
//						
//						RoleParameterContext[] roleBindings = new RoleParameterContext[1];
//						roleBindings[0] = new RoleParameterContext();
//						roleBindings[0].setRole(role);
//						// 서브프로세스 안쪽의 role 은 무조건 initiator 로 통일 시킨다.
//						roleBindings[0].setArgument(initiator.getName());
//						activity.setRoleBindings(roleBindings);
					}
					
				}else if( "EDGE".equalsIgnoreCase(cv.getShapeType()) ){
//					String formStr = cv.getFrom();
//					String toStr = cv.getTo();
//					String fromId = formStr.substring(0, formStr.indexOf("_TERMINAL"));
//					String toId = toStr.substring(0, toStr.indexOf("_TERMINAL"));
//					Activity fromAct = (Activity)activityMap.get(fromId);
//					Activity toAct = (Activity)activityMap.get(toId);
//					if( fromAct != null && toAct != null){
//						// 트렌지션 생성
//						Transition ts = new Transition(fromAct.getTracingTag()  , toAct.getTracingTag() );
//						// 컨디션 생성
//						if( this.getConditionMap().containsKey(cv.getId())){
//							ts.setTransitionId(cv.getId());
//							ts.setCondition(this.getConditionMap().get(cv.getId()));
//						}
//						
//						def.addTransition(ts);
//					}
				}
			}
            
//			CanvasDTO jsonString = new CanvasDTO();
//			jsonString.setJsonString(graphString);
//			cells.add(jsonString);
			 // def 에 Activity 셋팅
//			Collection<Object> coll = activityMap.values();
//	        Iterator<Object> iter = coll.iterator();
//	        while(iter.hasNext()){
//	        	Object act = iter.next();
//	        	if( act instanceof Activity){
//	        		def.addChildActivity((Activity)act);
//	        	}
//	        }
//	        // def 에 Role 셋팅
//	        Collection<Object> collRole = roleMap.values();
//	        Iterator<Object> iterRole = collRole.iterator();
//	        while(iterRole.hasNext()){
//	        	Object act = iterRole.next();
//	        	if( act instanceof Role){
//	        		def.addRole((Role)act);
//	        	}
//	        }
//			def.setExtendedAttribute( "cells", cells );
			
		}
		*/
			TextContext text = new TextContext();
			text.setText(this.getProcessName());
			def.setName(text);
			if( processEditor.getProcessDesignerInstanceId() != null ){
				def.setProcessDesignerInstanceId(processEditor.getProcessDesignerInstanceId());
			}
			FileOutputStream fos = null;
			try{
				File file = new File(processEditor.getResourceNode().getPath());
				fos = new FileOutputStream(file);
				String definitionInString = (String)GlobalContext.serialize(def, ProcessDefinition.class);
				ByteArrayInputStream bai = new ByteArrayInputStream(definitionInString.getBytes(GlobalContext.ENCODING));
				UEngineUtil.copyStream(bai, fos);
			} catch (Exception e) {
				throw e;//e.printStackTrace();
			} finally{
				if(fos!=null)
					fos.close();
			}
	}
	
	@ServiceMethod(callByContent=true)
	public void save(String title, boolean temp) throws Exception{
		/*
		ArrayList<CanvasDTO> cells = new ArrayList<CanvasDTO>();
		ProcessDefinition def = new ProcessDefinition();
		if( cell != null){
			Role[] roles = new Role[1];
					// default role
					Role initiator = new Role();
					initiator.setName("Initiator");
					roles[0] = initiator;
			Activity[] ac = new Activity[0];
			
			def.setRoles(roles);
			def.setChildActivities(ac);
			// 변수정의
			ProcessVariable pvs[] = makeProcessValiable();
			if( pvs != null ){
				def.setProcessVariables(pvs);
			}
			HashMap<String, CanvasDTO> canvasMap = new HashMap<String, CanvasDTO>();
//			HashMap<String, Object> activityMap = new HashMap<String, Object>();
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
				
				if( "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
					// 엑티비티 생성
					GeomShape geom = new GeomShape(cv);
					geom.setPvs(pvs);
					if(cv.getClassname() != null 
							&& ( "org.uengine.kernel.HumanActivity".equals(cv.getClassname()) 
								|| "org.uengine.codi.activitytypes.KnowledgeActivity".equals(cv.getClassname()) )){
						Activity activity = (HumanActivity)activityMap.get(cv.getId());
						activity = geom.makeProcVal(activity);
						
						if( cv.getParent() != null ){
							String groupId = geom.getParent();
							CanvasDTO groupCanvas = getCanvasMap().get(groupId);
							if( "OG.shape.HorizontalLaneShape".equals(groupCanvas.getShapeId()) 
									|| "OG.shape.VerticalLaneShape".equals(groupCanvas.getShapeId())){
								Role role = (Role)getRoleMap().get(groupId);
								((HumanActivity)activity).setRole(role);
							}
						}else{
							// 만약 휴먼엑티비티에  role 이 셋팅이 안되어 있다면 default role 로 initator 로 셋팅을 해준다.(서브프로세스의 role셋팅을 위하여)
							((HumanActivity)activity).setRole(initiator);
						}
						activityMap.put(cv.getId(), activity);
					}
				}else if( "GROUP".equalsIgnoreCase(cv.getShapeType()) ){
					// 서브프로세스
					if(cv.getClassname() != null && "org.uengine.kernel.SubProcessActivity".equals(cv.getClassname()) ){
						SubProcessActivity activity = (SubProcessActivity)activityMap.get(cv.getId());
						Role role = (Role)getRoleMap().get(cv.getParent());
						
						RoleParameterContext[] roleBindings = new RoleParameterContext[1];
						roleBindings[0] = new RoleParameterContext();
						roleBindings[0].setRole(role);
						// 서브프로세스 안쪽의 role 은 무조건 initiator 로 통일 시킨다.
						roleBindings[0].setArgument(initiator.getName());
						activity.setRoleBindings(roleBindings);
					}
					
				}else if( "EDGE".equalsIgnoreCase(cv.getShapeType()) ){
					String formStr = cv.getFrom();
					String toStr = cv.getTo();
					String fromId = formStr.substring(0, formStr.indexOf("_TERMINAL"));
					String toId = toStr.substring(0, toStr.indexOf("_TERMINAL"));
					Activity fromAct = (Activity)activityMap.get(fromId);
					Activity toAct = (Activity)activityMap.get(toId);
					if( fromAct != null && toAct != null){
						// 트렌지션 생성
						Transition ts = new Transition(fromAct.getTracingTag()  , toAct.getTracingTag() );
						// 컨디션 생성
						if( this.getConditionMap().containsKey(cv.getId())){
							ts.setTransitionId(cv.getId());
							ts.setCondition(this.getConditionMap().get(cv.getId()));
						}
						
						def.addTransition(ts);
					}
				}
			}
            
			CanvasDTO jsonString = new CanvasDTO();
			jsonString.setJsonString(graphString);
			cells.add(jsonString);
			 // def 에 Activity 셋팅
			Collection<Object> coll = activityMap.values();
	        Iterator<Object> iter = coll.iterator();
	        while(iter.hasNext()){
	        	Object act = iter.next();
	        	if( act instanceof Activity){
	        		def.addChildActivity((Activity)act);
	        	}
	        }
	        // def 에 Role 셋팅
	        Collection<Object> collRole = roleMap.values();
	        Iterator<Object> iterRole = collRole.iterator();
	        while(iterRole.hasNext()){
	        	Object act = iterRole.next();
	        	if( act instanceof Role){
	        		def.addRole((Role)act);
	        	}
	        }
//			def.setExtendedAttribute( "cells", cells );
			
		}
		if( temp ){
			if( title == null ){
				throw new Exception("title is null. please set process title");
			}
			def.setName(title);
			processManager.addProcessDefinition( title , 0, "description", false, GlobalContext.serialize(def, ProcessDefinition.class), "", "/"+title+".process2", title+".process2", "process2");
		}
		*/
	}
//	public Role[] makeRole()  throws Exception{
//		ArrayList<org.uengine.codi.mw3.webProcessDesigner.Role> RoleList = defineTab.getRolePanel().getRoles();
//		Role[] roles = null;
//		
//		return roles;
//	}
	public ProcessVariable[] makeProcessValiable() throws Exception{
		/*
		Collection<Object> collVariable = variableMap.values();
		ProcessVariable pvs[] = new ProcessVariable[collVariable.size()];
        Iterator<Object> iterVariable = collVariable.iterator();
        int i = 0;
        while(iterVariable.hasNext()){
        	PrcsVariable prcsv = (PrcsVariable)iterVariable.next();
			String nameAttr = prcsv.getName();
			String typeIdAttr = prcsv.getTypeId();
			String typeAttr = prcsv.getVariableType();
			
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
			}else if("string".equals(typeAttr)){
				pv.setType(String.class);
			}
			pvs[i] = pv;
			i++;
        }
        return pvs;
        */
		return null;
//		ArrayList<PrcsVariable> prcsValiable = defineTab.prcsValiablePanel.getPrcsValiables();
//		if( prcsValiable != null && prcsValiable.size() > 0 ){
//			pvs = new ProcessVariable[prcsValiable.size()];
//			for (int i = 0; i < prcsValiable.size(); i++ ) {
//				PrcsVariable prcsv = prcsValiable.get(i);
//				String nameAttr = prcsv.getName();
//				String typeIdAttr = prcsv.getTypeId();
//				String typeAttr = prcsv.getDataType().getSelected();
//				
//				TextContext text = new TextContext();
//				text.setText(nameAttr);
//
//				ProcessVariable pv = new ProcessVariable();
//				pv.setName(nameAttr);
//				pv.setDisplayName(text);
//				if( "complexType".equals(typeAttr)){
//					ComplexType complexType = new ComplexType();
//					complexType.setTypeId("["+typeIdAttr+"]");
//					
//					pv.setType(complexType.getClass());
//					pv.setDefaultValue(complexType);
//				}else if("string".equals(typeAttr)){
//					pv.setType(String.class);
//				}
//				pvs[i] = pv;
//			}
//			return pvs;
//		}
//		return null;
	}
	public String load(String definitionString) throws Exception {
		ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(definitionString);
		this.processDesignerContainer.setEditorId(alias);
		this.processDesignerContainer.load(def);
		
		processNameView.setFileId(alias);
		processNameView.setAlias(def.getName().getText());
		
		return def.getProcessDesignerInstanceId();
		
//		setDefinition(def);
		// default role
		/*
		Role initiator = new Role();
		initiator.setName("Initiator");
		roleMap.put("Initiator" , initiator);
		
		ProcessVariable pvs[] = def.getProcessVariables();
		if( pvs != null && pvs.length != 0){
			for(int i =0 ; i < pvs.length; i++){
				ProcessVariable pv = pvs[i];
				PrcsVariable designerValiable = new PrcsVariable();
				designerValiable.setName(pv.getName());
				if( pv.getDefaultValue() instanceof ComplexType ){
					ComplexType v = (ComplexType)pv.getDefaultValue();
					designerValiable.setVariableType("complexType");
					designerValiable.setTypeId(v.getTypeId().replace("[", "").replace("]", ""));
				}else if( pv.getDefaultValue() instanceof String ){
					designerValiable.setVariableType("string");
				}else if( pv.getDefaultValue() instanceof Number ){
				}// TODO others
				
				variableMap.put(pv.getName(), designerValiable);
			}
		}
		
		ProcessVariable[] procVars = def.getProcessVariables();
		if( procVars != null){
			for(int i=0; i<procVars.length; i++){
				procVars[i].setType(null);
				procVars[i].setDefaultValue(null);
				//procVars[i].setDisplayName(null);
				
				//procVars[i] = ProcessVariable.forName(procVars[i].getName());
				//def.getExtendedAttributes()
			}
		}
		
		// processDefinition setting
		
		if(def.getExtendedAttributes() != null){
			ArrayList<CanvasDTO> cellsList = (ArrayList<CanvasDTO>) def.getExtendedAttributes().get("cells");
//			activityMap = new HashMap<String, Object>();
//			activityList = new ArrayList<Activity>();
			if( cellsList != null){
				CanvasDTO []cells = new CanvasDTO[cellsList.size()];
				int tagCnt = 0;
				for(int i = 0; i < cellsList.size(); i++){
					cells[i] = (CanvasDTO)cellsList.get(i);
					if( cells[i] != null && cells[i].getJsonString() != null){
						this.setGraphString(cells[i].getJsonString());
					}
					if( cells[i].getTracingTag() != null ){
						if( "GEOM".equalsIgnoreCase(cells[i].getShapeType()) ){
							Activity activity = def.getActivity(cells[i].getTracingTag());
							activityMap.put(cells[i].getId() , activity );
						}else if( "GROUP".equalsIgnoreCase(cells[i].getShapeType()) ){
							if( "OG.shape.HorizontalLaneShape".equals(cells[i].getShapeId() ) || "OG.shape.VerticalLaneShape".equals(cells[i].getShapeId() )){
								roleMap.put(cells[i].getId() , def.getRole(cells[i].getRoleName() ));
							}else if( "OG.shape.bpmn.A_Subprocess".equals(cells[i].getShapeId() )){
								Activity activity = def.getActivity(cells[i].getTracingTag());
								activityMap.put(cells[i].getId() , activity );
							}
						}
						if( Integer.parseInt(cells[i].getTracingTag()) > tagCnt )
							tagCnt = Integer.parseInt(cells[i].getTracingTag());
					}
				}
				lastTracingTag = String.valueOf(tagCnt + 1);
				// canvas setting
				this.setCell(cells);
			}
		}
		 */
//		Role[] roles = def.getRoles();
//		if( roles != null && roles.length != 0){
////			ArrayList<org.uengine.codi.mw3.webProcessDesigner.Role> role = defineTab.rolePanel.getRoles();
//			for(int i =0 ; i < roles.length; i++){
//				org.uengine.codi.mw3.webProcessDesigner.Role designerRole = new org.uengine.codi.mw3.webProcessDesigner.Role();
//				designerRole.setName(roles[i].getName());
//				designerRole.setMetaworksContext(new MetaworksContext());
//				designerRole.getMetaworksContext().setWhen("view");
//				roleMap.put(cells[i].getId() , def.getRole(cells[i].getLabel() ));
//				role.add(designerRole);
//			}
//			// role setting
////			defineTab.rolePanel.setRoles(role);
//		}
		/*
		ArrayList<Transition> tsList = def.getTransitions();
		if( tsList != null && tsList.size()>0){
			for(int i =0 ; i < tsList.size(); i++){
				Transition ts = tsList.get(i);
				if( ts.getCondition() != null ){
//					conditionMap.put(ts.getTransitionId(), ts.getCondition());
				}
			}
		}
		*/
	}
	
	public void loadOld() throws Exception{
		
		String processName = getAlias();
		setProcessName(processName);
		/// read source file
		//File sourceCodeFile = new File(getBasePath() + getAlias());
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + processName);
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
//		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			
			this.load(bao.toString("UTF-8"));
			
			
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public void load() throws Exception{
		
		String processName = getAlias().substring(0, getAlias().indexOf("."));
		setProcessName(processName);
		/// read source file
		File sourceCodeFile = new File(getBasePath() + getAlias());
		//File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + processName + ".process2");
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
//		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			
			this.load(bao.toString("UTF-8"));
			
			
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j))
				tmp.append(j);
			else if (j < 256) {
				tmp.append("%");
				if (j < 16)
					tmp.append("0");
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
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
	
	@AutowiredFromClient
	transient public Session session;
	
}
