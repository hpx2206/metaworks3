package org.metaworks.website;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Children;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.Name;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;
//
//@Face(
//		ejsPath="genericfaces/spaceTree.ejs",
//		ejsPathForArray="genericfaces/spaceTree.ejs"
//	)

@Face(
		options={"hideAddBtn"},
		values={"true"},
		ejsPathMappingByContext={"{when: 'edit', face: 'genericfaces/ObjectFace.ejs'}"}
)
public interface IMenu extends IDAO{

	@Id
	public int getMenuId();
	public void setMenuId(int menuId) ;
	
	public int getParentMenuId();
	public void setParentMenuId(int parentMenu);
	
	@Name
	public String getName();
	public void setName(String name);
	
	@NonLoadable
	@NonSavable
	@Children
	@Hidden
	public IMenu getSubMenu();
	public void setSubMenu(IMenu menu);
	
	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isSelected();
	public void setSelected(boolean selected);
	
	@ServiceMethod(callByContent=true, when="never")
	public Object[] selectMenu() throws Exception; //should avoid 'select()' since it is for IDAO sql data selection. 
	
	@ServiceMethod(callByContent=true)
	public Navigation save() throws Exception;
	
	@Children
	@ServiceMethod(target=ServiceMethodContext.TARGET_NONE, when="never")  //it just return object value only
	public IMenu loadSubMenu() throws Exception;
	
	
	
}
