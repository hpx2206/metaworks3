package org.uengine.codi.mw3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.kernel.EJBProcessInstance;
import org.uengine.kernel.ProcessInstance;
import org.uengine.kernel.RoleMapping;
import org.uengine.persistence.processinstance.ProcessInstanceDAO;

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
	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] add() throws Exception {
		
		Object[] returnObjects = null;
		Long prevInstId = this.getInstId();

		// TODO: 우선 추가 모드 일때만 작업
		if(WHEN_NEW.equals(this.getMetaworksContext().getWhen())){
			// defId setting
			if(getActivityAppAlias()!=null){
				StringTokenizer tokenizer = new StringTokenizer(this.getTitle());
				String connector = null;
				
				if(getParameters()!=null){
					StringBuffer generatedTitle = new StringBuffer();
					
					int substringDelimiter = 0;
					for(ParameterValue pv : getParameters()){
						connector = tokenizer.nextToken("$").substring(substringDelimiter);
						tokenizer.nextToken(">");
						
						generatedTitle.append(connector).append(pv.getValueObject());
						
						substringDelimiter=1;
					}
					
					this.setTitle(generatedTitle.append(tokenizer.nextElement()).toString());
				}
				
				String newInstId = processManager.initializeProcess(getActivityAppAlias(), getTitle());
				
				if(getParameters()!=null){	
					for(ParameterValue pv : getParameters()){
						Serializable valueObject = (Serializable)pv.getValueObject();
						
						processManager.setProcessVariable(newInstId, "", pv.getVariableName(), valueObject);
					}
				}
				
				RoleMapping rm = RoleMapping.create();
				if(session.getUser() != null){
					rm.setName("initiator");
					rm.setEndpoint(session.getUser().getUserId());
					
					processManager.putRoleMapping(newInstId, rm);
				}
				
				processManager.setLoggedRoleMapping(rm);
				
				
				ProcessInstance instanceObject = processManager.getProcessInstance(newInstId);
				
				EJBProcessInstance ejbParentInstance = (EJBProcessInstance)instanceObject;
				ProcessInstanceDAO instanceDAO = ejbParentInstance.getProcessInstanceDAO();
				
				instanceDAO.set("initComCd", session.getCompany().getComCode());

				//means sub process
				if(this.getInstId() != null ){				
					instanceDAO.setRootInstId(new Long(getInstId()));
					this.setRootInstId(this.getInstId());
				}
				
				this.setInstId(new Long(newInstId));

				processManager.executeProcess(newInstId);
				
				instanceDAO.set("InitEp", session.getUser().getUserId());
				instanceDAO.set("InitRsNm", session.getUser().getName());

				//when newly creating process instance, there must be at least one or more rolemapping. so if there's no rolemapping has been defined by running process, add one implicitly.
				if(prevInstId == null){
					IRoleMapping roleMapping = new org.uengine.codi.mw3.model.RoleMapping().auto();
					roleMapping.setRootInstId(new Long(newInstId));
					roleMapping.select();
					
					if(!roleMapping.next()){
						processManager.putRoleMapping(newInstId, rm);
					}
				}
				
				processManager.applyChanges();
			}
		}
		

		IInstance instance = this.save();
		
		returnObjects = super.makeReturn(prevInstId, instance);
		
		return returnObjects;
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
		
		
	ArrayList<String> initialFollowers;

		public ArrayList<String> getInitialFollowers() {
			return initialFollowers;
		}
	
		public void setInitialFollowers(ArrayList<String> initialFollowers) {
			this.initialFollowers = initialFollowers;
		}


		
	ArrayList<ParameterValue> parameters;
	
		public ArrayList<ParameterValue> getParameters() {
			return parameters;
		}
	
		public void setParameters(ArrayList<ParameterValue> parameters) {
			this.parameters = parameters;
		} 
	
}
