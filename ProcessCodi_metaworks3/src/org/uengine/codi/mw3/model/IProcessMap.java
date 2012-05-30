package org.uengine.codi.mw3.model;

import org.metaworks.Remover;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;

public interface IProcessMap extends IDAO {

	@Id
	public String getDefId();
	public void setDefId(String defId);

	@Name
	public String getName();
	public void setName(String name);

	@ORMapping(databaseFields={"iconPath"}, objectFields={"uploadedPath"})
	public MetaworksFile getIconFile();
	public void setIconFile(MetaworksFile iconFile);

	@ORMapping(databaseFields={"color"}, objectFields={"value"})
	public ProcessMapColor getIconColor();
	public void setIconColor(ProcessMapColor color);
	
	@ServiceMethod(callByContent=true, when=WHEN_NEW)
	public Object[] create() throws Exception;

	@ServiceMethod(callByContent=true, when=WHEN_VIEW, inContextMenu=true, target=TARGET_POPUP)
	public Popup modify() throws Exception;

	@ServiceMethod(callByContent=true, when=WHEN_EDIT)
	public Object[] save() throws Exception;
	
	@Available(when={WHEN_NEW})
	@ServiceMethod
	public Remover close() throws Exception;
	
	public boolean confirmExist();
}
