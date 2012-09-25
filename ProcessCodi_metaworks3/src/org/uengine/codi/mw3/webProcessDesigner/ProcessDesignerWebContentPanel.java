package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.HumanActivity;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.Role;
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
	
	ProcessDesignerCanvas cell[];
		public ProcessDesignerCanvas[] getCell() {
			return cell;
		}
		public void setCell(ProcessDesignerCanvas[] cell) {
			this.cell = cell;
		}
		
	String graphString;
		public String getGraphString() {
			return graphString;
		}
		public void setGraphString(String graphString) {
			this.graphString = graphString;
		}
		
	@Hidden
	String processName;
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
		
	@ServiceMethod(callByContent=true)
	public void save(String title) throws Exception{
		ArrayList<ProcessDesignerCanvas> cells = new ArrayList<ProcessDesignerCanvas>();
		ProcessDefinition def = new ProcessDefinition();
		if( cell != null){
			Role[] roles = new Role[0];
			Activity[] ac = new Activity[0];
			if( title == null ){
				throw new Exception("please set process title");
			}
			def.setName(title);
			def.setRoles(roles);
			def.setChildActivities(ac);
			for(int i = 0; i < cell.length; i++){
				ProcessDesignerCanvas cv = cell[i];
				Activity activity = addActivity(cv);
				cells.add(cell[i]);
				if( activity != null ){
					if( activity instanceof HumanActivity){
						// 롤 정의가 먼저 필요할듯
						def.addRole(((HumanActivity)activity).getRole());
					}
					def.addChildActivity(activity);
				}
			}
			
			ProcessDesignerCanvas jsonString = new ProcessDesignerCanvas();
			jsonString.setJsonString(graphString);
			cells.add(jsonString);
			
			def.setExtendedAttribute( "cells", cells );
		}
		
		processManager.addProcessDefinition( title , 0, "description", false, GlobalContext.serialize(def, ProcessDefinition.class), "", "/"+title+".process2", "/"+title+".process2", "process2");
		
	}
	
	public Activity addActivity(ProcessDesignerCanvas cv)  throws Exception{
		
		if( cv != null && "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
			// 휴먼 엑티비티 추가 TODO 여러가지 상황에 맞추어서 추후 변경됨
//			HumanActivity humanActivity = null;
			KnowledgeActivity KnowledgeActivity = null;
			if(cv.getData() != null){
				String data = unescape(cv.getData());
				JSONArray jsonArray = (JSONArray)JSONSerializer.toJSON(data);
				if( jsonArray != null && jsonArray.size() > 0){
					KnowledgeActivity = new KnowledgeActivity();
					for( int i = 0; i < jsonArray.size() ; i++){
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);
						
						String customName = jsonObj.getString("customName");
						String customType = jsonObj.getString("customType");
						
						if( customType != null && "wfNode".equalsIgnoreCase(customType)){
							String customId = jsonObj.getString("customId");
							KnowledgeActivity.setName(customName);
							KnowledgeActivity.setKnolNodeId(customId);
							
						}else 	if( customType != null && "role".equalsIgnoreCase(customType) ){
							Role role = new Role();
							role.setName(customName);
							KnowledgeActivity.setRole(role);
							
						}
						
					}
				}
				return KnowledgeActivity;
			}
			
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
			ArrayList<ProcessDesignerCanvas> cellsList = (ArrayList<ProcessDesignerCanvas>) def.getExtendedAttributes().get("cells");
			if( cellsList != null){
				ProcessDesignerCanvas []cells = new ProcessDesignerCanvas[cellsList.size()];
				for(int i = 0; i < cellsList.size(); i++){
					cells[i] = (ProcessDesignerCanvas)cellsList.get(i);
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
					role.add(designerRole);
				}
				rolePanel.setRoles(role);
			}
			// bao.toString() xml 로 받은 데이터를 json 객체로 변환시켜줌
//			ArrayList<ProcessDesignerCanvas> cellsList = (ArrayList<ProcessDesignerCanvas>) GlobalContext.deserialize(bao.toString());
//			if( cellsList != null){
//				ProcessDesignerCanvas []cells = new ProcessDesignerCanvas[cellsList.size()];
//				for(int i = 0; i < cellsList.size(); i++){
//					cells[i] = (ProcessDesignerCanvas)cellsList.get(i);
//					if( cells[i] != null && cells[i].getJsonString() != null){
//						this.setGraphString(cells[i].getJsonString());
//					}
//				}
//				this.setCell(cells);
//			}
			
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
