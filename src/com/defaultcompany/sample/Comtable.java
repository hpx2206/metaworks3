package com.defaultcompany.sample;

import org.metaworks.dao.Database;

public class Comtable extends Database<IComtable> implements IComtable {
	String comCode;
	String comName;
	String description;
	String isDeleted;
	
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
	
	public static IComtable list() throws Exception {
		IComtable mainComTableList = (IComtable) sql(IComtable.class,
				"select * from comtable order by comcode desc");
		mainComTableList.select();
		return mainComTableList;
	}
}
