package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

public class Proposal implements ITool , Serializable {

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
	private String proposalClass;
	private String proposalDescription;
	private String proposalTitle;
	public String getProposalClass() {
		return proposalClass;
	}
	public void setProposalClass(String proposalClass) {
		this.proposalClass = proposalClass;
	}
	public String getProposalDescription() {
		return proposalDescription;
	}
	public void setProposalDescription(String proposalDescription) {
		this.proposalDescription = proposalDescription;
	}
	public String getProposalTitle() {
		return proposalTitle;
	}
	public void setProposalTitle(String proposalTitle) {
		this.proposalTitle = proposalTitle;
	}
	@Override
	public void onLoad() {
		// TODO Auto-generated method stub
		
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
