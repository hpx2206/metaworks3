package org.metaworks.website;

import javax.persistence.Table;

import org.directwebremoting.io.FileTransfer;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.Range;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.dao.IDAO;

@Table(name="Contents")
@Face(
	options={"hideAddBtn"},
	values ={"true"},
	
	ejsPathMappingByContext=
		{
			"{when: 'new', face: 'genericfaces/ObjectFace.ejs'}",
		}		

)
public interface IContents extends IDAO{

	@Id
	@Hidden
	public int getContentId();
	public void setContentId(int contentId);
	
	@Hidden
	public int getMenuId();
	public void setMenuId(int menuId);
	
	@Hidden
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
	
	@Hidden
	public String getParagraph();
	public void setParagraph(String paragraph);
	
	@Hidden
	public String getUrl();
	public void setUrl(String url);
	
	@Hidden
	public int getWidth();
	public void setWidth(int width);
	
	@Hidden
	public int getHeight();
	public void setHeight(int height);
	
	@Hidden
	@ORMapping(
		databaseFields = { 	"url", 			"paragraph" }, 
		objectFields = { 	"uploadedPath", "mimeType" }
	)
	public MetaworksFile getFile();
	public void setFile(MetaworksFile file);
	

	@ServiceMethod(callByContent=true, when=WHEN_NEW)
	public ContentPanel save() throws Exception;
	
	@ServiceMethod(when=WHEN_VIEW)
	public ContentPanel delete() throws Exception;
	
	@ServiceMethod
	public IContents newParagraph() throws Exception; //TODO: never use 'paragraph' as method name since it is consumed already by field name. we should create validator later

	@ServiceMethod
	public IContents newImage() throws Exception;

	@ServiceMethod
	public IContents newMovie() throws Exception;

	@ServiceMethod
	public IContents newFile() throws Exception;

}
