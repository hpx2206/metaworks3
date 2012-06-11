package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.IUser;
import org.uengine.codi.mw3.model.User;

@Face(displayName="분석결과확인")
public class CheckAnalysisResult implements ITool , Serializable{

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
    MetaworksContext metaworksContext;
        public MetaworksContext getMetaworksContext(){
            return this.metaworksContext;
        }
        public void setMetaworksContext(MetaworksContext metaworksContext){
            this.metaworksContext = metaworksContext;
        }
        
    boolean compYn;
    @Face(displayName="계속 진행 여부")
    public boolean isCompYn() {
		return compYn;
	}
	public void setCompYn(boolean compYn) {
		this.compYn = compYn;
	}

	String compYnVar;
	@Hidden
	public String getCompYnVar() {
		return compYnVar;
	}
	public void setCompYnVar(String compYnVar) {
		this.compYnVar = compYnVar;
	}
	
	String comment;
	@Face(displayName="코멘트", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public void onLoad() {
        this.metaworksContext = new MetaworksContext();
        metaworksContext.setWhen(MetaworksContext.WHEN_NEW);
		// TODO Auto-generated method stub
        
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		setCompYnVar(compYn == true ? "Y" : "N");
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
}
