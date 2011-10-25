package org.metaworks.website;

import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IMenu extends IDAO{

	@Id
	public int getMenuId();
	public void setMenuId(int menuId) ;
	
	public int getParentMenuId();
	public void setParentMenuId(int parentMenu);
	
	public String getName();
	public void setName(String name);
	
	@NonLoadable
	@NonSavable
	public IMenu getSubMenu();
	public void setSubMenu(IMenu menu);
	
	@NonLoadable
	@NonSavable
	public boolean isSelected();
	public void setSelected(boolean selected);
	
	@ServiceMethod(callByContent=true)
	public Object[] selectMenu() throws Exception; //should avoid 'select()' since it is for IDAO sql data selection. 
	
	@ServiceMethod(callByContent=true)
	public Navigation save() throws Exception;
}
