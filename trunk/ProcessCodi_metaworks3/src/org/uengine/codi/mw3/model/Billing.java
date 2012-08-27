package org.uengine.codi.mw3.model;

import java.util.ArrayList;

public class Billing {
	
	String billingType;
	
	ArrayList<Bill> billingHistory;
	
	ICompany company;

		public ICompany getCompany() {
			return company;
		}
	
		public void setCompany(ICompany company) {
			this.company = company;
		}

}