package org.uengine.codi.mw3.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dwr.MetaworksRemoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.Login;
import org.uengine.codi.mw3.filter.OtherSessionFilter;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName="$InstanceDueSetter", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs"
	, options={"fieldOrder"},values={"progress,dueDate,benefit,penalty,effort,onlyInitiatorCanComplete"} )
public class InstanceDueSetter implements ContextAware{
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
	
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}

	Long instId;
		@Hidden
		public Long getInstId() {
			return instId;
		}
	
		public void setInstId(Long instId) {
			this.instId = instId;
		}
		
	java.util.Date dueDate;
		@Face(displayName="$Due")
		public java.util.Date getDueDate() {
			return dueDate;
		}
	
		public void setDueDate(java.util.Date dueDate) {
			this.dueDate = dueDate;
		}
		
	boolean onlyInitiatorCanComplete;
		@Face(displayName="$OnlyInitiatorCanComplete")
		public boolean isOnlyInitiatorCanComplete() {
			return onlyInitiatorCanComplete;
		}
		public void setOnlyInitiatorCanComplete(boolean onlyInitiatorCanComplete) {
			this.onlyInitiatorCanComplete = onlyInitiatorCanComplete;
		}
	
	String progress;
		@Face(displayName="$progress")
		public String getProgress() {
			return progress;
		}
		public void setProgress(String progress) {
			this.progress = progress;
		}

	int benefit;
		@Face(displayName="$BV.benefit")
		public int getBenefit() {
			return benefit;
		}
		public void setBenefit(int benefit) {
			this.benefit = benefit;
		}

	int penalty;
		@Face(displayName="$BV.penalty")
		public int getPenalty() {
			return penalty;
		}
		public void setPenalty(int penalty) {
			this.penalty = penalty;
		}

	int effort;
		@Face(displayName="$Effort")
		public int getEffort() {
			return effort;
		}
		public void setEffort(int effort) {
			this.effort = effort;
		}
		
//	IUser assignee;
//
//		public IUser getAssignee() {
//			return assignee;
//		}
//	
//		public void setAssignee(IUser assignee) {
//			this.assignee = assignee;
//		}
		



	@Face(displayName="$Apply")
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] apply() throws Exception{
		Instance instance = new Instance();
		instance.setInstId(getInstId());

		if(getDueDate()==null){
			instance.databaseMe().setDueDate(null);
		}else{
			Calendar cal = Calendar.getInstance();
			cal.setTime(getDueDate());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 59);
			cal.set(Calendar.MILLISECOND, 0);
			
			instance.databaseMe().setDueDate(cal.getTime());
		}
		
		instance.databaseMe().setInitCmpl(isOnlyInitiatorCanComplete());
		instance.databaseMe().setProgress(getProgress());
		
		instance.databaseMe().setBVBenefit(getBenefit());
		instance.databaseMe().setBVPenalty(getPenalty());
		instance.databaseMe().setEffort(getEffort());
		
		instance.flushDatabaseMe();
		
		IInstance iInstance = instance.databaseMe();
		iInstance.setMetaworksContext(getMetaworksContext());
		iInstance.getMetaworksContext().setWhen("view");
		
		
		if(getDueDate() != null){
			//if schedule changed
			CommentWorkItem workItem = new CommentWorkItem();
			workItem.getMetaworksContext().setHow("changeSchedule");
			workItem.session = session;
			workItem.processManager = processManager;
			
			String title = "[일정 변경:" + new SimpleDateFormat("yyyy/MM/dd").format(getDueDate()) + "]";
			
			workItem.setInstId(getInstId());
			workItem.setTitle(title);
			workItem.add();
			
			MetaworksRemoteService.pushTargetClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new ToAppend(workItem, workItem)});
			
			//MetaworksRemoteService.pushOtherClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(iInstance), new WorkItemListener(workItem)});
			MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()), session.getUser().getUserId()),
					new Object[]{new InstanceListener(iInstance), new WorkItemListener(workItem)});		
		}
		
		return new Object[]{new Remover(new Popup(), true)};
	}

	
	public InstanceDueSetter(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
		//setDueDate(new Date());
	}
	
	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;
}
