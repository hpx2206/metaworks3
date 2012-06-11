package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.ITool;
@Face(displayName="이슈등록")
public class ReportTrouble implements ITool , Serializable {

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
	private String issueClass;
	private String issueDescription;
	private String issueTitle;
    
	@Face(displayName="이슈 유형")
	public String getIssueClass() {
		return issueClass;
	}
	public void setIssueClass(String issueClass) {
		this.issueClass = issueClass;
	}
	@Face(displayName="이슈 내용", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getIssueDescription() {
		return issueDescription;
	}
	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}
	@Face(displayName="이슈 제목")
    public String getIssueTitle() {
        return issueTitle;
    }
    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }
    
	@ServiceMethod
	public void report() {
		
	}
	
	@Override
	public void onLoad() {
		
	}

	@Override
	public void beforeComplete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterComplete() {
		// TODO Auto-generated method stub
		
	}
}
