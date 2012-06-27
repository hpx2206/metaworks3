package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.metaworks.Refresh;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.knowledge.WfNode;
import org.uengine.kernel.RoleMapping;
import org.uengine.processmanager.ProcessManagerRemote;

//@Face(displayName="답글")
public class CommentWorkItem extends WorkItem{
	
	public CommentWorkItem(){
		setType("comment");
	}

	@Hidden(on=false)
	@Range(size = 80)
	@Face(displayName = "")
	public String getTitle() {
		return super.getTitle();
	}

	@Test(scenario="first", starter=true, instruction="$first.Add", next="autowiredObject.org.uengine.codi.mw3.model.InstanceView.monitor()")
	@ServiceMethod(callByContent = true, target="popup")
	public Object[] add() throws Exception {
		
		if(getActivityAppAlias()!=null){
			//org.uengine.kernel.ProcessDefinition processDefinition = processManager.getProcessDefinition(getActivityAppAlias());
			
			StringTokenizer tokenizer = new StringTokenizer(title);
			String connector = null;
			
			StringBuffer generatedTitle = new StringBuffer();

			
			int substringDelimiter = 0;
			for(ParameterValue pv : getParameters()){
				
				connector = tokenizer.nextToken("$").substring(substringDelimiter);
				tokenizer.nextToken(">");
				
				generatedTitle.append(connector).append(pv.getValueObject());
				
				substringDelimiter=1;
				
			}
			
			title = generatedTitle.append(tokenizer.nextElement()).toString();
			
			String instId = processManager.initializeProcess(getActivityAppAlias(), getTitle());
			
			for(ParameterValue pv : getParameters()){
				processManager.setProcessVariable(instId, "", pv.getVariableName(), (Serializable)pv.getValueObject());
			}
			
			RoleMapping rm = RoleMapping.create();
			if(session.user!=null)
				rm.setEndpoint(session.user.getUserId());
			
			processManager.setLoggedRoleMapping(rm);

			
			processManager.executeProcess(instId);
			
			processManager.applyChanges();
			
			Instance instanceRef = new Instance();
			instanceRef.setInstId(new Long(instId));
			
			if(!isInstantiation()){ //means sub process
				instanceRef.setRootInstId(getInstId());
				
				Instance rootInstance = new Instance();
				rootInstance.setInstId(instanceRef.getRootInstId());
				
				instanceRef = rootInstance;
			}
			
			
			instanceViewContent.session = session;
			instanceViewContent.load(instanceRef);
			
			
			WfNode parent = afterInstantiation(instanceViewContent, instanceRef);
			
			return new Object[]{ new Refresh(instanceViewContent), new Refresh(parent)};
		}
		
		// TODO Auto-generated method stub
		return super.add();
	}
	
	@Autowired
	public InstanceViewContent instanceViewContent;

	@Test(scenario="first", starter=true, instruction="$first.NewActivity", next="autowiredObject.org.uengine.codi.mw3.model.IProcessMap@IssueManagement.process.initiate()")
	public Popup newActivity() throws Exception {
		// TODO Auto-generated method stub
		return super.newActivity();
	}

	String activityAppAlias;

		public String getActivityAppAlias() {
			return activityAppAlias;
		}
	
		public void setActivityAppAlias(String activityAppAlias) {
			this.activityAppAlias = activityAppAlias;
		}
		
	ArrayList<ParameterValue> parameters;
	
		public ArrayList<ParameterValue> getParameters() {
			return parameters;
		}
	
		public void setParameters(ArrayList<ParameterValue> parameters) {
			this.parameters = parameters;
		} 
	
}
