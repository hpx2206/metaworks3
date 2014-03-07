package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.widget.ModalWindow;

public class SelfTestSurveyPanel {
	
	public static final String INIT = "init";
	public static final String PROGRESSIVE = "progressive";
	public static final String COMPLETE = "complete";
	
	public static final String SURVEY_FIRST = "first";
	public static final String SURVEY_SECOND= "second";
	public static final String SURVEY_THIRD = "third";
	public static final String SURVEY_FOURTH = "fourth";
	public static final String SURVEY_FIFTH = "fifth";
	public static final String SURVEY_SIXTH = "sixth";
	public static final String SURVEY_SEVENTH = "seventh";
	public static final String SURVEY_EIGHTH = "eighth";
	public static final String SURVEY_NINTH = "ninth";
	
	public static final Long SURVEY_INIT_INDEX = new Long(1);
	
	int sumScore;

	
	public int getSumScore() {
		return sumScore;
	}


	public void setSumScore(int sumScore) {
		this.sumScore = sumScore;
	}
		
	Long surveyIndex;
		public Long getSurveyIndex() {
			return surveyIndex;
		}
		public void setSurveyIndex(Long surveyIndex) {
			this.surveyIndex = surveyIndex;
		}

	ISelfTestSurveyContent surveyContent;
		public ISelfTestSurveyContent getSurveyContent() {
			return surveyContent;
		}
		public void setSurveyContent(ISelfTestSurveyContent surveyContent) {
			this.surveyContent = surveyContent;
		}

	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	SelfTestChart selfTestChart;
		public SelfTestChart getSelfTestChart() {
				return selfTestChart;
		}
		public void setSelfTestChart(SelfTestChart selfTestChart) {
			this.selfTestChart = selfTestChart;
		}

	@AutowiredFromClient
	public Session session;
		
	public void load() throws Exception {
		
		if(metaworksContext == null)
			metaworksContext = new MetaworksContext();
		
		ISelfTestSurvey iSelfTestSurvey = new SelfTestSurvey().checkProgress(session.getEmployee().getEmpCode());
		
		while(iSelfTestSurvey.next()) {
			if(iSelfTestSurvey.getSurveyIndex() == null || iSelfTestSurvey.getSurveyIndex() == 0) {
				if(iSelfTestSurvey.getSurveyIndex() == null) {
					// db 안의 설문조사 시작 인덱스는 1부터 시작을 기반으로 한다.
					this.setSurveyIndex(SURVEY_INIT_INDEX);
					
				} else {
					
					// 첫번째 설문이 끝낫으면 두번째 설문을 검색해서 가져와야 하므로 + 1 
					this.setSurveyIndex(iSelfTestSurvey.getSurveyIndex() + 1);
				}
				
				this.getMetaworksContext().setWhen(INIT);
				
			} else {
				this.setSurveyIndex(iSelfTestSurvey.getSurveyIndex());
				this.getMetaworksContext().setWhen(PROGRESSIVE);
				
			}
		}
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object progressfirstSurvey() throws Exception {
		
		this.getMetaworksContext().setHow(SURVEY_FIRST);
		
		SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
		this.setSurveyContent(selfTestSurveyContent.findFirstSurvey(this.getSurveyIndex()));
		
		return this;
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object showResult() throws Exception {
		if(metaworksContext == null)
			metaworksContext = new MetaworksContext();
		this.getMetaworksContext().setHow(COMPLETE);
		
		if(selfTestChart == null){
			selfTestChart = new SelfTestChart();
			selfTestChart.setSumScore(this.getSumScore());
		}
		
		ModalWindow modalWindow = new ModalWindow();
		modalWindow.setWidth(900);
		modalWindow.setTitle("셀프 테스트 설문조사");
		modalWindow.setHeight(600);
		modalWindow.setPanel(selfTestChart);
		
		//selfTestChart의 오버라이드된 ToAppeend메소드로 
		return modalWindow;
		
	}
}
