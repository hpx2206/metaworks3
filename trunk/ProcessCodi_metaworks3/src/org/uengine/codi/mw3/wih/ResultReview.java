package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

@Face(displayName="$RR_TITLE")
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
	@Face(displayName="$RR_TEAM_OPINION", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getTeamReview() {
		return teamReview;
	}
	public void setTeamReview(String teamReview) {
		this.teamReview = teamReview;
	}
	@Face(displayName="$RR_DIRECTOR_OPINION", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getManagerReview() {
		return managerReview;
	}
	public void setManagerReview(String managerReview) {
		this.managerReview = managerReview;
	}
	
	boolean compYn;
    @Face(displayName="$RR_APPROVAL")
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
