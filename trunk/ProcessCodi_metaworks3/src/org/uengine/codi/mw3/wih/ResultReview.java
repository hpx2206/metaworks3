package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

@Face(displayName="결과리뷰")
public class ResultReview implements ITool , Serializable {
	
	String instanceId;
	@Hidden
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	private String teamReview;
	private String managerReview;
	@Face(displayName="팀장평가의견", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getTeamReview() {
		return teamReview;
	}
	public void setTeamReview(String teamReview) {
		this.teamReview = teamReview;
	}
	@Face(displayName="부서장의견", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getManagerReview() {
		return managerReview;
	}
	public void setManagerReview(String managerReview) {
		this.managerReview = managerReview;
	}
	
	boolean compYn;
    @Face(displayName="승인여부")
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
	@Override
	public void onLoad() {
		
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
