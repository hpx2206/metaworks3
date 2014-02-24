package org.uengine.codi.mw3.model;

import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;

@Face(ejsPath="dwr/metaworks/genericfaces/CleanObjectFace.ejs")
@Table(name="public_introduce_code")
public interface IPublicServiceIntroduceCode extends IDAO{
	
	@Id
	@Hidden
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
	
	public int findSectors() throws Exception;
	public int findServices() throws Exception;
	public int findTabs() throws Exception;
	
	public Long findSectorsSequenceByTab(String codeId) throws Exception;
	public Long findServicesSequenceByTab(String codeId) throws Exception;
	public Long findTabsSequence() throws Exception;
	
	public IPublicServiceIntroduceCode loadServicesByTab(String codeId) throws Exception;
	public IPublicServiceIntroduceCode loadSectorsByTab(String codeId) throws Exception;
	public IPublicServiceIntroduceCode loadTabs() throws Exception;
	public IPublicServiceIntroduceCode findCurrentLastTab() throws Exception;
	
	public void addSector(String name, String codeId) throws Exception;
	public void addService(String name, String codeId) throws Exception;
	public void addTab(String name) throws Exception;
	
	
}
