package org.uengine.codi.mw3.process.vacation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Range;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.User;

@Face(ejsPath="genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"type,destination,startDate,endDate,emergencyContact,delegator,content"})
public class VacationRequest implements ITool, ContextAware {

	String type;
		@Face(displayName="휴가구분")
		@Range(options={"선택", "연차", "반차", "특별휴가", "하계휴가", "공가", "병가"}, values={"", "0", "1", "2", "3", "4", "5", "6"})
		@NotNull(message="휴가구분을 선택하세요.")
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String destination;
		@Face(displayName="목적지", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "50"})
		@NotNull(message="목적지를 선택하세요")
		public String getDestination() {
			return destination;
		}
		public void setDestination(String destination) {
			this.destination = destination;
		}

	Date startDate;
		@Face(displayName="시작일")
		@NotNull(message="시작일을 입력하세요.")
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

	Date endDate;
		@Face(displayName="종료일")
		@NotNull(message="종료일을 입력하세요.")
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	String emergencyContact;
		@Face(displayName="비상연락처")
		@NotNull(message="비상연락처를 입력하세요.")
		public String getEmergencyContact() {
			return emergencyContact;
		}
		public void setEmergencyContact(String emergencyContact) {
			this.emergencyContact = emergencyContact;
		}
	
	IUser delegator;
		@Face(displayName="업무대리자")
		public IUser getDelegator() {
			return delegator;
		}
		public void setDelegator(IUser delegator) {
			this.delegator = delegator;
		}
	
	String content;
		@Face(displayName="신청내용", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"5", "50"})
		@NotNull(message="신청내용을 입력하세요.")
		public String getContent() {
			return content;
		}
	
		public void setContent(String content) {
			this.content = content;
		}
	

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	/* bpm variable */
	String delegatorId;
		@Hidden
		public String getDelegatorId() {
			return delegatorId;
		}
		public void setDelegatorId(String delegatorId) {
			this.delegatorId = delegatorId;
		}
		
	@Override
	public void onLoad() throws Exception {		
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			IUser delegator = new User();
			delegator.getMetaworksContext().setHow("picker");
			
			this.setDelegator(delegator);
			
			this.setStartDate(new Date());
			this.setEndDate(new Date());
		}
		
		this.getDelegator().getMetaworksContext().setWhen(this.getMetaworksContext().getWhen());
	}

	@Override
	public void beforeComplete() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setDelegatorId(this.getDelegator().getUserId());
		}
		
	}

	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	

}
