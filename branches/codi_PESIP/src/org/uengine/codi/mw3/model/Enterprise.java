package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.dao.Database;

public class Enterprise extends Database<IEnterprise> implements IEnterprise {

	public Enterprise() {
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

	String Fax;
		public String getFax() {
			return Fax;
		}
		public void setFax(String fax) {
			Fax = fax;
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
		
}
