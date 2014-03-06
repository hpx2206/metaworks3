package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.Database;

public class EnterpriseInformation extends Database<IEnterpriseInformation> implements IEnterpriseInformation {

	public final static String ENTERPRISE = "enterprise";
	
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
	
}
