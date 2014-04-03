package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Table(name="public_introduce_item")
public interface IPublicServiceIntroduceItem extends IDAO{
	
	@Hidden
	@Id
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
	
	@Hidden
	public String getServiceName();
	public void setServiceName(String serviceName);
	
	@NonLoadable
	@NonSavable
	public String getCutServiceName();
	public void setCutServiceName(String cutServiceName);
	
	@Hidden
	public String getServiceIntroduce();
	public void setServiceIntroduce(String serviceIntroduce);
	
	@Hidden
	public String getConstructTerm();
	public void setConstructTerm(String constructTerm);
	
	@Hidden
	public String getBudget();
	public void setBudget(String budget);
	
	@Hidden
	public String getServiceSummary();
	public void setServiceSummary(String serviceSummary);
	
	@Hidden
	public String getServicePurpose();
	public void setServicePurpose(String servicePurpose);
	
	@Hidden
	public String getPublicExpectation();
	public void setPublicExpectation(String publicExpectation);
	
	@Hidden
	public String getCivilianExpectation();
	public void setCivilianExpectation(String civilianExpectation);
	
	@Hidden
	public String getManagerAttachOrganization();
	public void setManagerAttachOrganization(String managerAttachOrganization);
	
	@Hidden
	public String getManagerName();
	public void setManagerName(String managerName);
	
	@Hidden
	public String getManagerNumber();
	public void setManagerNumber(String managerNumber);
	
	@Hidden
	public String getManagerEmail();
	public void setManagerEmail(String managerEmail);
	
	@Hidden
	public String getConstructCareer();
	public void setConstructCareer(String constructCareer);
	
	@Hidden
	public String getRelationService();
	public void setRelationService(String relationService);
	
	public IPublicServiceIntroduceItem loadItem(String codeId) throws Exception;
	public IPublicServiceIntroduceItem loadSelectedItem(String codeId, String sectorId, String serviceId, Long itemId) throws Exception;
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
