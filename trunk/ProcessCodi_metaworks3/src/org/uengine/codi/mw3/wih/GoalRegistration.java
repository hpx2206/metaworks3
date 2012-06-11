package org.uengine.codi.mw3.wih;

import java.io.Serializable;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.uengine.codi.ITool;

@Face(displayName="임원 성과목표 등록")
public class GoalRegistration implements ITool , Serializable {

	String instanceId;
	@Hidden
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	private String evalMonth1;
	private String evalType1;
	private String goal1;
	
	private String evalMonth2;
	private String evalType2;
	private String goal2;
	
	private String evalMonth3;
	private String evalType3;
	private String goal3;
	
	private String evalMonth4;
	private String evalType4;
	private String goal4;
	
	private String lastGoal;
	
	private String accountingGoal;
	@Face(displayName="평가월(1월)")
	public String getEvalMonth1() {
		return evalMonth1;
	}
	public void setEvalMonth1(String evalMonth1) {
		this.evalMonth1 = evalMonth1;
	}
	@Face(displayName="1월 유형")
	public String getEvalType1() {
		return evalType1;
	}
	public void setEvalType1(String evalType1) {
		this.evalType1 = evalType1;
	}
	@Face(displayName="1월 목표")
	public String getGoal1() {
		return goal1;
	}
	public void setGoal1(String goal1) {
		this.goal1 = goal1;
	}
	@Face(displayName="평가월(2월)")
	public String getEvalMonth2() {
		return evalMonth2;
	}
	public void setEvalMonth2(String evalMonth2) {
		this.evalMonth2 = evalMonth2;
	}
	@Face(displayName="2월 유형")
	public String getEvalType2() {
		return evalType2;
	}
	public void setEvalType2(String evalType2) {
		this.evalType2 = evalType2;
	}
	@Face(displayName="2월 목표")
	public String getGoal2() {
		return goal2;
	}
	public void setGoal2(String goal2) {
		this.goal2 = goal2;
	}
	@Face(displayName="평가월(3월)")
	public String getEvalMonth3() {
		return evalMonth3;
	}
	public void setEvalMonth3(String evalMonth3) {
		this.evalMonth3 = evalMonth3;
	}
	@Face(displayName="3월 유형")
	public String getEvalType3() {
		return evalType3;
	}
	public void setEvalType3(String evalType3) {
		this.evalType3 = evalType3;
	}
	@Face(displayName="3월 목표")
	public String getGoal3() {
		return goal3;
	}
	public void setGoal3(String goal3) {
		this.goal3 = goal3;
	}
	@Face(displayName="평가월(4월)")
	public String getEvalMonth4() {
		return evalMonth4;
	}
	public void setEvalMonth4(String evalMonth4) {
		this.evalMonth4 = evalMonth4;
	}
	@Face(displayName="4월 유형")
	public String getEvalType4() {
		return evalType4;
	}
	public void setEvalType4(String evalType4) {
		this.evalType4 = evalType4;
	}
	@Face(displayName="4월 목표")
	public String getGoal4() {
		return goal4;
	}
	public void setGoal4(String goal4) {
		this.goal4 = goal4;
	}
	@Face(displayName="Goal(최종목표)", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getLastGoal() {
		return lastGoal;
	}
	public void setLastGoal(String lastGoal) {
		this.lastGoal = lastGoal;
	}
	@Face(displayName="Financial Impact(재무적 성과)", ejsPath="genericfaces/richText.ejs", options={"rows","cols"}, values={"5","80"})
	public String getAccountingGoal() {
		return accountingGoal;
	}
	public void setAccountingGoal(String accountingGoal) {
		this.accountingGoal = accountingGoal;
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
