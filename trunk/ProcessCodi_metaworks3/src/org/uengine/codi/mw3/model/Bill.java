package org.uengine.codi.mw3.model;

import java.util.Date;

public class Bill {
	
	String billingType;
	
		public String getBillingType() {
			return billingType;
		}
	
		public void setBillingType(String billingType) {
			this.billingType = billingType;
		}

	double charge;
		public double getCharge() {
			return charge;
		}
	
		public void setCharge(double charge) {
			this.charge = charge;
		}
		
		
	Date issueDate;
	
		public Date getIssueDate() {
			return issueDate;
		}
	
		public void setIssueDate(Date issueDate) {
			this.issueDate = issueDate;
		}
		
	Date completionDate;

		public Date getCompletionDate() {
			return completionDate;
		}
	
		public void setCompletionDate(Date completionDate) {
			this.completionDate = completionDate;
		}
		
	ICompany company;

		public ICompany getCompany() {
			return company;
		}
	
		public void setCompany(ICompany company) {
			this.company = company;
		}
		
		
	IInstance instances;
	
	
		public IInstance getInstances() {
			return instances;
		}
	
		public void setInstances(IInstance instances) {
			this.instances = instances;
		}

	public void bill(){}
		
		
}