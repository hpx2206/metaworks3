package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public class SelfTestSurvey extends Database<ISelfTestSurvey> implements ISelfTestSurvey{
	
	String empCode;
		@Id
		public String getEmpCode() {
			return empCode;
		}
		public void setEmpCode(String empCode) {
			this.empCode = empCode;
		}
	// 설문조사 진행 확인
	Long surveyIndex;
		public Long getSurveyIndex() {
			return surveyIndex;
		}
		public void setSurveyIndex(Long surveyIndex) {
			this.surveyIndex = surveyIndex;
		}
		
	// 설문조사 점수
	Long survey1;
		public Long getSurvey1() {
			return survey1;
		}
		public void setSurvey1(Long survey1) {
			this.survey1 = survey1;
		}
		
	Long survey2;
		public Long getSurvey2() {
			return survey2;
		}
		public void setSurvey2(Long survey2) {
			this.survey2 = survey2;
		}
		
	Long survey3;
		public Long getSurvey3() {
			return survey3;
		}
		public void setSurvey3(Long survey3) {
			this.survey3 = survey3;
		}
		
	Long survey4;
		public Long getSurvey4() {
			return survey4;
		}
		public void setSurvey4(Long survey4) {
			this.survey4 = survey4;
		}
		
	Long survey5;
		public Long getSurvey5() {
			return survey5;
		}
		public void setSurvey5(Long survey5) {
			this.survey5 = survey5;
		}
		
	Long survey6;
		public Long getSurvey6() {
			return survey6;
		}
		public void setSurvey6(Long survey6) {
			this.survey6 = survey6;
		}
		
	Long survey7;
		public Long getSurvey7() {
			return survey7;
		}
		public void setSurvey7(Long survey7) {
			this.survey7 = survey7;
		}
		
	Long survey8;
		public Long getSurvey8() {
			return survey8;
		}
		public void setSurvey8(Long survey8) {
			this.survey8 = survey8;
		}
		
	Long survey9;
		public Long getSurvey9() {
			return survey9;
		}
		public void setSurvey9(Long survey9) {
			this.survey9 = survey9;
		}
		
	public int checkProgress(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select empcode as empCode, surveyIndex");
		sb.append(" from pseip_survey_score");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setEmpCode(empCode);
		survey.select();
		
		return survey.size();
	}
	
	public void save(Long sumScore, String surveyForm, String empCode, Long surveyIndex) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update pseip_survey_score");
		sb.append(" set survey"+surveyForm+" = "+sumScore.toString()+", surveyIndex = ?surveyIndex");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setSurveyIndex(surveyIndex);
		survey.setEmpCode(empCode);
		survey.update();
		
	}
	
	public void createEmpScore(String empCode) throws Exception {
		SelfTestSurvey survey = new SelfTestSurvey();
		survey.setEmpCode(empCode);
		survey.createDatabaseMe();
	}
	
	public ISelfTestSurvey checkEmpCode(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select empcode");
		sb.append(" from pseip_survey_score");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setEmpCode(empCode);
		survey.select();
		
		return survey;
	}
	
	public Long findSurveyIndex(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select surveyIndex");
		sb.append(" from pseip_survey_score");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setEmpCode(empCode);
		survey.select();
		
		Long index = null;
		while(survey.next()) {
			if(survey.getSurveyIndex() == null || survey.getSurveyIndex() == 0) {
				// 0으로 리턴해준다.
				index = new Long(0);
				
			} else {
				index = survey.getSurveyIndex();
			}
		}
		
		return index;
	}
	
	public ISelfTestSurvey findAllSelfTestScore(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from pseip_survey_score");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey scores = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		scores.setEmpCode(empCode);
		scores.select();
		
		return scores;
	}
	
	public void resetScore() throws Exception {
		this.deleteDatabaseMe();
	}
	
}
