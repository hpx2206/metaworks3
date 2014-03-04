package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class EnterpriseInformation extends Database<IEnterpriseInformation> implements IEnterpriseInformation {

	public final static String ENTERPRISE = "enterprise";
	public final static String SERVICE = "service";
	public final static String PATENT = "patent";
	public final static String SURVEY = "survey";
	
	public EnterpriseInformation() {
		metaworksContext = new MetaworksContext();
	}
	
	String enterpriseName;
		public String getEnterpriseName() {
			return enterpriseName;
		}
		public void setEnterpriseName(String enterpriseName) {
			this.enterpriseName = enterpriseName;
		}

	String representative;
		public String getRepresentative() {
			return representative;
		}
		public void setRepresentative(String representative) {
			this.representative = representative;
		}

	String businessPart;
		public String getBusinessPart() {
			return businessPart;
		}
		public void setBusinessPart(String businessPart) {
			this.businessPart = businessPart;
		}

	String address;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}

	String foundation;
		public String getFoundation() {
			return foundation;
		}
		public void setFoundation(String foundation) {
			this.foundation = foundation;
		}

	String number;
		public String getNumber() {
			return number;
		}
		public void setNumber(String number) {
			this.number = number;
		}

	String carryon;
		public String getCarryon() {
			return carryon;
		}
		public void setCarryon(String carryon) {
			this.carryon = carryon;
		}
	
	MetaworksContext metaworksContext;
		public MetaworksContext getMetaworksContext() {
			return metaworksContext;
		}
		public void setMetaworksContext(MetaworksContext metaworksContext) {
			this.metaworksContext = metaworksContext;
		}
		
	EnterpriseService enterpriseService;
		public EnterpriseService getEnterpriseService() {
			return enterpriseService;
		}
		public void setEnterpriseService(EnterpriseService enterpriseService) {
			this.enterpriseService = enterpriseService;
		}
		
	EnterprisePatent enterprisePatent;
		public EnterprisePatent getEnterprisePatent() {
			return enterprisePatent;
		}
		public void setEnterprisePatent(EnterprisePatent enterprisePatent) {
			this.enterprisePatent = enterprisePatent;
		}
		
	SelfTestSurvey selfTestSurvey;
		public SelfTestSurvey getSelfTestSurvey() {
			return selfTestSurvey;
		}
		public void setSelfTestSurvey(SelfTestSurvey selfTestSurvey) {
			this.selfTestSurvey = selfTestSurvey;
		}
		
	@ServiceMethod(callByContent=true)
	public Object inputNextService() {
		this.getMetaworksContext().setWhen(SERVICE);
		
		if(enterpriseService == null) {
			enterpriseService = new EnterpriseService();
		}
		return this;
	}
	
	@ServiceMethod(callByContent=true)
	public Object inputNextPatent() {
		this.getMetaworksContext().setWhen(PATENT);
		
		if(enterprisePatent == null) {
			enterprisePatent = new EnterprisePatent();
		}
		return this;
	}
	
	@ServiceMethod(callByContent=true)
	public Object inputNextSurvey() {
		this.getMetaworksContext().setWhen(SURVEY);
		
		if(selfTestSurvey == null) {
			selfTestSurvey = new SelfTestSurvey();
		}
		return this;
	}
	
}
