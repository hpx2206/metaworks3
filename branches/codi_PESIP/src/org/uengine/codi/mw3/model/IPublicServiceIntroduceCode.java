package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.widget.ModalWindow;

@Table(name="public_introduce_code")
public interface IPublicServiceIntroduceCode extends IDAO{
	
	@Hidden
	@Id
	public String getId();
	public void setId(String id);
	
	public String getName();
	public void setName(String name);
	
	@Hidden
	public String getParentId();
	public void setParentId(String parentId);
	
	@Hidden
	public Long getSequence();
	public void setSequence(Long sequence);
	
	@Hidden
	public String getTab();
	public void setTab(String tab);
	
	public Long findSectorSeqeunce() throws Exception;
	public Long findServiceSeqeunce() throws Exception;
	public int findTabs() throws Exception;
	
	public Long findSectorsSequenceByTab(String codeId) throws Exception;
	public Long findServicesSequenceByTab(String codeId) throws Exception;
	public Long findTabsSequence() throws Exception;
	
	public IPublicServiceIntroduceCode loadServicesByTab(String codeId) throws Exception;
	public IPublicServiceIntroduceCode loadSectorsByTab(String codeId) throws Exception;
	public IPublicServiceIntroduceCode loadTabs() throws Exception;
	
	public IPublicServiceIntroduceCode findSector() throws Exception;
	public IPublicServiceIntroduceCode findService() throws Exception;
	public IPublicServiceIntroduceCode findCurrentLastTab() throws Exception;
	public IPublicServiceIntroduceCode findTabInfo() throws Exception;
	
	public void addSector(String name, String codeId) throws Exception;
	public void addService(String name, String codeId) throws Exception;
	public void addTab(String name) throws Exception;
	
	@ServiceMethod(callByContent=true, needToConfirm=true, inContextMenu=true, target=TARGET_POPUP)
	@Face(displayName="$Delete")
	public Object[] delete() throws Exception;
	// tab을 삭제
	public void deleteTab() throws Exception;
	//tab을 기반으로 item을 삭제하므로
	public void deleteByTab(String tabId) throws Exception;
	
	// code 수정 (column, service)
	@ServiceMethod(callByContent=true, inContextMenu=true, target=TARGET_POPUP)
	@Face(displayName="$Modify")
	public ModalWindow modify() throws Exception;
	//tab 수정
	public void modifyTab() throws Exception;
	
}
