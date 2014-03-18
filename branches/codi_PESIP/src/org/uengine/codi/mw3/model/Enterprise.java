package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.dao.Database;

public class Enterprise extends Database<IEnterprise> implements IEnterprise {

	public final static String ENTERPRISE = "enterprise";
	
	String globalCom;
		@Id
		public String getGlobalCom() {
			return globalCom;
		}
		public void setGlobalCom(String globalCom) {
			this.globalCom = globalCom;
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
	
	String carryOn;
		public String getCarryOn() {
			return carryOn;
		}
		public void setCarryOn(String carryOn) {
			this.carryOn = carryOn;
		}
	
	String fax;
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
	
	public void saveEnterprise() throws Exception {
		this.createDatabaseMe();
	}
	
	public void modifyEnterprise() throws Exception {
		this.syncToDatabaseMe();
	}
	
	public IEnterprise findEnterprise(String globalCom) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("select *");
		sb.append(" from enterprise");
		sb.append(" where globalcom = ?globalcom");
		
		IEnterprise enterprise = (IEnterprise) sql(IEnterprise.class, sb.toString());
		enterprise.setGlobalCom(globalCom);
		enterprise.select();
		
		return enterprise;
	}
}
