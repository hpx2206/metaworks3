package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.User;
@Face(displayName="처리결과입력")
public class WriteResolution implements ITool , Serializable{

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
	
    private String issueResult;
    private String completeDate;
    
    @Face(displayName="처리결과", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
    public String getIssueResult() {
		return issueResult;
	}
	public void setIssueResult(String issueResult) {
		this.issueResult = issueResult;
	}
	@Face(displayName="처리일시")
    public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	@Override
	public void onLoad() {
        // TODO Auto-generated method stub
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		// setContinueYnVar(continueYn == true ? "Y" : "N");
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub	
		
	}
}
