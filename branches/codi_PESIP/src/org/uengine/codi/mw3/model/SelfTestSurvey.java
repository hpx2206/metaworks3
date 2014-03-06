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
	Long productScore;
		public Long getProductScore() {
			return productScore;
		}
		public void setProductScore(Long productScore) {
			this.productScore = productScore;
		}
	
	Long globalInfoScore;
		public Long getGlobalInfoScore() {
			return globalInfoScore;
		}
		public void setGlobalInfoScore(Long globalInfoScore) {
			this.globalInfoScore = globalInfoScore;
		}
		
	Long globalManpowerScore;
		public Long getGlobalManpowerScore() {
			return globalManpowerScore;
		}
		public void setGlobalManpowerScore(Long globalManpowerScore) {
			this.globalManpowerScore = globalManpowerScore;
		}

	Long globalActivityScore;
		public Long getGlobalActivityScore() {
			return globalActivityScore;
		}
		public void setGlobalActivityScore(Long globalActivityScore) {
			this.globalActivityScore = globalActivityScore;
		}

	Long itCapabilityScore;
		public Long getItCapabilityScore() {
			return itCapabilityScore;
		}
		public void setItCapabilityScore(Long itCapabilityScore) {
			this.itCapabilityScore = itCapabilityScore;
		}

	Long brandMarketingScore;
		public Long getBrandMarketingScore() {
			return brandMarketingScore;
		}
		public void setBrandMarketingScore(Long brandMarketingScore) {
			this.brandMarketingScore = brandMarketingScore;
		}

	Long rndScore;
		public Long getRndScore() {
			return rndScore;
		}
		public void setRndScore(Long rndScore) {
			this.rndScore = rndScore;
		}

	Long globalStrategyScore;
		public Long getGlobalStrategyScore() {
			return globalStrategyScore;
		}
		public void setGlobalStrategyScore(Long globalStrategyScore) {
			this.globalStrategyScore = globalStrategyScore;
		}

	Long globalNetworkScore;
		public Long getGlobalNetworkScore() {
			return globalNetworkScore;
		}
		public void setGlobalNetworkScore(Long globalNetworkScore) {
			this.globalNetworkScore = globalNetworkScore;
		}
		
	public ISelfTestSurvey checkProgress(String empCode) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select survey_index");
		sb.append(" from pseip_survey_evaluate");
		sb.append(" where empcode = ?empcode");
		
		ISelfTestSurvey survey = (ISelfTestSurvey) sql(ISelfTestSurvey.class, sb.toString());
		survey.setEmpCode(empCode);
		survey.select();
		
		return survey;
	}
	
	public void saveProduct(int sumScore) throws Exception {
	}
	
}
