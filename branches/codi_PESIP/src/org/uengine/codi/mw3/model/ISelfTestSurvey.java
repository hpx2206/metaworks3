package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_survey_evaluate")
public interface ISelfTestSurvey extends IDAO{
	
	@Id
	public String getEmpCode();
	public void setEmpCode(String empCode);
	
	// 설문조사 진행 확인
	public Long getSurveyIndex();
	public void setSurveyIndex(Long surveyIndex);
	
	// 설문조사 점수 부분
	public Long getProductScore();
	public void setProductScore(Long productScore);
	
	public Long getGlobalInfoScore();
	public void setGlobalInfoScore(Long globalInfoScore);
	
	public Long getGlobalManpowerScore();
	public void setGlobalManpowerScore(Long globalManpowerScore);
	
	public Long getGlobalActivityScore();
	public void setGlobalActivityScore(Long globalActivityScore);
	
	public Long getItCapabilityScore();
	public void setItCapabilityScore(Long itCapabilityScore);
	
	public Long getBrandMarketingScore();
	public void setBrandMarketingScore(Long brandMarketingScore);
	
	public Long getRndScore();
	public void setRndScore(Long rndScore);
	
	public Long getGlobalStrategyScore();
	public void setGlobalStrategyScore(Long globalStrategyScore);
	
	public Long getGlobalNetworkScore();
	public void setGlobalNetworkScore(Long globalNetworkScore);
	
	// 진행된 테스트가 있는지 검사
	public ISelfTestSurvey checkProgress(String empCode) throws Exception;
	
}
