package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;
import org.springframework.beans.factory.annotation.Autowired;

public class Instance extends Database<IInstance> implements IInstance{

	@Autowired
	InstanceViewContent instanceViewContent;
	
	//@Autowired
	//ProcessManagerRemote processManager;	

	public Instance(){
		
	}
	
	public ContentWindow detail() throws Exception{
		//InstanceViewContent instanceViewContent = new InstanceViewContent();
		//instanceViewContent.processManager = this.processManager;
		
		instanceViewContent.load(this);
		
		System.out.println("detail");	
		
		return instanceViewContent;
	}
	
	Long instId;
		@Id
		public Long getInstId() {
			return instId;
		}
		public void setInstId(Long instId) {
			this.instId = instId;
		}

	@Override
	public Long getDefVerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefVerId(Long defVerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getDefId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefId(Long defId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefPath(String DefPath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getDefName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefName(String defName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getStartedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStartedDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getFinishedDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFinishedDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDueDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDueDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getDefModDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefModDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Date getModDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setModDate(Date when) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStatus(String status) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInfo(String info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsDeleted() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsDeleted(boolean isDeleted) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsAdhoc() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsAdhoc(boolean isAdhoc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsSubProcess() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsSubProcess(boolean isSubProcess) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getRootInstId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRootInstId(Long instanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getMainInstId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainInstId(Long instanceId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMainActTrcTag() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainActTrcTag(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getMainExecScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainExecScope(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getMainDefVerId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMainDefVerId(Long defVerId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getIsArchive() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setIsArchive(boolean isArchive) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAbsTrcPath() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAbsTrcPath(String value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean getDontReturn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDontReturn(boolean value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Number getStrategyId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setStrategyId(Number strategyId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInitEp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInitEp(String initEp) {
		// TODO Auto-generated method stub
		
	}
	
	IUser currentUser;
		public IUser getCurrentUser() {
			return currentUser;
		}
		public void setCurrentUser(IUser currentUser) {
			this.currentUser = currentUser;
		}

	IUser initiator;
		public IUser getInitiator() {
			return initiator;
		}
		public void setInitiator(IUser initiator) {
			this.initiator = initiator;
		}

}
