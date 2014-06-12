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
	
	// 설문조사를 찾아온다. itemType으로
	public ISelfTestSurveyContent findSurvey(Long itemType) throws Exception;
}
