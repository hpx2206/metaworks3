package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.activitytypes.KnowledgeActivity;
import org.uengine.codi.mw3.CodiClassLoader;
import org.uengine.codi.mw3.knowledge.WfNode;
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
		
	@ServiceMethod(callByContent=true)
	public Object[] save() throws Exception{
		ArrayList<ProcessDesignerCanvas> cells = new ArrayList<ProcessDesignerCanvas>();
		ProcessDefinition def = new ProcessDefinition();
		if( cell != null){
			Role[] roles = new Role[0];
			Activity[] ac = new Activity[0];
			def.setName("테스트 프로세스");
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
		
		processManager.addProcessDefinition("테스트프로세스", 0, "description", false, GlobalContext.serialize(def, ProcessDefinition.class), "", "test/p22.process2", "test/p22.process2", "process2");
		
		return null;
	}
	
	public Activity addActivity(ProcessDesignerCanvas cv)  throws Exception{
		
		if( cv != null && "GEOM".equalsIgnoreCase(cv.getShapeType()) ){
			// 휴먼 엑티비티 추가 TODO 여러가지 상황에 맞추어서 추후 변경됨
//			HumanActivity humanActivity = null;
			KnowledgeActivity KnowledgeActivity = null;
			if(cv.getData() != null){
				// escape 처리가 되어있기 때문에 decode 하여 사용 TODO 현재 한글깨짐
				String data = URLDecoder.decode(cv.getData(), "UTF-8");
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
		
		/// read source file
		File sourceCodeFile = new File(CodiClassLoader.getMyClassLoader().sourceCodeBase() + "/" + getAlias().substring(0, getAlias().indexOf(".")) + ".process2");
		
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		FileInputStream is;
		try {
			is = new FileInputStream(sourceCodeFile);
			UEngineUtil.copyStream(is, bao);
			ProcessDefinition def = (ProcessDefinition) GlobalContext.deserialize(bao.toString());
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
	
	@Autowired
	public ProcessManagerRemote processManager;
}
