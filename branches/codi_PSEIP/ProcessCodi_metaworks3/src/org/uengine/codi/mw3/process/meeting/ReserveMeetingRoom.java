package org.uengine.codi.mw3.process.meeting;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.uengine.codi.ITool;

@Face(ejsPath="genericfaces/FormFace.ejs", options={"fieldOrder"}, values={"title,startDate-startTime,endDate-endTime,participants"})
public class ReserveMeetingRoom implements ITool, ContextAware {

	String title;
		@Face(displayName="회의주제", ejsPath="genericfaces/richText.ejs", options={"rows", "cols"}, values={"1", "50"})
		@NotNull(message="회의 주제를 입력하세요.")
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		
	MeetingFollower participants;
		@Face(displayName="참여자")
		public MeetingFollower getParticipants() {
			return participants;
		}
		public void setParticipants(MeetingFollower participants) {
			this.participants = participants;
		}
		
	Date startDate;
		@Face(displayName="시작일자")		
		@NotNull(message="시작일자일 입력하세요.")
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

	String startTime;
		@Face(displayName="시작시간")
		@Range(options={"09:00", "10:00","11:00","12:00","13:00","14:00","15:00","16:00", "17:00"}, values={"09", "10","11","12","13","14","15","16", "17"})
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
	
	Date endDate;
		@Face(displayName="종료일자")
		@NotNull(message="종료일자일 입력하세요.")
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	String endTime;		
		@Face(displayName="종료시간")
		@Range(options={"10:00","11:00","12:00","13:00","14:00","15:00","16:00", "17:00", "18:00"}, values={"09", "10","11","12","13","14","15","16","17","18"})
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
			
	@Override
	public void onLoad() throws Exception {
		if(MetaworksContext.WHEN_EDIT.equals(this.getMetaworksContext().getWhen())){
			this.setParticipants(new MeetingFollower());
			
			this.setStartDate(new Date());
			this.setEndDate(new Date());
		}
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterComplete() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
