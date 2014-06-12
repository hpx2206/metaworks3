package org.uengine.codi.mw3.model;

import java.util.ArrayList;
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
	public static final String SURVEY_FIRST = "1";
	public static final String SURVEY_SECOND= "2";
	public static final String SURVEY_THIRD = "3";
	public static final String SURVEY_FOURTH = "4";
	public static final String SURVEY_FIFTH = "5";
	public static final String SURVEY_SIXTH = "6";
	public static final String SURVEY_SEVENTH = "7";
	public static final String SURVEY_EIGHTH = "8";
	public static final String SURVEY_NINTH = "9";
	
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
	
	// progressive일 때 비교하기 위한 String[] 
	public static final String[] SURVEY_LIST =
		{SURVEY_FIRST, SURVEY_SECOND, SURVEY_THIRD, SURVEY_FOURTH, SURVEY_FIFTH, SURVEY_SIXTH, SURVEY_SEVENTH, SURVEY_EIGHTH, SURVEY_NINTH};
	
	// 초기화를 위한 Long
	public static final Long SURVEY_INDEX_INIT = new Long(0);
	public static final Long SURVEY_SCORE_INIT = new Long(0);
	
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
		
	SelfTestChart selfTestChart;
		public SelfTestChart getSelfTestChart() {
			return selfTestChart;
		}
		public void setSelfTestChart(SelfTestChart selfTestChart) {
			this.selfTestChart = selfTestChart;
		}

	@AutowiredFromClient
	public Session session;
		
	// load 할 때, 셀프 테스트가 처음인지 이미 한 기록이 있는지 살펴 보아야 한다.
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
		int checkSurvey = selfTestSurvey.checkProgress(session.getEmployee().getGlobalCom());
		
		if(checkSurvey == 0) {
			this.getMetaworksContext().setWhen(INIT);
			
		// 일로 들어온 순간 무조건 테스트를 한 적이 있다는 것.
		} else {
			this.getMetaworksContext().setWhen(PROGRESSIVE);
//			while(iSelfTestSurvey.next()) {
//				
//				if(iSelfTestSurvey.getEmpCode() == null && iSelfTestSurvey.getSurveyIndex() == 0) {
//					this.getMetaworksContext().setWhen(INIT);
//					
//				} else if(session.getEmployee().getEmpCode().equals(iSelfTestSurvey.getEmpCode())){
//					this.getMetaworksContext().setWhen(PROGRESSIVE);
//					
//				}
//			}
			
		}
		
	}
	@ServiceMethod(callByContent=true)
	public Object surveyStandBy() throws Exception {
		
		if(INIT.equals(this.getMetaworksContext().getWhen())) {
			
			SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
			selfTestSurvey.createEmpScore(session.getEmployee().getGlobalCom());
			
			this.getMetaworksContext().setHow(SURVEY_FIRST);
			this.setSurveyIndex(SURVEY_ORDER_FIRST);
		
			SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
			this.setSurveyContent(selfTestSurveyContent.findSurvey(this.getSurveyIndex()));
			
		} else {
			
			SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
			Long surveyIndex = selfTestSurvey.findSurveyIndex(session.getEmployee().getGlobalCom());
			
			if(surveyIndex == 0) {
				this.getMetaworksContext().setHow(SURVEY_FIRST);
				this.setSurveyIndex(SURVEY_ORDER_FIRST);
				
				SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
				this.setSurveyContent(selfTestSurveyContent.findSurvey(this.getSurveyIndex()));
				
			} else if(surveyIndex == SURVEY_LENGTH) {
				
				this.getMetaworksContext().setHow(COMPLETE);
				this.showResult();
				
			} else {
				Long nextSurveyIndex = surveyIndex + 1;
				this.setSurveyIndex(nextSurveyIndex);
				//contenxt는 총 9개 이중에 surveyIndex와 맞는 것을 찾아야한다.
				for(int i = 0; i < SURVEY_LIST.length; i++) {
					if(SURVEY_LIST[i].equals(surveyIndex.toString())) {
						this.getMetaworksContext().setHow(SURVEY_LIST[i]);
						
					}
				}
				
				SelfTestSurveyContent selfTestSurveyContent = new SelfTestSurveyContent();
				this.setSurveyContent(selfTestSurveyContent.findSurvey(this.getSurveyIndex()));
				
			}
		}
		
		return this;
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object progressSurvey() throws Exception {
		
		if(SURVEY_FIRST.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SECOND);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_SECOND);
			
			return this;
		}
		
		if(SURVEY_SECOND.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_THIRD);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_THIRD);
			
			return this;
		}	
		
		if(SURVEY_THIRD.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_FOURTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_FOURTH);
			
			return this;
		}
		
		if(SURVEY_FOURTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_FIFTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_FIFTH);
			
			return this;
		}
		
		if(SURVEY_FIFTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SIXTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_SIXTH);
			
			return this;
		}
		
		if(SURVEY_SIXTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_SEVENTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_SEVENTH);
			
			return this;
		}
		
		if(SURVEY_SEVENTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_EIGHTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_EIGHTH);
			
			return this;
		}
		
		if(SURVEY_EIGHTH.equals(this.getMetaworksContext().getHow())) {
			this.setSurveyIndex(SURVEY_ORDER_NINTH);
			this.saveSurvey();
			this.loadSurvey();
			this.getMetaworksContext().setHow(SURVEY_NINTH);
			
			return this;
		}
		
		if(SURVEY_NINTH.equals(this.getMetaworksContext().getHow())) {
			this.getMetaworksContext().setHow(COMPLETE);
			this.saveSurvey();
			
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
		
		// 마지막 단계일 때.
		if(COMPLETE.equals(this.getMetaworksContext().getHow())) {
			int parseIndex = Integer.parseInt(this.getSurveyIndex().toString());
			selfTestSurvey.save(this.getSumScore(), SURVEY_LIST[parseIndex - 1], session.getEmployee().getGlobalCom(), this.getSurveyIndex());
			
		// 그렇지 않을 때.
		} else {
			// 한단계 앞의 걸 저장해야하므로 -1
			int parseIndex = (int) (this.getSurveyIndex() - 1);
			// 그런데 배열은 0번째가 처음이다 그러므로 -1을 한번더
			selfTestSurvey.save(this.getSumScore(), SURVEY_LIST[parseIndex - 1], session.getEmployee().getGlobalCom(), this.getSurveyIndex());
		}
	}
	
	@ServiceMethod(callByContent=true)
	public Object showResult() throws Exception {
		this.progressSurvey();
		
		SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
		ISelfTestSurvey iSelfTestSurvey = selfTestSurvey.findAllSelfTestScore(session.getEmployee().getGlobalCom());
		
		ArrayList<Long> sumScoreList = new ArrayList<Long>();
		
		while(iSelfTestSurvey.next()) {
			sumScoreList.add(iSelfTestSurvey.getSurvey1());
			sumScoreList.add(iSelfTestSurvey.getSurvey2());
			sumScoreList.add(iSelfTestSurvey.getSurvey3());
			sumScoreList.add(iSelfTestSurvey.getSurvey4());
			sumScoreList.add(iSelfTestSurvey.getSurvey5());
			sumScoreList.add(iSelfTestSurvey.getSurvey6());
			sumScoreList.add(iSelfTestSurvey.getSurvey7());
			sumScoreList.add(iSelfTestSurvey.getSurvey8());
			sumScoreList.add(iSelfTestSurvey.getSurvey9());
		} 
		
		if(selfTestChart == null) {
 			selfTestChart = new SelfTestChart(); 
			
		}
		selfTestChart.setSumScore(sumScoreList);
		
	    return this;
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object restartSurvey() throws Exception {
		SelfTestSurvey selfTestSurvey = new SelfTestSurvey();
		selfTestSurvey.setGlobalCom(session.getEmployee().getGlobalCom());
		selfTestSurvey.resetScore();
		
		this.getMetaworksContext().setWhen(INIT);
		this.getMetaworksContext().setHow(MetaworksContext.HOW_EVER);
		
		return this;
	}
}
