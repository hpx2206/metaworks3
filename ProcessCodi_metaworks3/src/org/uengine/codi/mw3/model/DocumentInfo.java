package org.uengine.codi.mw3.model;

import java.util.Date;

import org.metaworks.MetaworksContext;
import org.metaworks.Refresh;
import org.metaworks.Remover;
import org.metaworks.website.MetaworksFile;
import org.metaworks.widget.ModalWindow;
import org.uengine.codi.mw3.knowledge.WfNode;

public class DocumentInfo extends GroupInfo{

	String parentId;			
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
	
	Date startDate;
		public Date getStartDate() {
			return startDate;
		}
		public void setStartDate(Date startDate) {
			this.startDate = startDate;
		}

	String authorId;		
		public String getAuthorId() {
			return authorId;
		}
		public void setAuthorId(String authorId) {
			this.authorId = authorId;
		}
		
	
	public DocumentInfo(){
		
	}
	public DocumentInfo(Session session){
		this.session = session;
		this.setId(session.getLastSelectedItem());
		this.setMetaworksContext(new MetaworksContext());
		this.getMetaworksContext().setWhen(MetaworksContext.WHEN_VIEW);
	}
	@Override
	public void load() throws Exception {
		this.followersLoad();
		WfNode wfNode = new WfNode();
		wfNode.setId(this.getId());
		
		try {
			wfNode.copyFrom(wfNode.databaseMe());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.setName(wfNode.getName());
		this.setSecuopt(wfNode.getSecuopt());
		this.setStartDate(wfNode.getStartDate());
		this.setParentId(wfNode.getId());	
		this.setAuthorId(wfNode.getAuthorId());		
		this.setDescription(wfNode.getDescription());
		
		
		MetaworksFile logoFile = new MetaworksFile();
		logoFile.setUploadedPath(wfNode.getUrl());
		logoFile.setFilename(wfNode.getThumbnail());
		this.setLogoFile(logoFile);
	}
	
	@Override
	public ModalWindow modify() throws Exception {
		DocumentTitle documentTitle = new DocumentTitle();
		documentTitle.setId(this.getId());
		documentTitle.setName(this.getName());
		documentTitle.setDescription(this.getDescription());
		documentTitle.setLogoFile(new MetaworksFile());
		documentTitle.setParentId(this.getParentId());
		documentTitle.setMetaworksContext(new MetaworksContext());
		documentTitle.getMetaworksContext().setWhen(MetaworksContext.WHEN_EDIT);
		documentTitle.session = session;
		
		return new ModalWindow(documentTitle, MODIFY_POPUP_WIDTH, MODIFY_POPUP_HEIGHT, "문서제목수정");
	}
	
	@Override
	public Object[] delete() throws Exception {
		DocumentNode deletedNode = new DocumentNode();
		deletedNode.setId(this.getId());
		deletedNode.copyFrom(deletedNode.databaseMe());
		deletedNode.deleteDatabaseMe();
		
		//this가 아닌 Node지우기.
		return new Object[]{new Remover(deletedNode), new Refresh(new InstanceListPanel())};
	}
	
	@Override
	public void followersLoad() throws Exception {
		this.followers =  new DocumentFollowers();
		this.followers.session = session;
		this.followers.load();
	}
	
	
	
}
