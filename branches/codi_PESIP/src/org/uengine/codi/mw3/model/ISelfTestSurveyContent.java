package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="pseip_survey_content")
public interface ISelfTestSurveyContent extends IDAO {
	
	@Id
	public String getItemId();
	public void setItemId(String itemId);
	
	public Long getItemType();
	public void setItemType(Long itemType);
	
	public String getItemContent();
	public void setItemContent(String itemContent);
	
	// 첫 번째 설문 찾기
	public ISelfTestSurveyContent findFirstSurvey(Long itemType) throws Exception;
}
