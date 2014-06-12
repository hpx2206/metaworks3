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
import org.uengine.kernel.HumanActivity;
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
				
				if( (path.substring(0, 1)).equals(File.separator)  ){
					path = path.substring(1);
					// System.out.println(path);
				}
				
				ProcessTopicMapping processTopicMapping = new ProcessTopicMapping();
				processTopicMapping.setProcessPath(path);
				IProcessTopicMapping iptm = processTopicMapping.findByProcessPath();
				
				if( iptm == null ){
					// create
					String parentId=null;
					
					WfNode wfNode = new WfNode();
					wfNode.setName(name);
					wfNode.setType(type);
					wfNode.setParentId(session.getCompany().getComCode());
					wfNode.setAuthorId(session.getUser().getUserId());		
					wfNode.setCompanyId(session.getCompany().getComCode());
					wfNode.createMe();
					parentId = wfNode.getId();
					
					processTopicMapping.setTopicId(parentId);
					processTopicMapping.setProcessPath(path);
					processTopicMapping.setProcessName(name);
					processTopicMapping.setType(type);
					processTopicMapping.createDatabaseMe();
					processTopicMapping.flushDatabaseMe();
						
							
					ArrayList<Activity> activityList = processDesignerContentPanel.processDesignerContainer.getActivityList();
					for(Activity activity : activityList){
						if( activity instanceof HumanActivity){
							addActivityToDatabase(activity, parentId, path);
						}
					}
				}else{
					// update
					String parentId = null;
					iptm.beforeFirst();
					while(iptm.next()){
						if( "process".equals(iptm.getType()) ){
							parentId = iptm.getTopicId();
							WfNode wfNode = new WfNode();
							wfNode.setId(parentId);
							wfNode.databaseMe().setName(name);
							processTopicMapping.copyFrom(iptm);
							processTopicMapping.databaseMe().setProcessName(name);
						}
					}
					iptm.beforeFirst();
					
					ArrayList<Activity> activityList = processDesignerContentPanel.processDesignerContainer.getActivityList();
					for(Activity activity : activityList){
						boolean updataFlag = true;
						while(iptm.next()){
							if(  iptm.getProcessName().equals(activity.getDescription().getText())){
								updataFlag = false;
								break;
							}
						}
						if( updataFlag ){
							//  processTopicMapping 테이블에 현재 엑티비티와 같은 이름으로된 data가 없는 경우 activity를 넣어준다.
							addActivityToDatabase(activity, parentId, path);
						}
					}
					
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
	
	public void addActivityToDatabase(Activity activity, String parentId,  String processPath) throws Exception{
		String name = activity.getDescription().getText();
		String type = "activity";
		
		String activitytId=null;
		
		WfNode wfNode = new WfNode();
		wfNode.setName(name);
		wfNode.setType(type);
		wfNode.setParentId(parentId);	
		wfNode.setAuthorId(session.getUser().getUserId());		
		wfNode.setCompanyId(session.getCompany().getComCode());
		wfNode.createMe();
		activitytId = wfNode.getId();
		
		wfNode.flushDatabaseMe();
		
		
		ProcessTopicMapping processTopicMapping = new ProcessTopicMapping();
		processTopicMapping.setProcessPath(processPath);
		processTopicMapping.setProcessName(name);
		processTopicMapping.setType(type);
		processTopicMapping.setTopicId(activitytId);
		processTopicMapping.createDatabaseMe();
		processTopicMapping.flushDatabaseMe();
		
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
