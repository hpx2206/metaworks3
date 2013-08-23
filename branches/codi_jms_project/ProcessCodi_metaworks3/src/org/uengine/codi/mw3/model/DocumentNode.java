package org.uengine.codi.mw3.model;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Available;
import org.metaworks.annotation.Face;
import org.metaworks.dao.DAOUtil;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.knowledge.WfNode;

public class DocumentNode extends Database<IDocumentNode> implements IDocumentNode{
	@AutowiredFromClient
	transient public Session session;
	
	public final static String TYPE = "doc";
	public final static int DEPTH = 2;

	String id;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		
	String parentId;			
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}		
	String secuopt;
		public String getSecuopt() {
			return secuopt;
		}
		public void setSecuopt(String secuopt) {
			this.secuopt = secuopt;
		}
	String authorId;
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
	public int loadDepth = 0;
		public int getLoadDepth() {
			return loadDepth;
		}
		public void setLoadDepth(int loadDepth) {
			this.loadDepth = loadDepth;
		}	
	String url;
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
	String thumbnail;
		public String getThumbnail() {
			return thumbnail;
		}
		public void setThumbnail(String thumbnail) {
			this.thumbnail = thumbnail;
		}

	String conntype;
		public String getConntype() {
			return conntype;
		}
		public void setConntype(String conntype) {
			this.conntype = conntype;
		}

	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	
	String companyId;
		public String getCompanyId() {
			return companyId;
		}
		public void setCompanyId(String companyId) {
			this.companyId = companyId;
		}
		
	String description;
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

	String userId;
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
	
	String userName;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		
	String folderId;
		public String getFolderId() {
			return folderId;
		}
		public void setFolderId(String folderId) {
			this.folderId = folderId;
		}
	ArrayList<DocumentNode> childNode;
		public ArrayList<DocumentNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<DocumentNode> childNode) {
			this.childNode = childNode;
		}

	boolean first;
		public boolean isFirst() {
			return first;
		}
		public void setFirst(boolean first) {
			this.first = first;
		}
	
	boolean close;
		public boolean isClose() {
			return close;
		}
		public void setClose(boolean close) {
			this.close = close;
		}	
	String visType;
		public String getVisType() {
			return visType;
		}
		public void setVisType(String visType) {
			this.visType = visType;
		}
		@AutowiredFromClient
	public PageNavigator pageNavigator; 
		
	MetaworksFile file;
		public MetaworksFile getFile() {
			return file;
		}
		public void setFile(MetaworksFile file) {
			this.file = file;
		}	
	public DocumentNode(){
		setChildNode(new ArrayList<DocumentNode>());
		setFile(new MetaworksFile()); 
		getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	
	public IDocumentNode loadDocumentList() throws Exception{
		
		DAOUtil daoUtil = new DAOUtil();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol knol");
		sb.append(" where knol.type = ?type");
		sb.append(" and parentid = ?parentid");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
		sb.append(" order by "  + daoUtil.replaceReservedKeyword("no"));
		
		
		IDocumentNode dao = (IDocumentNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(),sb.toString(),IDocumentNode.class);
		dao.set("type", TYPE);
		dao.set("parentid", "Main");
		dao.select();
		
		return dao;
	}
	
	public ArrayList<DocumentNode> loadChildren() throws Exception{
		childNode = new ArrayList<DocumentNode>();
		IDocumentNode parentNode = loadDocumentList();
		if(parentNode.size() > 0){
			while(parentNode.next()){
				
				DocumentNode documentNode = new DocumentNode();
				
				documentNode.copyFrom(parentNode);
				documentNode.setChildNode(new ArrayList<DocumentNode>());

				documentNode.setMetaworksContext(this.getMetaworksContext());
				
				documentNode.setLoadDepth(this.getLoadDepth());
				documentNode.setClose(true);
				documentNode.setFirst(this.isFirst());
				
				if(!this.isFirst())
					documentNode.load();
				
				childNode.add(documentNode); 
				
			}
		}
		return childNode;
	}
	public void expand() throws Exception{
		this.setLoadDepth(2);
		this.setChildNode(loadChildren());
		
		setClose(false);
	}
	
	public void collapse() throws Exception{
		setClose(true);
	}
	
	public void load() throws Exception {
		load(this.getId());
	}
	
	public void load(String nodeId) throws Exception{
		setId(nodeId);
		
		if(this.getLoadDepth() < DEPTH){
			if(this.getLoadDepth() > -1)
				setLoadDepth(getLoadDepth()+1);
			
			setChildNode(loadChildren());
			setClose(false);
		}
			setFirst(false);
	}
	
	public Object[] loadDocument() throws Exception {
		DocumentListWindow documentListWindow = new DocumentListWindow();
		documentListWindow.load(this.getId());
		return new Object[]{ new Refresh(documentListWindow) };
	}

	
	@Override
	public Object[] remove() throws Exception {
		// TODO Auto-generated method stub

		StringBuffer sb = new StringBuffer();
		sb.append("update bpm_knol");
		sb.append("  set type = null ");
		sb.append(" where id=?id");
		
		IDocumentNode documentNode = (IDocumentNode) sql(IDocumentNode.class, sb.toString());
		documentNode.set("id", this.getId());
		
		documentNode.update();
		
		
		return new Object[]{new Remover(this)};
	}

	@Override
	public ModalWindow modify() throws Exception {
		// TODO Auto-generated method stub
		DocumentTitle documentTitle = new DocumentTitle();
		documentTitle.setId(this.getId());
		documentTitle.setName(this.getName());
		documentTitle.setMetaworksContext(new MetaworksContext());
		documentTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		documentTitle.session = session;
		return new ModalWindow(documentTitle, 500,250, "문서제목수정");
	}
	
	public IDocumentNode saveMe() throws Exception {
		// TODO Auto-generated method stub
		WfNode node = new WfNode();
		node.setId(this.getId());
		node.copyFrom(node.databaseMe());
		
		
		
		return createDatabaseMe();
	}
	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof DocumentDrag){
			
			DocumentDrag documentInClipboard = (DocumentDrag) clipboard;
			
			WorkItem workitem = new WorkItem();
			workitem.setTaskId(new Long(documentInClipboard.getTaskId()));
			
			WfNode wfNode = new WfNode();
			wfNode.setUrl(this.getUrl());
			wfNode.setThumbnail(this.getThumbnail());
			wfNode.setConnType(this.getConntype());
			wfNode.createMe();
			
			
			
			
			workitem.databaseMe();
			workitem.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
			
			
			
			workitem.flushDatabaseMe();
			IWorkItem item = workitem.databaseMe();
			return new Object[]{new Refresh(item)};
		}
		
		return null;
	}
	
	//하위폴더
	@Override
	public ModalWindow addSubFolder() throws Exception {
		DocumentTitle documentTitle = new DocumentTitle();
		documentTitle.setMetaworksContext(new MetaworksContext());
		documentTitle.getMetaworksContext().setWhen("SubFolder");
		documentTitle.session = session;
		return new ModalWindow(documentTitle , 500, 200,  "$addSubDocument");
	}

	
}
