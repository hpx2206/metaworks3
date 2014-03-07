package org.uengine.codi.mw3.model;

import java.util.HashMap;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.kernel.UEngineException;

public class SelfTestSurveyPanel {
	
	public static final String INIT = "init";
	public static final String PROGRESSIVE = "progressive";
	public static final String COMPLETE = "complete";
	
	// metaworksContenxt를 위한 static final
	public static final String SURVEY_FIRST = "first";
	public static final String SURVEY_SECOND= "second";
	public static final String SURVEY_THIRD = "third";
	public static final String SURVEY_FOURTH = "fourth";
	public static final String SURVEY_FIFTH = "fifth";
	public static final String SURVEY_SIXTH = "sixth";
	public static final String SURVEY_SEVENTH = "seventh";
	public static final String SURVEY_EIGHTH = "eighth";
	public static final String SURVEY_NINTH = "ninth";
	
	// itemType을 위한 static final
	public static final Long SURVEY_ORDER_FIRST = new Long(1);
	public static final Long SURVEY_ORDER_SECOND = new Long(2);
	public static final Long SURVEY_ORDER_THIRD = new Long(3);
	public static final Long SURVEY_ORDER_FOURTH = new Long(4);
	public static final Long SURVEY_ORDER_FIFTH = new Long(5);
	public static final Long SURVEY_ORDER_SIXTH = new Long(6);
	public static final Long SURVEY_ORDER_SEVENTH = new Long(7);
	public static final Long SURVEY_ORDER_EIGHTH = new Long(8);
	public static final Long SURVEY_ORDER_NINTH = new Long(9);
	
	public static final int SURVEY_LENGTH = 9;
	
	Long sumScore;
		public Long getSumScore() {
			return sumScore;
		}
		public void setSumScore(Long sumScore) {
			this.sumScore = sumScore;
		}
		
	Long surveyIndex;
		public Long getSurveyIndex() {
			return surveyIndex;
		}
		public void setSurveyIndex(Long surveyIndex) {
			this.surveyIndex = surveyIndex;
		}
		
	HashMap<String, String> surveyForm;
		public HashMap<String, String> getSurveyForm() {
			return surveyForm;
		}
		public void setSurveyForm(HashMap<String, String> surveyForm) {
			this.surveyForm = surveyForm;
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
		
	@AutowiredFromClient
	public Session session;
		
	public void load() throws Exception {
		
		// survey 형태 지정
		if(surveyForm == null) {
			surveyForm = new HashMap<String, String>();
			surveyForm.put(SURVEY_FIRST, "제품");
			surveyForm.put(SURVEY_SECOND, "해외 정보");
			surveyForm.put(SURVEY_THIRD, "해외 마케팅 인력");
			surveyForm.put(SURVEY_FOURTH, "해외 마케팅 활동");
			surveyForm.put(SURVEY_FIFTH, "IT 역량");
			surveyForm.put(SURVEY_SIXTH, "브랜드 마케팅");
			surveyForm.put(SURVEY_SEVENTH, "R&D");
			surveyForm.put(SURVEY_EIGHTH, "글로벌 전략");
			surveyForm.put(SURVEY_NINTH, "글로벌 네트워크");
		}
		
		if(metaworksContext == null)
			metaworksContext = new MetaworksContext();
		
		SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
		ISelfTestSurvey iSelfTestSurvey = selfTestSurvey.checkProgress(session.getEmployee().getEmpCode());
		
		if(!(iSelfTestSurvey.next())) {
			this.getMetaworksContext().setWhen(INIT);
			
		} else if (iSelfTestSurvey.next()) {
			while(iSelfTestSurvey.next()) {
				
				if(iSelfTestSurvey.getSurveyIndex() == 0) {
					this.getMetaworksContext().setWhen(INIT);
					
				} else {
					this.getMetaworksContext().setWhen(PROGRESSIVE);
					
				}
			}
			
		}
		
	}
	@ServiceMethod(callByContent=true)
	public Object surveyStandBy() throws Exception {
		SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
		selfTestSurvey.createEmpScore(session.getEmployee().getEmpCode());
		
		this.getMetaworksContext().setHow(SURVEY_FIRST);
		this.setSurveyIndex(SURVEY_ORDER_FIRST);
		
		SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
		this.setSurveyContent(selfTestSurveyContent.findSurvey(this.getSurveyIndex()));
		
		return this;
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object initProgressSurvey() throws Exception {
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_FIRST.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SECOND);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_SECOND);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_SECOND.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_THIRD);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_THIRD);
			
			return this;
		}	
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_THIRD.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_FOURTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_FOURTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_FOURTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_FIFTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_FIFTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_FIFTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SIXTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_SIXTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_SIXTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SEVENTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_SEVENTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_SEVENTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_EIGHTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_EIGHTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_EIGHTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_NINTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_NINTH);
			
			return this;
		}
		
		if(INIT.equals(this.getMetaworksContext().getWhen()) && SURVEY_NINTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_NINTH);
			this.loadSurvey();
			this.saveSurvey();
			this.getMetaworksContext().setHow(SURVEY_NINTH);
			
			return this;
		}
		
		return new UEngineException("치명적인 에러입니다.");
	}
	
	public void loadSurvey() throws Exception {
		SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
		this.setSurveyContent(selfTestSurveyContent.findSurvey(this.getSurveyIndex()));
	}
	
	public void saveSurvey() throws Exception {
		SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
		selfTestSurvey.setSurveyIndex(this.getSurveyIndex());
		selfTestSurvey.save(new Long(2), this.getMetaworksContext().getHow(), session.getEmployee().getEmpCode());
	}
	
	@ServiceMethod(callByContent=true)
	public Object showResult() throws Exception {
		
		return this;
		
	}
}
