package org.uengine.codi.mw3.webProcessDesigner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Vector;

import org.metaworks.MetaworksContext;
import org.uengine.codi.mw3.ide.editor.valuechain.ValueChainEditor;
import org.uengine.codi.mw3.knowledge.TopicMapping;
import org.uengine.codi.mw3.knowledge.TopicNode;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.contexts.TextContext;
import org.uengine.kernel.Activity;
import org.uengine.kernel.GlobalContext;
import org.uengine.kernel.ProcessDefinition;
import org.uengine.kernel.Role;
import org.uengine.kernel.ValueChain;
import org.uengine.kernel.ValueChainDefinition;
import org.uengine.util.UEngineUtil;

public class ValueChainDesignerContentPanel extends ProcessDesignerContentPanel{

	public ValueChainDesignerContentPanel() throws Exception {
		
		super();
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setHow("valuechain");
	}
	
	@Override
	public String load(String definitionString) throws Exception{
		ValueChainDefinition def = (ValueChainDefinition) GlobalContext.deserialize(definitionString);
		this.processDesignerContainer.setEditorId(alias);
		this.processDesignerContainer.loadValueChain(def);
		
		rolePanel = processDesignerContainer.getRolePanel();
		processVariablePanel = processDesignerContainer.getProcessVariablePanel();
		
		processNameView.setFileId(alias);
		processNameView.setAlias(def.getName().getText());
		
		return def.getProcessDesignerInstanceId();
	}
		
	public void saveMe(ValueChainEditor valueChainEditor) throws Exception{
		ValueChainDefinition def = processDesignerContainer.containerToValueChainDefinition(processDesignerContainer);
		
		Vector<ValueChain> list = def.getChildValueChains();
		ArrayList<String> processPathList = new ArrayList<String>();
		for(int i = 0; i < list.size(); i++){
			ValueChain vc = list.get(i);
			MajorProcessDefinitionNode mpd = vc.getMajorProcessDefinitionNode();
			mpd.findChildProcess(processPathList, mpd.getChild());
		}
		
		
		if( processPathList.size() > 0 ){
			for(String path : processPathList){
				ProcessDesignerContentPanel processDesignerContentPanel = new ProcessDesignerContentPanel();
				processDesignerContentPanel.setAlias(path);
				processDesignerContentPanel.setUseClassLoader(true);
				processDesignerContentPanel.load();
				
				String name = processDesignerContentPanel.getProcessNameView().getAlias();
				String type = "process";
				
				
				// processTopicMapping - name으로 topicID가져옴.
				// null 이면 metaworksContext - new
				// null 아니면 metaworksContext - edit
				ProcessTopicMapping ptm = new ProcessTopicMapping();
				ptm.setProcessName(name);
				IProcessTopicMapping findptm = ptm.findByName();
				if(findptm!=null){
					this.getMetaworksContext().setWhen(metaworksContext.WHEN_EDIT);
				}else{
					this.getMetaworksContext().setWhen(metaworksContext.WHEN_NEW);
				}
				//saveTopic("process", path, name);
				
				if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){//new일때
					WfNode wfNode = new WfNode();
					wfNode.setName(name);
					wfNode.setType(type);
					wfNode.setParentId(session.getCompany().getComCode());	
					wfNode.setAuthorId(session.getUser().getUserId());		
					wfNode.setCompanyId(session.getCompany().getComCode());
					wfNode.createMe();
					
					String parentId = wfNode.getId();
	
			
					
					ProcessTopicMapping processTopicMapping = new ProcessTopicMapping();
					processTopicMapping.setProcessPath(path);
					processTopicMapping.setProcessName(name);
					processTopicMapping.setType(type);
					processTopicMapping.setTopicId(wfNode.getId());
					processTopicMapping.createDatabaseMe();
					processTopicMapping.flushDatabaseMe();
					
					ProcessDesignerContainer processDesignerContainer = processDesignerContentPanel.getProcessDesignerContainer();
					ArrayList<Role> roleList = processDesignerContentPanel.processDesignerContainer.getRoleList();
					for(Role role : roleList){
						if( "Initiator".equalsIgnoreCase(role.getName()) ){
							continue;
						}
						
						TopicMapping tm = new TopicMapping();
						tm.setTopicId(wfNode.getId());
						tm.setUserId(role.getName());
						tm.setUserName(role.getDisplayName().getText());
						tm.setAssigntype(5);
						tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
						
						tm.saveMe();
						tm.flushDatabaseMe();
					}
					
				
					
					
					ArrayList<Activity> activityList = processDesignerContentPanel.processDesignerContainer.getActivityList();
					for(Activity activity : activityList){
						name = null;
						name = activity.getDescription().getText();
						type = "activity";
						//name += "[" + activity.getTracingTag() + "]";
						
						wfNode = null;
						wfNode = new WfNode();
						wfNode.setName(name);
						wfNode.setType(type);
						wfNode.setParentId(parentId);	
						wfNode.setAuthorId(session.getUser().getUserId());		
						wfNode.setCompanyId(session.getCompany().getComCode());
						wfNode.createMe();
	
						
						processTopicMapping = null;
						processTopicMapping = new ProcessTopicMapping();
						processTopicMapping.setProcessPath(path);
						processTopicMapping.setProcessName(name);
						processTopicMapping.setType(type);
						processTopicMapping.setTopicId(wfNode.getId());
						processTopicMapping.createDatabaseMe();
						processTopicMapping.flushDatabaseMe();
						
						processDesignerContainer = null;
						roleList = null;
						processDesignerContainer = processDesignerContentPanel.getProcessDesignerContainer();
						roleList = processDesignerContainer.getRoleList();
						for(Role role : roleList){
							if( "Initiator".equalsIgnoreCase(role.getName()) ){
								continue;
							}
							
							TopicMapping tm = new TopicMapping();
							tm.setTopicId(wfNode.getId());
							tm.setUserId(role.getName());
							tm.setUserName(role.getDisplayName().getText());
							tm.setAssigntype(5);
							tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
							
							tm.saveMe();
							tm.flushDatabaseMe();
						}
						
						
					}
				}else{//edit일때
					/*
					 * TODO case: edit
					 */
				}
				
			}
		}
		
		TextContext text = new TextContext();
		text.setText(this.getProcessName());
		def.setName(text);
		if( valueChainEditor.getProcessDesignerInstanceId() != null ){
			def.setProcessDesignerInstanceId(valueChainEditor.getProcessDesignerInstanceId());
		}
		FileOutputStream fos = null;
		try{
			File file = new File(valueChainEditor.getResourceNode().getPath());
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
	
	public void saveTopic(String type, String path, String name) throws Exception{
		
		if(MetaworksContext.WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			
			WfNode wfNode = new WfNode();
			wfNode.setName(name);
			wfNode.setType(type);
			wfNode.setParentId(session.getCompany().getComCode());	
			wfNode.setAuthorId(session.getUser().getUserId());		
			wfNode.setCompanyId(session.getCompany().getComCode());
			wfNode.createMe();
			
			
			
			ProcessTopicMapping processTopicMapping = new ProcessTopicMapping();
			processTopicMapping.setProcessPath(path);
			processTopicMapping.setProcessName(name);
			processTopicMapping.setType(type);
			processTopicMapping.setTopicId(wfNode.getId());
			processTopicMapping.createDatabaseMe();
			processTopicMapping.flushDatabaseMe();
			
			
			

//			ProcessDesignerContainer processDesignerContainer = processDesignerContentPanel.getProcessDesignerContainer();
//			ArrayList<Role> roleList = processDesignerContainer.getRoleList();
//			for(Role role : roleList){
//				if( "Initiator".equalsIgnoreCase(role.getName()) ){
//					continue;
//				}
//				
//				tm = new TopicMapping();
//				tm.setTopicId(wfNode.getId());
//				tm.setUserId(session.getUser().getUserId());
//				tm.setUserName(session.getUser().getName());
//				tm.getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
//				
//				tm.saveMe();
//				tm.flushDatabaseMe();
//			}
			
			
		}else{
//			wfNode.setId(this.getTopicId());
//			
//			wfNode.copyFrom(wfNode.databaseMe());
//			wfNode.setSecuopt(topicSecuopt ? "1" : "0");
//			
//			if(this.getLogoFile().getUploadedPath() != null && this.getLogoFile().getFilename() != null){
//				wfNode.setUrl(this.getLogoFile().getUploadedPath());
//				wfNode.setThumbnail(this.getLogoFile().getFilename());
//			}
//			
//			wfNode.setName(this.getTopicTitle());
//			wfNode.saveMe();
		}
	}
	
}
