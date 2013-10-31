package org.uengine.codi.mw3.model;

import org.metaworks.dao.Database;

public class Company extends Database<ICompany> implements ICompany {
	String comCode;
	String comName;
	String description;
	String isDeleted;
	String repMail;
	
	
	public String getRepMail() {
		return repMail;
	}

	public void setRepMail(String repMail) {
		this.repMail = repMail;
	}

	public String getRepMlHst() {
		return repMlHst;
	}

	public void setRepMlHst(String repMlHst) {
		this.repMlHst = repMlHst;
	}

	public String getRepMlPwd() {
		return repMlPwd;
	}

	public void setRepMlPwd(String repMlPwd) {
		this.repMlPwd = repMlPwd;
	}

	String repMlHst;
	String repMlPwd;

	public String getComCode() {
		return comCode;
	}

	public void setComCode(String comCode) {
		this.comCode = comCode;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public ICompany load() throws Exception {
		if (getComCode() != null) {
			ICompany company = (ICompany) databaseMe();
			return company;
		}
		return null;
	}

	@Override
	public void save() throws Exception {
		syncToDatabaseMe();
	}
}
