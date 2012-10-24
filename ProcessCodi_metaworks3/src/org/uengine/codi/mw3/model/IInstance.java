package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
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

	public Date getStartedDate();
	public void setStartedDate(Date when);

	public Date getFinishedDate();
	public void setFinishedDate(Date when);

	public Date getDueDate();
	public void setDueDate(Date when);

	public Date getDefModDate();
	public void setDefModDate(Date when);

	public Date getModDate();
	public void setModDate(Date when);

	public String getStatus();
	public void setStatus(String status);

	public String getInfo();
	public void setInfo(String info);

	public String getName();
	public void setName(String name);
	
	public boolean getIsDeleted();
	public void setIsDeleted(boolean isDeleted);
	
	public boolean getIsAdhoc();
	public void setIsAdhoc(boolean isAdhoc);

	public boolean getIsSubProcess();
	public void setIsSubProcess(boolean isSubProcess);
	
	public Long getRootInstId();
	public void setRootInstId(Long instanceId);
	
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
	
	public String getTopicId();
	public void setTopicId(String topicId);
	
	@NonLoadable
	@NonSavable
	public InstanceViewThreadPanel getInstanceViewThreadPanel();
	public void setInstanceViewThreadPanel(InstanceViewThreadPanel instanceViewThreadPanel);

	
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
	
	@ServiceMethod(inContextMenu=true, callByContent=true, keyBinding="Ctrl+X")
	public Session cut();
	
	@ServiceMethod(inContextMenu=true, callByContent=true, needToConfirm=true, target="popup"/*, mouseBinding="drop"*/, keyBinding="Ctrl+V")
	public Object[] paste() throws Exception;
	
	@ServiceMethod(inContextMenu=true, callByContent=true)
	public Object[] addTrayBar() throws Exception;

	@ServiceMethod(inContextMenu=true, callByContent=true, needToConfirm=true)
	@Available(when="instanceNavigator")
	@Face(displayName="$SplitFromRootProcess")
	public void split() throws Exception;
	
	@ServiceMethod(inContextMenu=true, callByContent=true, target=TARGET_SELF)	
	public void over() throws Exception;
	
	
}
