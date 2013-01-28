package org.uengine.codi.mw3.webProcessDesigner;

import java.util.Date;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;

public class ConditionInput implements ContextAware {
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String expressionText;
		public String getExpressionText() {
			return expressionText;
		}
		public void setExpressionText(String expressionText) {
			this.expressionText = expressionText;
		}
		
	Date expressionDate;
		public Date getExpressionDate() {
			return expressionDate;
		}
		public void setExpressionDate(Date expressionDate) {
			this.expressionDate = expressionDate;
		}	
		
	String yesNo;
	    @Range(options={"yes","no"},values={"yes","no"})
		public String getYesNo() {
			return yesNo;
		}
		public void setYesNo(String yesNo) {
			this.yesNo = yesNo;
		}
		
	String changeType;
		public String getChangeType() {
			return changeType;
		}
		public void setChangeType(String changeType) {
			this.changeType = changeType;
		}
		
	public ConditionInput(){
		this.setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	public void init(){
		expressionDate = new Date();
		
	}
	public void load() throws Exception{
		
	}
		
	@ServiceMethod( payload={"changeType"} )
	public void changeInput() throws Exception{
		this.getMetaworksContext().setHow(changeType);
	}
}
