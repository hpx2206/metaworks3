package org.uengine.codi.mw3.model;

import java.util.Map;

import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.annotation.Test;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

@Face(
		ejsPathMappingByContext=
	{
		"{where: 'pinterest', face: 'dwr/metaworks/org/uengine/codi/mw3/model/IInstance_pinterest.ejs'}",
	}		

)
@Table(name="bpm_procinst")
public interface IInstance extends IDAO{

	@ServiceMethod
	public ContentWindow detail() throws Exception;

	@ServiceMethod(target="popup")
	public ModalWindow popupDetail() throws Exception;

	@ServiceMethod(target="self")
	public Object replaceDetail() throws Exception;

	@ServiceMethod	
	@Test(scenario="first", starter=true, instruction="$first.FlowChart", next="autowiredObject.org.uengine.codi.mw3.model.InstanceListPanel.switchToKnowledge()")
	public ProcessInstanceMonitor flowchart() throws Exception;
	
	@Id
	public Long getInstId();
	public void setInstId(Long id);

	public String getDefVerId();
	public void setDefVerId(String defVerId);

	public String getDefId();
	public void setDefId(String defId);

	public String getDefPath();
	public void setDefPath(String DefPath);

	public String getDefName();
	public void setDefName(String defName);

	
	public String getLastCmnt();
	public void setLastCmnt(String lastComment);

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
	
	public String getInitComCd();
	public void setInitComCd(String initComCd);

	public String getSecuopt();
	public void setSecuopt(String secuopt);
	
	public String getAssignee();
	public void setAssignee(String assignee);

	public boolean isInitCmpl();
	public void setInitCmpl(boolean initCmpl);
	
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
	
	@ServiceMethod(inContextMenu=true, callByContent=true, mouseBinding="drag", keyBinding="Ctrl+X")
	public Session cut();
	
	@ServiceMethod(inContextMenu=true, callByContent=true, needToConfirm=true, target="popup"/*, mouseBinding="drop"*/, keyBinding="Ctrl+V")
	public Object[] paste() throws Exception;

	@ServiceMethod(inContextMenu=true, callByContent=true, needToConfirm=true)
	@Available(when="instanceNavigator")
	@Face(displayName="$SplitFromRootProcess")
	public void split() throws Exception;
}
