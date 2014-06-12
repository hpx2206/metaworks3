package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Id;
import org.metaworks.dao.IDAO;

public interface ILikeItem extends IDAO{
	
	@Id
	public Long getInstId();
	public void setInstId(Long instId);
	
	public String getEmpCode();
	public void setEmpCode(String empCode);
	
	public int getChecked();
	public void setChecked(int checked);
	
	public ILikeItem checkEmpLikeClick() throws Exception;
	public void addLikeInstance() throws Exception;
	public void findLikeCount() throws Exception;
}
