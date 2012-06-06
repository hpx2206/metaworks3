package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
import org.uengine.codi.mw3.model.User;

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
    
    public String getIssueResult() {
		return issueResult;
	}
	public void setIssueResult(String issueResult) {
		this.issueResult = issueResult;
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
