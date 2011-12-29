package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="bpm_procinst")
public interface IInstance extends IDAO{

	@ServiceMethod
	public ContentPanel detail() throws Exception;

	
	@Id
	public Long getInstId();
	public void setInstId(Long id);

	public Long getDefVerId();
	public void setDefVerId(Long defVerId);

	public Long getDefId();
	public void setDefId(Long defId);

	public String getDefPath();
	public void setDefPath(String DefPath);

	public String getDefName();
	public void setDefName(String defName);

	public java.util.Date getStartedDate();
	public void setStartedDate(java.util.Date when);

	public abstract java.util.Date getFinishedDate();
	public abstract void setFinishedDate(java.util.Date when);

	public abstract java.util.Date getDueDate();
	public abstract void setDueDate(java.util.Date when);

	public abstract java.util.Date getDefModDate();
	public abstract void setDefModDate(java.util.Date when);

	public abstract java.util.Date getModDate();
	public abstract void setModDate(java.util.Date when);

	public String getStatus();
	public void setStatus(String status);

	public String getInfo();
	public void setInfo(String info);

	public abstract String getName();
	public abstract void setName(String name);
	
	public abstract boolean getIsDeleted();
	public abstract void setIsDeleted(boolean isDeleted);
	
	public abstract boolean getIsAdhoc();
	public abstract void setIsAdhoc(boolean isAdhoc);

	public abstract boolean getIsSubProcess();
	public abstract void setIsSubProcess(boolean isSubProcess);
	
	public abstract Long getRootInstId();
	public abstract void setRootInstId(Long instanceId);
	
	public abstract Long getMainInstId();
	public abstract void setMainInstId(Long instanceId);
	
	public abstract String getMainActTrcTag();
	public abstract void setMainActTrcTag(String value);	

	public abstract String getMainExecScope();
	public abstract void setMainExecScope(String value);	

	public abstract Long getMainDefVerId();
	public abstract void setMainDefVerId(Long defVerId);
	
	public boolean getIsArchive();
	public void setIsArchive(boolean isArchive);
	
	public abstract String getAbsTrcPath();
	public abstract void setAbsTrcPath(String value);

	public boolean getDontReturn();
	public void setDontReturn(boolean value);
	
	public Number getStrategyId();
	public void setStrategyId(Number strategyId);
	
	public String getInitEp();
	public void setInitEp(String initEp);
	
	
	/////// following setter/getters stands for mapping tuple data to object by bean mapping /////////
	
	@ORMapping(
			objectFields = { "userId", "name"},
			databaseFields = { "initEp", "initRsNm" } 
	)
	public IUser getInitiator();
	public void setInitiator(IUser user);
	
	@ORMapping(
			objectFields = { "userId", "name"},
			databaseFields = { "currEp", "currRsNm" } 
	)
	public IUser getCurrentUser();
	public void setCurrentUser(IUser user);
	
	
}
