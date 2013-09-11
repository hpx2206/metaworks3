package org.uengine.codi.mw3.model;

import java.util.ArrayList;
import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Face;
import org.metaworks.annotation.Hidden;
import org.metaworks.dao.DAOUtil;
import org.metaworks.dao.Database;
import org.metaworks.dao.MetaworksDAO;
import org.metaworks.dao.TransactionContext;
import org.metaworks.dao.UniqueKeyGenerator;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.processexplorer.DocumentFilePanel;
import org.uengine.codi.mw3.processexplorer.DocumentNavigatorPanel;
import org.uengine.codi.mw3.processexplorer.DocumentViewWindow;

public class DocumentNode extends Database<IDocumentNode> implements IDocumentNode{
	@AutowiredFromClient
	transient public Session session;
	@AutowiredFromClient
	public DocumentNavigatorPanel documentNavigatorPanel;

	public final static String TYPE_DOC = "doc";
	public final static String TYPE_FILE = "file";
	public final static int DEPTH = 1;

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

	ArrayList<DocumentNode> childNode;
		public ArrayList<DocumentNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(ArrayList<DocumentNode> childNode) {
			this.childNode = childNode;
		}

	ArrayList<DocumentNode> folderList;
		public ArrayList<DocumentNode> getFolderList() {
			return folderList;
		}
		public void setFolderList(ArrayList<DocumentNode> folderList) {
			this.folderList = folderList;
		}
	ArrayList<WorkItem> fileList;
		public ArrayList<WorkItem> getFileList() {
			return fileList;
		}
		public void setFileList(ArrayList<WorkItem> fileList) {
			this.fileList = fileList;
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
		
	Date startDate;
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}
	
	Date endDate;
		public Date getEndDate() {
			return endDate;
		}
		public void setEndDate(Date endDate) {
			this.endDate = endDate;
		}
	
	String visType;
		public String getVisType() {
			return visType;
		}
		public void setVisType(String visType) {
			this.visType = visType;
		}
	int no;
		public int getNo() {
			return no;
		}
		public void setNo(int no) {
			this.no = no;
		}
	String fileIcon;
		public String getFileIcon() {
			return fileIcon;
		}
		public void setFileIcon(String fileIcon) {
			this.fileIcon = fileIcon;
		}
	
	boolean documentSecuopt;		
		@Hidden
		@Face(displayName="$DocumentSecuopt")
		public boolean isDocumentSecuopt() {
			return documentSecuopt;
		}
		public void setDocumentSecuopt(boolean documentSecuopt) {
			this.documentSecuopt = documentSecuopt;
		}		
		
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
		sb.append(" and companyId =?companyId");
		sb.append(" and ( knol.secuopt=0 OR (knol.secuopt=1 and ( exists (select topicid from BPM_TOPICMAPPING tp where tp.userid=?userid and knol.id=tp.topicid)  ");
		sb.append(" 																	 or ?userid in ( select empcode from emptable where partcode in (  ");
		sb.append(" 																	 						select userId from BPM_TOPICMAPPING where assigntype = 2 and topicID = knol.id )))))  ");
		sb.append(" order by "  + daoUtil.replaceReservedKeyword("no"));
		
		
		IDocumentNode dao = (IDocumentNode)MetaworksDAO.createDAOImpl(TransactionContext.getThreadLocalInstance(),sb.toString(),IDocumentNode.class);
		dao.set("type", TYPE_DOC);
		dao.set("parentid", "Main");
		dao.set("companyId", this.getCompanyId());
		dao.select();
		
		return dao;
	}
	
	//
	public static IDocumentNode findFile(String parentId) throws Exception{
		
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * ");
		sql.append(" from bpm_knol");
		sql.append(" where type=?type");
		sql.append(" and parentId=?parentId");
		
		IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sql.toString());
		
		node.set("type", TYPE_FILE);
		node.set("parentId",parentId);
		node.select();
		
		return node;
	}
	
	public static IDocumentNode findFolder(String parentId) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * ");
		sql.append(" from bpm_knol");
		sql.append(" where type=?type");
		sql.append(" and parentId=?parentId");
		
		IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sql.toString());
		
		node.set("type", TYPE_DOC);
		node.set("parentId",parentId);
		node.select();
		
		return node;
	}
	public ArrayList<DocumentNode> loadChildren() throws Exception{
		childNode = new ArrayList<DocumentNode>();
		
		DAOUtil daoUtil = new DAOUtil();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from bpm_knol");
		sb.append(" where parentId=?parentId");
		sb.append(" and type=?type");
		sb.append(" order by " +daoUtil.replaceReservedKeyword("no"));
		
		IDocumentNode parentNode = (IDocumentNode) sql(IDocumentNode.class,sb.toString());
		
		parentNode.set("parentId", this.getId());
		parentNode.set("type",TYPE_DOC);
		parentNode.select();
		
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
	
	public ArrayList<WorkItem> loadFileView(String id) throws Exception{
		fileList = new ArrayList<WorkItem>();
		StringBuffer sb = new StringBuffer();
			sb.append("select *,tool as fileIcon");
			sb.append(" from bpm_worklist");
			sb.append(" where folderId=?id");
			
			IWorkItem workitem = (IWorkItem) sql(WorkItem.class,sb.toString());
			
			workitem.set("id",id);
			workitem.select();
			
			if(workitem.size() >0){
				while(workitem.next()){
					WorkItem work = new WorkItem();
					
					work.copyFrom(workitem);
					
					work.getMetaworksContext().setHow("fileView");
//					work.fileIconType(workitem.getTool());
					fileList.add(work);
				}
			}
			
			
//			return new Object[]{ new Refresh(documentListWindow) };
			return fileList;
	
	}
	
	public ArrayList<DocumentNode> loadFolderView(String id) throws Exception{
		folderList = new ArrayList<DocumentNode>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append(" from bpm_knol");
		sb.append(" where type=?type");
		sb.append(" and parentId=?parentId");
		
		IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sb.toString());
		
		node.set("type", TYPE_DOC);
		node.set("parentId",id);
		node.select();
		
		if(node.size()>0){
			while(node.next()){
				DocumentNode documentNode = new DocumentNode();
				
				documentNode.copyFrom(node);
				documentNode.setFolderList(new ArrayList<DocumentNode>());
				
				documentNode.setMetaworksContext(this.getMetaworksContext());
				folderList.add(documentNode);
			}
		}
		return folderList;
	}
	
	public void expand() throws Exception{
		this.setLoadDepth(1);
		this.setChildNode(loadChildren());
		
		setClose(true);
	}
	
	
	
	public void collapse() throws Exception{
		setClose(false);
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
		String title = "문서명: " + this.getName();
		Object[] returnObject = Perspective.loadDocumentListPanel(session, "document", getId(), title);
		
		return returnObject;

	}
	
	@Override
	public Object[] loadExplorerDocument() throws Exception{
		
		String title = "문서명: " + this.getName();
		Object[] returnObject = Perspective.loadDocumentListPanel(session, "explorer", getId(), title);
		
		return returnObject;
		
		
		
	}
	
	
	
	@Override
	public Object[] openFolderView() throws Exception{
		
		DocumentFilePanel documentFilePanel = new DocumentFilePanel();
		documentFilePanel.setMetaworksContext(new MetaworksContext());
		documentFilePanel.loadDetailView(this.getId());
		
		DocumentNode node = new DocumentNode();
		node.setName(this.getName());
		node.setMetaworksContext(new MetaworksContext());
		node.getMetaworksContext().setHow("Navigator");
		documentNavigatorPanel.documentList.add(node);
		
		
		return new Object[]{new Refresh(documentFilePanel), new Refresh(documentNavigatorPanel)};
	}
	
	
	@Override
	public Object[] remove() throws Exception {

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
		DocumentTitle documentTitle = new DocumentTitle();
		documentTitle.setId(this.getId());
		documentTitle.setName(this.getName());
		documentTitle.setDescription(this.getDescription());
		documentTitle.setParentId(this.getParentId());
		documentTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		documentTitle.session = session;
		return new ModalWindow(documentTitle, 500,250, "문서제목수정");
	}
	
	public void saveMe() throws Exception {
		syncToDatabaseMe();
		flushDatabaseMe();
	}
	
	public String makeId() throws Exception {
		return String.valueOf(UniqueKeyGenerator.issueKey("bpm_knol", TransactionContext.getThreadLocalInstance()));
	}
	
	public void createMe() throws Exception {
		String nodeId = this.makeId();
		
		setId(nodeId);

		createDatabaseMe();
		flushDatabaseMe();
	}
	
	@Override
	public Object[] drop() throws Exception {
		Object clipboard = session.getClipboard();
		if(clipboard instanceof DocumentDrag){
			
			DocumentDrag documentInClipboard = (DocumentDrag) clipboard;
			
			WorkItem workitem = new WorkItem();
			workitem.setTaskId(new Long(documentInClipboard.getTaskId()));
			workitem.databaseMe().setFolderId(this.getId());
			workitem.databaseMe().setFolderName(this.getName());
			workitem.flushDatabaseMe();
			InstanceViewThreadPanel instView = new InstanceViewThreadPanel();
			instView.load();
			return new Object[]{new Refresh(instView)};
		}
		
		return null;
	}
	
	@Override
	public ModalWindow addSubFolder() throws Exception {
		DocumentTitle documentSubTitle = new DocumentTitle();
		documentSubTitle.setId(this.getId());
		documentSubTitle.getMetaworksContext().setHow("sub");
		documentSubTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_NEW);
		documentSubTitle.session = session;
		
		return new ModalWindow(documentSubTitle , 500, 200,  "$addSubDocument");
	}

	
}
