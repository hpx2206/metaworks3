package org.metaworks.example;

import javax.validation.constraints.NotNull;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Validator;
import org.metaworks.annotation.ValidatorContext;
import org.metaworks.annotation.ValidatorSet;
import org.metaworks.dao.Database;

public class Employee extends  Database<IEmployee> implements IEmployee {

	String empName;
	  public String getEmpName() {
		  return empName;
	  }
	  
	  public void setEmpName(String empName) {
		  this.empName = empName;
	  }

  
	String comName;
	  public String getComName() {
		  return comName;
	  }
	  
	  public void setComName(String comName) {
		  this.comName = comName;
	  }
	
	String password;	
	  public String getPassword() {
		  return password;
	  }
	  
	  public void setPassword(String password) {
		  this.password = password;
	  }

	@Override
	public void complete() {
		// TODO Auto-generated method stub
		
	}  




}
