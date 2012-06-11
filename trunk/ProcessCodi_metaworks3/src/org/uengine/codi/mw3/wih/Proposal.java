package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
@Face(displayName="마케팅 계획")
public class Proposal implements ITool , Serializable {

	String instanceId;
	@Hidden
		public String getInstanceId() {
			return instanceId;
		}
		public void setInstanceId(String instanceId) {
			this.instanceId = instanceId;
		}
		
	
	private String promote;
	private String type;
	private String estimatedCost;
	private String goal;
	private String productNtype;
	private String evaluationIndicators;
	private String promoteGoal;
		
	@Face(displayName="홍보수단")
	public String getPromote() {
		return promote;
	}
	public void setPromote(String promote) {
		this.promote = promote;
	}
	@Face(displayName="유형")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Face(displayName="예상비용")
	public String getEstimatedCost() {
		return estimatedCost;
	}
	public void setEstimatedCost(String estimatedCost) {
		this.estimatedCost = estimatedCost;
	}
	@Face(displayName="목표")
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	@Face(displayName="마케팅대상 및 제품")
	public String getProductNtype() {
		return productNtype;
	}
	public void setProductNtype(String productNtype) {
		this.productNtype = productNtype;
	}
	@Face(displayName="효과 및 평가지표")
	public String getEvaluationIndicators() {
		return evaluationIndicators;
	}
	public void setEvaluationIndicators(String evaluationIndicators) {
		this.evaluationIndicators = evaluationIndicators;
	}
	@Face(displayName="광고목표")
	public String getPromoteGoal() {
		return promoteGoal;
	}
	public void setPromoteGoal(String promoteGoal) {
		this.promoteGoal = promoteGoal;
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
