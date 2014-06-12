package org.uengine.codi.mw3.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
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
import org.uengine.codi.mw3.filter.AllSessionFilter;
import org.uengine.processmanager.ProcessManagerRemote;

@Face(displayName="$InstanceDueSetter", ejsPath="dwr/metaworks/genericfaces/FormFace.ejs"
	, options={"fieldOrder"},values={"dueDate,benefit,penalty,effort,progress,onlyInitiatorCanComplete"} )
public class InstanceDueSetter implements ContextAware{
	
	@AutowiredFromClient
	public Locale localeManager;
	
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

		// 공공서비스 과제에서는 아래의 세개를 표시하지 않습니다.
	int benefit;
		@Hidden
		//@Face(displayName="$BV.benefit")
		public int getBenefit() {
			return benefit;
		}
		public void setBenefit(int benefit) {
			this.benefit = benefit;
		}

	int penalty;
		@Hidden
		//@Face(displayName="$BV.penalty")
		public int getPenalty() {
			return penalty;
		}
		public void setPenalty(int penalty) {
			this.penalty = penalty;
		}

	int effort;
		@Hidden
		//@Face(displayName="$Effort")
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

		IInstance instanceRef = instance.databaseMe();
		
		if(instanceRef.isInitCmpl() != isOnlyInitiatorCanComplete())
			instanceRef.setInitCmpl(isOnlyInitiatorCanComplete());		// 시작자만 완료 가능
		if(instanceRef.getBenefit() != getBenefit())
			instanceRef.setBenefit(getBenefit());			// benefit
		if(instanceRef.getPenalty() != getPenalty())
			instanceRef.setPenalty(getPenalty());			// penalty
		if(instanceRef.getEffort() != getEffort())
			instanceRef.setEffort(getEffort());				// effort
		
		instanceRef.getMetaworksContext().setWhen("blinking");
		
		long databaseDueTime = 0;
		long dueTime = 0;
		
		// 납기일 설정
		if(instanceRef.getDueDate() != null){
			databaseDueTime = instanceRef.getDueDate().getTime();
		}
		
		if(getDueDate() != null){
			Calendar cal = Calendar.getInstance();
			cal.setTime(getDueDate());
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 23, 59, 00);
			cal.set(Calendar.MILLISECOND, 0);
			
			dueTime = (cal.getTime()).getTime();
		}
		
		
		if(databaseDueTime != dueTime){
			instanceRef.setDueDate(new Date(dueTime));
			
			//if schedule changed
			CommentWorkItem workItem = new CommentWorkItem();
			workItem.getMetaworksContext().setHow("changeSchedule");
			workItem.session = session;
			workItem.processManager = processManager;
			
			String title = null;
			
			if(dueTime == 0){
				title = localeManager.getString("$CancelDate");
			}else{				
				title = localeManager.getString("$ChangedDate");			
				title += ": " + new SimpleDateFormat("yyyy/MM/dd").format(getDueDate());
			}
			
			workItem.setInstId(getInstId());
			workItem.setTitle(title);
			workItem.add();

			MetaworksRemoteService.pushTargetClientObjects(
					Login.getSessionIdWithUserId(session.getUser().getUserId()),
					new Object[]{new ToAppend(new InstanceViewThreadPanel(), workItem)});
			
			//상대에게 실시간으로 워크아이템 푸쉬 해주는건데.. 워크아이템 에서 작업을 해주기떄문에 필요 없음.
			//MetaworksRemoteService.pushOtherClientObjects(Login.getSessionIdWithUserId(session.getUser().getUserId()), new Object[]{new InstanceListener(iInstance), new WorkItemListener(workItem)});
			/*MetaworksRemoteService.pushClientObjectsFiltered(
					new OtherSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom()), session.getUser().getUserId().toUpperCase()),
					new Object[]{new WorkItemListener(workItem)});	*/	
		}
		

		if(instanceRef.getImplementationObject().isDirty()){
			MetaworksRemoteService.pushClientObjectsFiltered(
				new AllSessionFilter(Login.getSessionIdWithCompany(session.getEmployee().getGlobalCom())),
				new Object[]{new InstanceListener(instanceRef)});		
		}
		
		//todobadge count real-time
		instance.flushDatabaseMe();
		
		TodoBadge todoBadge = new TodoBadge();
		todoBadge.session = session;
		todoBadge.refresh();
		
		return new Object[]{new Remover(new Popup(), true), new Refresh(todoBadge)};
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
