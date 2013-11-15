package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Test;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;

public interface IProcessMap extends IDAO {

	
	@Id
	public String getMapId();
	public void setMapId(String mapId);

	public String getDefId();
	public void setDefId(String defId);

	@Name
	public String getName();
	public void setName(String name);

	public String getCmPhrase();
	public void setCmPhrase(String cmPhrase);
	
	public String getCmTrgr();
	public void setCmTrgr(String cmTrgr);

	public int getNo();
	public void setNo(int no);
	
	public String getComCode();
	public void setComCode(String comCode);

	@NonSavable
	@NonLoadable
	public RoleMappingPanel getRoleMappingPanel();
	public void setRoleMappingPanel(RoleMappingPanel roleMappingPanel);
	
	@ORMapping(databaseFields={"iconPath"}, objectFields={"uploadedPath"})
	public MetaworksFile getIconFile();
	public void setIconFile(MetaworksFile iconFile);

	@ORMapping(databaseFields={"color"}, objectFields={"value"})
	public ProcessMapColor getIconColor();
	public void setIconColor(ProcessMapColor color);
	
	@ServiceMethod(callByContent=true, when=WHEN_VIEW, target=TARGET_POPUP)
	public Popup modify() throws Exception;
	
	@ServiceMethod(callByContent=true, when=WHEN_VIEW)
	public Object[] remove() throws Exception;

	@ServiceMethod(callByContent=true, when=WHEN_EDIT, target=ServiceMethodContext.TARGET_APPEND)
	public Object[] save() throws Exception;
	
	@Available(when={WHEN_NEW})
	@ServiceMethod
	public Remover close() throws Exception;
	
	@ServiceMethod(payload={"defId", "name"} , target=ServiceMethodContext.TARGET_APPEND) 
	@Test(scenario="first", starter=true, instruction="$first.ProcessStart", next="autowiredObject.org.uengine.codi.mw3.model.CommentWorkItem@-1.add()")
//	@Test(scenario="first", starter=true, instruction="Issue Tracking 프로세스를 선택합니다.", next="autowiredObject.org.uengine.codi.mw3.admin.PageNavigator.goKnowledge()")
	public Object[] initiate() throws Exception;
	
	@ServiceMethod(payload="defId") 
	public Object[] processListFilter() throws Exception;
	
	public void createMe() throws Exception;
	public void saveMe() throws Exception;
	
	public boolean confirmExist();
}
