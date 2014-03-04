package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.EventContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="public_introduce_item")
@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
public interface IPublicServiceIntroduceItem extends IDAO{
	
	@Id
	@Hidden
	public Long getItemId();
	public void setItemId(Long itemId);
	
	@Hidden
	public String getTab();
	public void setTab(String tab);
	
	@Hidden
	public String getServiceId();
	public void setServiceId(String serviceId);
	
	@Hidden
	public String getSectorId();
	public void setSectorId(String sectorId);
	
	@Hidden
	public Long getProfileNo();
	public void setProfileNo(Long profileNo);
	
	public String getContentName();
	public void setContentName(String contentName);
	
	@Hidden
	public String getContentDescription();
	public void setContentDescription(String contentDescription);
	
	@Hidden
	public String getGovExpectedEffect();
	public void setGovExpectedEffect(String govExpectedEffect);
	
	@Hidden
	public String getPriExpectedEffect();
	public void setPriExpectedEffect(String priExpectedEffect);
	
	@Hidden
	public String getBusinessMotivation();
	public void setBusinessMotivation(String businessMotivation);
	
	@Hidden
	public String getModelConcept();
	public void setModelConcept(String modelConcept);
	
	@Hidden
	public String getCSF();
	public void setCSF(String CSF);
	
	public IPublicServiceIntroduceItem loadItem(String codeId) throws Exception;
	public IPublicServiceIntroduceItem loadSelectedItem(String codeId, String sectorId, String serviceId, String contentName) throws Exception;
	public ArrayList<String> findContentName(String codeId, String sectorId, String serviceId) throws Exception;
	public void addContent() throws Exception;
	
	@ServiceMethod(callByContent=true, mouseBinding=ServiceMethodContext.MOUSEBINDING_LEFTCLICK, bindingHidden=true, target=TARGET_POPUP)
	@Hidden
	public Object[] showDetail() throws Exception;
	
	public void save() throws Exception;
	public void delete() throws Exception;
	public void deleteByTab(String tabId) throws Exception;
	public void deleteBySector(String sectorId) throws Exception;
	public void deleteByService(String serviceId) throws Exception;
	
}
