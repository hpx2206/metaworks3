package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name = "assessment")
public interface IAssessment extends IDAO {

	
	
	@Id
	@Hidden
	public String getEmpCode();
	public void setEmpCode(String empCode);
	
	public int getPerformance();
	public void setPerformance(int performance);
	
	public int getReliability();
	public void setReliability(int reliability);
	
	public int getIntegrity();
	public void setIntegrity(int integrity);
	
	public String getUserName() ;
	public void setUserName(String userName);
}
