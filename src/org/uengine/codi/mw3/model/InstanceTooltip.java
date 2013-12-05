package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class InstanceTooltip implements ContextAware {
	
	public static final int NON_CHECEKD = 0;
	public static final int CHECKED = 1;

	@AutowiredFromClient
	public Session session;
	
	@Autowired
	public ProcessManagerRemote processManager;	

	@AutowiredFromClient
	public Locale localeManager;
	
	Long instanceId;
		@Id
		public Long getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(Long instanceId) {
			this.instanceId = instanceId;
		}
	
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}
		
	String status;
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
		
	public InstanceTooltip() throws Exception{
		setMetaworksContext(new MetaworksContext());
	}
	
	@ServiceMethod(payload={"instanceId"}, target=ServiceMethodContext.TARGET_POPUP)
	public ModalWindow monitor() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.monitor();
	}
	
	@ServiceMethod(payload={"instanceId"}, target=ServiceMethodContext.TARGET_POPUP, loader="auto")
	public Popup schedule() throws Exception{
		
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.schedule();
	}
	
	@ServiceMethod(payload={"instanceId"}, target=ServiceMethodContext.TARGET_POPUP, loader="auto")
	public Popup newSubInstance() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.newSubInstance();
	}

	@ServiceMethod(payload={"instanceId"}, needToConfirm=true, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] remove() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());
		
		return instance.remove();
	}
	
	@ServiceMethod(payload={"instanceId", "secuopt", "status"}, target=ServiceMethodContext.TARGET_SELF)
	public void toggleSecurityConversation() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;
		instance.setInstId(this.getInstanceId());		
		instance.setSecuopt(this.getSecuopt());
		instance.toggleSecurityConversation();
		
		this.setSecuopt(instance.getSecuopt());
	}
	
	@ServiceMethod(payload={"instanceId", "secuopt", "status"}, target=ServiceMethodContext.TARGET_POPUP)
	public Object[] complete() throws Exception{
		Instance instance = new Instance();
		instance.processManager = processManager;
		instance.session = session;
		instance.localeManager = localeManager;		
		instance.setInstId(this.getInstanceId());
		instance.setStatus(this.getStatus());
		instance.complete();
		
		this.setStatus(instance.getStatus());
		
		// 평가하기
		Followers followers = new Followers();
		followers.setInstanceId(this.getInstanceId().toString());
		IUser users = followers.findFollowers();
				
//		while(users.next()){
//			System.out.println(""+ users.getName() +"");
//		}
				
		// 나 말고도 다른 팔로워 들이 있으면
		// size가 2인 이유는 일을 시작한 사람이 1명
		// 그리고 현재 인스턴스를 보고 있는 사람이 1명 해서 총 2명이다.
		// 인스턴스 이니시에이터가 아닌 사람이 완료를 누르는 경우에는 작동하면 안된다. 그러면
		// user의 사이즈가 늘어난다...(일에 참여한 것이므로)
		if(users.size() >= 2) {
			Popup assessmentPopup = new Popup();
			AssessmentPanel assessmentPanel = new AssessmentPanel();
			while(users.next()) {
				Assessment assessment = new Assessment();
				assessment.setEmpCode(users.getUserId());
				assessment.setUserName(users.getName());
				assessment.setMetaworksContext(new MetaworksContext());
				assessment.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
				if(assessmentPanel.getAssessment() == null) {
					assessmentPanel.assessment = new ArrayList<Assessment>();
				}
				assessmentPanel.getAssessment().add(assessment);
			}
			assessmentPopup.setPanel(assessmentPanel);
			return new Object[]{new Refresh(this), assessmentPopup};
			
		} else {
			return null;
		}
//		
	}
	
	// 좋아요 버튼 누를 때
	@ServiceMethod(payload="instanceId")
	public Object[] addLikeInstance() throws Exception {
		LikeItem like = new LikeItem();
		like.setInstId(this.getInstanceId());
		like.setEmpCode(session.getEmployee().getEmpCode());
		ILikeItem findlike = like.checkEmpLikeClick();
		
		if(findlike == null){
			like.setChecked(CHECKED);
			like.addLikeInstance();
			
		} else {
			if(findlike.getChecked() == CHECKED)
				like.setChecked(NON_CHECEKD);
			else
				like.setChecked(CHECKED);
			
			like.syncToDatabaseMe();
		}
		
		// 인스턴스 리스트 패널 뿌려주기 
		InstanceList instanceList = new InstanceList(session);
		instanceList.session = session;
		instanceList.setMetaworksContext(new MetaworksContext());
		instanceList.load();
		
		InstanceListPanel instanceListPanel = new InstanceListPanel(session);
		instanceListPanel.session = session;
		instanceListPanel.setInstanceList(instanceList);
		
		return new Object[]{instanceListPanel};
		
	}
	
}
