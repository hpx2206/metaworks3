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
import org.uengine.codi.mw3.admin.PageNavigator;
import org.uengine.codi.mw3.processexplorer.DocumentFilePanel;
import org.uengine.codi.mw3.processexplorer.DocumentFileViewPanel;
import org.uengine.codi.mw3.processexplorer.DocumentFolderPanel;
import org.uengine.codi.mw3.processexplorer.DocumentViewWindow;
import org.uengine.codi.mw3.processexplorer.ProcessExplorerContentWindow;

public class DocumentNode extends Database<IDocumentNode> implements IDocumentNode{
	@AutowiredFromClient
	transient public Session session;
	
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
	ArrayList<DocumentNode> fileList;
		public ArrayList<DocumentNode> getFileList() {
			return fileList;
		}
		public void setFileList(ArrayList<DocumentNode> fileList) {
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

	@AutowiredFromClient
	public PageNavigator pageNavigator; 
	
	@AutowiredFromClient
	public DocumentViewWindow documentViewWindow;
	
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
	DocumentFolderPanel documentFolderPanel;
		public DocumentFolderPanel getDocumentFolderPanel() {
			return documentFolderPanel;
		}
		public void setDocumentFolderPanel(DocumentFolderPanel documentFolderPanel) {
			this.documentFolderPanel = documentFolderPanel;
		}
	DocumentFilePanel documentFilePanel;
		public DocumentFilePanel getDocumentFilePanel() {
			return documentFilePanel;
		}
		public void setDocumentFilePanel(DocumentFilePanel documentFilePanel) {
			this.documentFilePanel = documentFilePanel;
		}
	DocumentFileViewPanel documentFileViewPanel;	
		public DocumentFileViewPanel getDocumentFileViewPanel() {
			return documentFileViewPanel;
		}
		public void setDocumentFileViewPanel(DocumentFileViewPanel documentFileViewPanel) {
			this.documentFileViewPanel = documentFileViewPanel;
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
		dao.set("type", TYPE_DOC);
		dao.set("parentid", "Main");
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
	
	public static IDocumentNode findDetail(String id) throws Exception{
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * ");
		sql.append(" from bpm_knol");
		sql.append(" where type=?type");
		sql.append(" and id=?id");
		
		IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sql.toString());
		
		node.set("type", TYPE_FILE);
		node.set("id",id);
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
	
	public ArrayList<DocumentNode> loadExplorerView(String parentId) throws Exception{
		folderList = new ArrayList<DocumentNode>();
		fileList = new ArrayList<DocumentNode>();
		StringBuffer sb = new StringBuffer();
		if("fileView".equals(this.getMetaworksContext().getHow())){
			sb.append("select * ");
			sb.append(" from bpm_knol");
			sb.append(" where type=?type");
			sb.append(" and parentId=?parentId");
			
			IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sb.toString());
			
			node.set("type", TYPE_FILE);
			node.set("parentId",parentId);
			node.select();
			
			if(node.size() >0){
				while(node.next()){
					DocumentNode documentNode = new DocumentNode();
					
					documentNode.copyFrom(node);
					documentNode.setFileList(new ArrayList<DocumentNode>());
					
					documentNode.setMetaworksContext(this.getMetaworksContext());
				documentNode.fileIconType(node.getConntype());
					fileList.add(documentNode);
				}
			}
			
			
//			return new Object[]{ new Refresh(documentListWindow) };
			return fileList;
		
		}else if("folderView".equals(this.getMetaworksContext().getHow())){
			sb.append("select * ");
			sb.append(" from bpm_knol");
			sb.append(" where type=?type");
			sb.append(" and parentId=?parentId");
			
			IDocumentNode node = (IDocumentNode) sql(DocumentNode.class,sb.toString());
			
			node.set("type", TYPE_DOC);
			node.set("parentId",parentId);
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
		
		
		return null;
	}
	
	public String fileIconType(String mimeType){
		
		if(mimeType.indexOf("pdf") > -1){
			fileIcon = "Icon_pdf.png";
		}else if(mimeType.indexOf("ms") > -1){
			if(mimeType.indexOf("excel") > -1){
				fileIcon = "Icon_excel.png";
			}else if(mimeType.indexOf("powerpoint") > -1){
				fileIcon = "Icon_ppt.png";
			}else if(mimeType.indexOf("word") > -1){
				fileIcon = "Icon_doc.png";
			}
		}else if(mimeType.indexOf("haansoft") > -1){
				fileIcon = "Icon_haansoft.png";
		}else if(mimeType.indexOf("text") > -1){
				fileIcon = "Icon_text.png";
		}else if(mimeType.indexOf("image") > -1){
				fileIcon = "Icon_image.png";
		}else{
				fileIcon = "Icon_etc.png";
		}
		return fileIcon;
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
		DocumentListWindow documentListWindow = new DocumentListWindow();
		documentListWindow.setParentId(this.getId());
		documentListWindow.load();
		return new Object[]{ new Refresh(documentListWindow) };
	}
	
	@Override
	public Object[] loadExplorerDocument() throws Exception{
		DocumentViewWindow documentWindow = new DocumentViewWindow();
		documentWindow.session = session;
		documentWindow.setId(this.getId());
		documentWindow.setName(this.getName());
		documentWindow.setTempId(this.getId());
		documentWindow.setParentId(this.getParentId());
		documentWindow.load();
		
		ProcessExplorerContentWindow processExplorerContentWindow = new ProcessExplorerContentWindow();
		processExplorerContentWindow.setPanel(documentWindow);
		return new Object[]{new Refresh(processExplorerContentWindow)};
	}
	
	@Override
	public Object[] loadFolderView() throws Exception{
		documentFolderPanel = new DocumentFolderPanel();
		documentFilePanel = new DocumentFilePanel();
		
		
		
		
		return new Object[]{new Refresh(documentFolderPanel), new Refresh(documentFilePanel)};
	}
	
	
	@Override
	public Object[] loadDetailView() throws Exception{
		DocumentFileViewPanel documentFileViewPanel = new DocumentFileViewPanel();
		documentFileViewPanel.setId(this.getId());
		documentFileViewPanel.load();
		
		DocumentFolderPanel documentFolderPanel = new DocumentFolderPanel();
		documentFolderPanel.setParentId(this.getParentId());
		documentFolderPanel.load(this.getParentId());
		
		
		return new Object[]{new Refresh(documentFolderPanel), new Refresh(documentFilePanel)};
		
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
			
			DocumentNode node = new DocumentNode();
			WorkItem workitem = new WorkItem();
			workitem.setTaskId(new Long(documentInClipboard.getTaskId()));
			node.setId(node.getId());
			node.setName(documentInClipboard.getExtFile());
			node.setType(TYPE_FILE);
			node.setNo(0);
			node.setSecuopt(documentSecuopt ? "1" : "0");
			node.setParentId(this.getId());
			node.setUrl(documentInClipboard.getContent());
			node.setThumbnail(documentInClipboard.getExtFile());
			node.setConntype(documentInClipboard.getTool());
			node.setStartDate(documentInClipboard.getStartDate());
			node.setEndDate(documentInClipboard.getEndDate());
			node.setAuthorId(session.getUser().getUserId());		
			node.setCompanyId(session.getCompany().getComCode());
			
			node.createMe();
			
			return new Object[]{new Refresh(node)};
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
