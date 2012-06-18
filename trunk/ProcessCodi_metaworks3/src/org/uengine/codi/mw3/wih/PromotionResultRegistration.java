package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

@Face(displayName="$PRR_TITLE")
public class PromotionResultRegistration implements ITool , Serializable {

	String instanceId;
	@Hidden
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	private String type1;
	private String goals1;
	private String performance1;
	private String performanceRate1;
	private String bigo1;
	
	private String type2;
	private String goals2;
	private String performance2;
	private String performanceRate2;
	private String bigo2;
	
	private String type3;
	private String goals3;
	private String performance3;
	private String performanceRate3;
	private String bigo3;
	
	@Face(displayName="$PRR_TYPE1")
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	@Face(displayName="$PRR_GOAL1")
	public String getGoals1() {
		return goals1;
	}
	public void setGoals1(String goals1) {
		this.goals1 = goals1;
	}
	@Face(displayName="$PRR_PERFORMANCE1")
	public String getPerformance1() {
		return performance1;
	}
	public void setPerformance1(String performance1) {
		this.performance1 = performance1;
	}
	@Face(displayName="$PRR_PERFORMANCE_RATE1")
	public String getPerformanceRate1() {
		return performanceRate1;
	}
	public void setPerformanceRate1(String performanceRate1) {
		this.performanceRate1 = performanceRate1;
	}
	@Face(displayName="$PRR_DESC1")
	public String getBigo1() {
		return bigo1;
	}
	public void setBigo1(String bigo1) {
		this.bigo1 = bigo1;
	}
	@Face(displayName="$PRR_TYPE2")
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
	}
	@Face(displayName="$PRR_GOAL2")
	public String getGoals2() {
		return goals2;
	}
	public void setGoals2(String goals2) {
		this.goals2 = goals2;
	}
	@Face(displayName="$PRR_PERFORMANCE2")
	public String getPerformance2() {
		return performance2;
	}
	public void setPerformance2(String performance2) {
		this.performance2 = performance2;
	}
	@Face(displayName="$PRR_PERFORMANCE_RATE2")
	public String getPerformanceRate2() {
		return performanceRate2;
	}
	public void setPerformanceRate2(String performanceRate2) {
		this.performanceRate2 = performanceRate2;
	}
	@Face(displayName="$PRR_DESC2")
	public String getBigo2() {
		return bigo2;
	}
	public void setBigo2(String bigo2) {
		this.bigo2 = bigo2;
	}
	@Face(displayName="$PRR_TYPE3")
	public String getType3() {
		return type3;
	}
	public void setType3(String type3) {
		this.type3 = type3;
	}
	@Face(displayName="$PRR_GOAL3")
	public String getGoals3() {
		return goals3;
	}
	public void setGoals3(String goals3) {
		this.goals3 = goals3;
	}
	@Face(displayName="$PRR_PERFORMANCE3")
	public String getPerformance3() {
		return performance3;
	}
	public void setPerformance3(String performance3) {
		this.performance3 = performance3;
	}
	@Face(displayName="$PRR_PERFORMANCE_RATE3")
	public String getPerformanceRate3() {
		return performanceRate3;
	}
	public void setPerformanceRate3(String performanceRate3) {
		this.performanceRate3 = performanceRate3;
	}
	@Face(displayName="$PRR_DESC3")
	public String getBigo3() {
		return bigo3;
	}
	public void setBigo3(String bigo3) {
		this.bigo3 = bigo3;
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
