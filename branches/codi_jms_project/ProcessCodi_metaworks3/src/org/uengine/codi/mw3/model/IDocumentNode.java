package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.NonLoadable;
import org.metaworks.annotation.NonSavable;
import org.metaworks.annotation.ORMapping;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.annotation.Table;
import org.metaworks.dao.IDAO;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.processexplorer.DocumentFilePanel;
import org.uengine.codi.mw3.processexplorer.DocumentFolderPanel;

@Table(name="bpm_knol")
@Face(ejsPathForArray="dwr/metaworks/genericfaces/ListFace.ejs")
public interface IDocumentNode extends IDAO {

	@Id
	public String getId();
	public void setId(String id);
	
	public String getName();
	public void setName(String name);
	
	public String getSecuopt();
	public void setSecuopt(String secuopt);
	
	@ORMapping(databaseFields="authorId", objectFields="userId")
	public String getAuthorId();
	public void setAuthorId(String authorId);
	
	public String getType();
	public void setType(String type);
	
	public String getCompanyId();
	public void setCompanyId(String companyId);
	
	public String getDescription();
	public void setDescription(String description);
	
	public String getThumbnail();
	public void setThumbnail(String thumbnail);
	
	public String getConntype();
	public void setConntype(String conntype);
	
	public String getUrl();
	public void setUrl(String url);
	
	public Date getStartDate();
	public void setStartDate(Date startDate);
	
	public Date getEndDate();
	public void setEndDate(Date endDate);
	
	@Hidden
	public String getParentId();
	public void setParentId(String parentId);
	
	@Hidden
	@NonLoadable
	@NonSavable
	public int getLoadDepth();
	public void setLoadDepth(int loadDepth);
	
	@NonLoadable
	@NonSavable
	@Hidden(when=MetaworksContext.WHEN_EDIT)
	public ArrayList<DocumentNode> getChildNode();
	public void setChildNode(ArrayList<DocumentNode> childNode) ;
	
	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isClose();
	public void setClose(boolean close);

	@NonLoadable
	@NonSavable
	@Hidden
	public boolean isFirst();
	public void setFirst(boolean first);
	@Hidden
	public String getVisType();
	public void setVisType(String visType);
	
	public int getNo();
	public void setNo(int no);
	
	@NonLoadable
	@NonSavable
	public ArrayList<DocumentNode> getFolderList();
	public void setFolderList(ArrayList<DocumentNode> folderList);
	
	@NonLoadable
	@NonSavable
	public ArrayList<WorkItem> getFileList();
	public void setFileList(ArrayList<WorkItem> fileList);

	@ServiceMethod(inContextMenu=true, callByContent=true)
	@Face(displayName="$Remove")
	public Object[] remove() throws Exception;
	
	@ServiceMethod(inContextMenu=true,callByContent=true, target="popup")
	@Face(displayName="$Edit")
	public ModalWindow modify() throws Exception;
	
	@ServiceMethod(inContextMenu=true, target="popup")
	@Face(displayName="$addSubDocument")
	public ModalWindow addSubFolder() throws Exception;
	
	
	@ServiceMethod(callByContent=true, mouseBinding="drop", target=ServiceMethodContext.TARGET_APPEND)
	public Object[] drop() throws Exception;
	
	@Hidden
	@ServiceMethod(callByContent=true, except={"childNode"})
	public void expand() throws Exception;	
	
	@Hidden
	@ServiceMethod(callByContent=true, except={"childNode"})
	public void collapse() throws Exception;	
	
	@ServiceMethod(callByContent=true, except={"childNode"}, target=TARGET_SELF)
	public IDocumentNode loadDocumentList() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode"}, target=TARGET_APPEND)
	public Object[] loadExplorerDocument() throws Exception;
	
	@ServiceMethod(callByContent=true, except={"childNode"}, target=TARGET_SELF)
	public ArrayList<DocumentNode> loadChildren() throws Exception;
	
	@ServiceMethod(callByContent=true,target=ServiceMethodContext.TARGET_APPEND)
	public Object[] openFolderView() throws Exception;
	
	@ServiceMethod(callByContent=true)
	public Object[] loadDocument() throws Exception;
	
	@ORMapping(
			databaseFields = {"url", "thumbnail", "conntype"}, 
			objectFields = {"uploadedPath", "filename", "mimeType"},
			objectIsNullWhenFirstDBFieldIsNull = true,
			availableWhen = "type=='file'"	
			)
	public MetaworksFile getFile();
	public void setFile(MetaworksFile file);
	
	
	public void saveMe() throws Exception;
	
	public void createMe() throws Exception;
}
