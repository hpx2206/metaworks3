package org.uengine.codi.mw3.ide.dictionary;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="dictionary")
public interface IDictionaryItem extends IDAO{
	
	
	@Id
	public int getDicId();
	public void setDicId(int dicId);
	
	
	public String getDicType();
	public void setDicType(String dicType);
	
	public String getDicName();
	public void setDicName(String dicName);
	
	public String getDicDescription();
	public void setDicDescription(String dicDescription);
	
	public String getDicLinked();
	public void setDicLinked(String dicLinked);
	
	public String getStartDate();
	public void setStartDate(String startDate);
	
	public String getModDate();
	public void setModDate(String modDate);

	@ServiceMethod(callByContent = true, target=ServiceMethodContext.TARGET_APPEND)
	public void modify() throws Exception;
	
	@ServiceMethod
	public void delete() throws Exception;
	
}
