package org.uengine.codi.mw3.webProcessDesigner;

import java.io.Serializable;

import org.metaworks.ContextAware;
import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.admin.WebEditor;

@Face(ejsPath="dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Documentation.ejs",
		ejsPathMappingByContext = {
			"{when:'edit', face : 'dwr/metaworks/genericfaces/FormFace.ejs'}",
			"{when:'view', face : 'dwr/metaworks/org/uengine/codi/mw3/webProcessDesigner/Documentation.ejs'}"
		}
		, options={"fieldOrder"}
		,values={"purpose,reference,responsibility,equipment,requirement,indicationStandard,initialCondition,notandum,activityDetail"})
public class Documentation implements Serializable , ContextAware{
	transient MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	WebEditor purpose;
	WebEditor reference;
	WebEditor responsibility;
	WebEditor equipment;
	WebEditor requirement;
	WebEditor indicationStandard;
	WebEditor initialCondition;
	WebEditor notandum;
	
	WebEditor activityDetail;
	
	@Face(displayName="목적")
	@Available(how={"process"})
	public WebEditor getPurpose() {
		return purpose;
	}
	public void setPurpose(WebEditor purpose) {
		this.purpose = purpose;
	}
	@Face(displayName="참조")
	@Available(how={"process"})
	public WebEditor getReference() {
		return reference;
	}
	public void setReference(WebEditor reference) {
		this.reference = reference;
	}
	@Face(displayName="책임")
	@Available(how={"process"})
	public WebEditor getResponsibility() {
		return responsibility;
	}
	public void setResponsibility(WebEditor responsibility) {
		this.responsibility = responsibility;
	}
	@Face(displayName="장비")
	@Available(how={"process"})
	public WebEditor getEquipment() {
		return equipment;
	}
	public void setEquipment(WebEditor equipment) {
		this.equipment = equipment;
	}
	@Face(displayName="자격요건")
	@Available(how={"process"})
	public WebEditor getRequirement() {
		return requirement;
	}
	public void setRequirement(WebEditor requirement) {
		this.requirement = requirement;
	}
	@Face(displayName="판정기준")
	@Available(how={"process"})
	public WebEditor getIndicationStandard() {
		return indicationStandard;
	}
	public void setIndicationStandard(WebEditor indicationStandard) {
		this.indicationStandard = indicationStandard;
	}
	@Face(displayName="초기조건")
	@Available(how={"process"})
	public WebEditor getInitialCondition() {
		return initialCondition;
	}
	public void setInitialCondition(WebEditor initialCondition) {
		this.initialCondition = initialCondition;
	}
	@Face(displayName="주의사항")
	@Available(how={"process"})
	public WebEditor getNotandum() {
		return notandum;
	}
	public void setNotandum(WebEditor notandum) {
		this.notandum = notandum;
	}
	
	@Face(displayName="요약내용")
	public WebEditor getActivityDetail() {
		return activityDetail;
	}
	public void setActivityDetail(WebEditor activityDetail) {
		this.activityDetail = activityDetail;
	}
	
	
	public Documentation(){
		setMetaworksContext(new MetaworksContext());
		getMetaworksContext().setWhen("edit");
	}
	
	public void init(){
		if( "process".equals(this.getMetaworksContext().getHow()) ){
			setPurpose(new WebEditor());
			setReference(new WebEditor());
			setResponsibility(new WebEditor());
			setEquipment(new WebEditor());
			setRequirement(new WebEditor());
			setIndicationStandard(new WebEditor());
			setInitialCondition(new WebEditor());
			setNotandum(new WebEditor());
		}
		setActivityDetail(new WebEditor());
		
	}
		
}
