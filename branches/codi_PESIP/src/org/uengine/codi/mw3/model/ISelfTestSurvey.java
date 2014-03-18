package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_survey_score")
public interface ISelfTestSurvey extends IDAO{
	
	@Id
	public String getGlobalCom();
	public void setGlobalCom(String globalCom);
	
	// 설문조사 진행 확인
	public Long getSurveyIndex();
	public void setSurveyIndex(Long surveyIndex);
	
	// 설문조사 점수 부분
	public Long getSurvey1();
	public void setSurvey1(Long survey1);
	
	public Long getSurvey2();
	public void setSurvey2(Long survey2);
	
	public Long getSurvey3();
	public void setSurvey3(Long survey3);
	
	public Long getSurvey4();
	public void setSurvey4(Long survey4);
	
	public Long getSurvey5();
	public void setSurvey5(Long survey5);
	
	public Long getSurvey6();
	public void setSurvey6(Long survey6);
	
	public Long getSurvey7();
	public void setSurvey7(Long survey7);
	
	public Long getSurvey8();
	public void setSurvey8(Long survey8);
	
	public Long getSurvey9();
	public void setSurvey9(Long survey9);
	
	public int checkProgress(String empCode) throws Exception;
	public ISelfTestSurvey checkEmpCode(String empCode) throws Exception;
	public Long findSurveyIndex(String empCode) throws Exception;
	public ISelfTestSurvey findAllSelfTestScore(String empCode) throws Exception;
	public void createEmpScore(String empCode) throws Exception;
	public void resetScore() throws Exception;
	
}
