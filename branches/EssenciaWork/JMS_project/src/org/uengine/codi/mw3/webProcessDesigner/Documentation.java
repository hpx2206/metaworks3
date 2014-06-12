package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Documentation.ejs",
		ejsPathMappingByContext = {
			"{when:'edit', face : 'dwr/metaworks/genericfaces/FormFace.ejs'}",
			"{when:'view', face : 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Documentation.ejs'}"
		}
		, options={"fieldOrder"}
		,values={"purpose,range,reference,define,responsibility,equipment,requirement,initialCondition,notandum,step,indicationStandard,activityDetail"})
public class Documentation implements Serializable , ContextAware{
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	String purpose;
	String range;
	String reference;
	String define;
	String responsibility;
	String equipment;
	String requirement;
	String initialCondition;
	String notandum;
	String step;
	String indicationStandard;
	
	String activityDetail;
	
	@Face(displayName="목적", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Face(displayName="적용범위", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	
	@Face(displayName="참조", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
	@Face(displayName="정의", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getDefine() {
		return define;
	}
	public void setDefine(String define) {
		this.define = define;
	}

	@Face(displayName="책임", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}
	@Face(displayName="장비", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	@Face(displayName="자격요건", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getRequirement() {
		return requirement;
	}
	public void setRequirement(String requirement) {
		this.requirement = requirement;
	}
	@Face(displayName="초기조건", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getInitialCondition() {
		return initialCondition;
	}
	public void setInitialCondition(String initialCondition) {
		this.initialCondition = initialCondition;
	}
	@Face(displayName="주의사항", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getNotandum() {
		return notandum;
	}
	public void setNotandum(String notandum) {
		this.notandum = notandum;
	}
	
	@Face(displayName="절차", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getStep() {
		return step;
	}
	public void setStep(String step) {
		this.step = step;
	}
	
	@Face(displayName="판정기준", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	@Available(how={"process"})
	public String getIndicationStandard() {
		return indicationStandard;
	}
	public void setIndicationStandard(String indicationStandard) {
		this.indicationStandard = indicationStandard;
	}
	
	@Face(displayName="요약내용", ejsPath="genericfaces/richText.ejs", options = { "rows", "cols" }, values = { "10", "60" })
	public String getActivityDetail() {
		return activityDetail;
	}
	public void setActivityDetail(String activityDetail) {
		this.activityDetail = activityDetail;
	}
	
	
	public Documentation(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	
	public void init(){
//		if( "process".equals(this.getMetaworksContext().getHow()) ){
//			setPurpose(new String());
//			setReference(new String());
//			setResponsibility(new String());
//			setEquipment(new String());
//			setRequirement(new String());
//			setIndicationStandard(new String());
//			setInitialCondition(new String());
//			setNotandum(new String());
//		}
//		setActivityDetail(new String());
		
	}
		
}
