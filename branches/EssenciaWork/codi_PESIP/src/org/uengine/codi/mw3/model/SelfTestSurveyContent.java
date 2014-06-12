package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class SelfTestSurveyContent extends Database<ISelfTestSurveyContent> implements ISelfTestSurveyContent{

	String itemId;
		@Id
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
	
	Long itemType;
		public Long getItemType() {
			return itemType;
		}
		public void setItemType(Long itemType) {
			this.itemType = itemType;
		}

	String itemContent;
		public String getItemContent() {
			return itemContent;
		}
		public void setItemContent(String itemContent) {
			this.itemContent = itemContent;
		}

	public ISelfTestSurveyContent findSurvey(Long itemType) throws Exception {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select item_content as itemContent");
		sb.append(" from pseip_survey_content where item_type=?itemType");
		
		ISelfTestSurveyContent surveyContent = (ISelfTestSurveyContent) sql(ISelfTestSurveyContent.class, sb.toString());
		surveyContent.setItemType(itemType);
		surveyContent.select();
		
		return surveyContent;
	}
	
}
