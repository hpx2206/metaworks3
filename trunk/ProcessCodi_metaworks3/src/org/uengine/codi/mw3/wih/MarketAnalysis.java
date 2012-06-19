package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;
@Face(displayName="$MA_TITLE")
public class MarketAnalysis implements ITool , Serializable {

	String instanceId;
	@Hidden
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	private String promote;
	private String cost;
	private String startDate;
	private String endDate;
	
	private String product;
	
	private String goal;
	private String performance;
	private String evaluationIndicators;
	private String analysisResult;
	
	
	@Face(displayName="$MA_PROMOTE_TYPE")
	public String getPromote() {
		return promote;
	}
	public void setPromote(String promote) {
		this.promote = promote;
	}
	@Face(displayName="$MA_COST")
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	@Face(displayName="$MA_START_DATE")
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Face(displayName="$MA_END_DATE")
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Face(displayName="$MA_PRODUCT")
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	@Face(displayName="$MA_GOAL", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	@Face(displayName="$MA_PERFORMANCE")
	public String getPerformance() {
		return performance;
	}
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	@Face(displayName="$MA_EVALUATION_INDICATORS")
	public String getEvaluationIndicators() {
		return evaluationIndicators;
	}
	public void setEvaluationIndicators(String evaluationIndicators) {
		this.evaluationIndicators = evaluationIndicators;
	}
	@Face(displayName="$MA_ANALYSIS_RESULT", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getAnalysisResult() {
		return analysisResult;
	}
	public void setAnalysisResult(String analysisResult) {
		this.analysisResult = analysisResult;
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
