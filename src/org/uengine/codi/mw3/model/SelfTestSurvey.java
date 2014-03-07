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
	Long first;
		public Long getFirst() {
			return first;
		}
		public void setFirst(Long first) {
			this.first = first;
		}
		
	Long second;
		public Long getSecond() {
			return second;
		}
		public void setSecond(Long second) {
			this.second = second;
		}
		
	Long third;
		public Long getThird() {
			return third;
		}
		public void setThird(Long third) {
			this.third = third;
		}
		
	Long fourth;
		public Long getFourth() {
			return fourth;
		}
		public void setFourth(Long fourth) {
			this.fourth = fourth;
		}
		
	Long fifth;
		public Long getFifth() {
			return fifth;
		}
		public void setFifth(Long fifth) {
			this.fifth = fifth;
		}
		
	Long sixth;
		public Long getSixth() {
			return sixth;
		}
		public void setSixth(Long sixth) {
			this.sixth = sixth;
		}
		
	Long seventh;
		public Long getSeventh() {
			return seventh;
		}
		public void setSeventh(Long seventh) {
			this.seventh = seventh;
		}
		
	Long eighth;
		public Long getEighth() {
			return eighth;
		}
		public void setEighth(Long eighth) {
			this.eighth = eighth;
		}
		
	Long ninth;
		public Long getNinth() {
			return ninth;
		}
		public void setNinth(Long ninth) {
			this.ninth = ninth;
		}
		
	public ISelfTestSurvey checkProgress(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select surveyIndex");
		sb.append(" from pseip_survey_score");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setEmpCode(empCode);
		survey.select();
		
		return survey;
	}
	
	public void save(Long sumScore, String surveyForm, String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("update pseip_survey_score");
		sb.append(" set "+surveyForm+" = "+sumScore.toString()+", surveyIndex = ?surveyIndex");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setSurveyIndex(this.getSurveyIndex());
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
}
