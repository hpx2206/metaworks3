package org.metaworks.website;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

public interface IContents extends IDAO{

	@Id
	public int getContentId();
	public void setContentId(int contentId);
	
	public int getMenuId();
	public void setMenuId(int menuId);
	
	
	@Range(
			options={"Paragraph",	"Image",	"Movie",	"Source Code", 	"File"}, 
			values ={"p",			"img",		"mov",		"src", 			"file"}
	)
	public String getType();
	public void setType(String type);
	
	@Face(ejsPath="genericfaces/richText.ejs",
			options={"cols",	"rows"},
			values ={"30", 		"10"}
	)
	public String getParagraph();
	public void setParagraph(String paragraph);
	
	public String getUrl();
	public void setUrl(String url);
	
	public int getWidth();
	public void setWidth(int width);
	
	public int getHeight();
	public void setHeight(int height);
	
	@ORMapping(
		databaseFields = { 	"url", 			"paragraph" }, 
		objectFields = { 	"uploadedPath", "mimeType" }
	)
	public MetaworksFile getFile();
	public void setFile(MetaworksFile file);
	

	@ServiceMethod(callByContent=true)
	public ContentPanel save() throws Exception;
	
	@ServiceMethod
	public ContentPanel delete() throws Exception;
	
}
