package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_survey_score")
public interface ISelfTestSurvey extends IDAO{
	
	@Id
	public String getEmpCode();
	public void setEmpCode(String empCode);
	
	// 설문조사 진행 확인
	public Long getSurveyIndex();
	public void setSurveyIndex(Long surveyIndex);
	
	// 설문조사 점수 부분
	public Long getFirst();
	public void setFirst(Long first);
	
	public Long getSecond();
	public void setSecond(Long second);
	
	public Long getThird();
	public void setThird(Long third);
	
	public Long getFourth();
	public void setFourth(Long fourth);
	
	public Long getFifth();
	public void setFifth(Long fifth);
	
	public Long getSixth();
	public void setSixth(Long sixth);
	
	public Long getSeventh();
	public void setSeventh(Long seventh);
	
	public Long getEighth();
	public void setEighth(Long eighth);
	
	public Long getNinth();
	public void setNinth(Long ninth);
	
	// 진행된 테스트가 있는지 검사
	public ISelfTestSurvey checkProgress(String empCode) throws Exception;
	
}
