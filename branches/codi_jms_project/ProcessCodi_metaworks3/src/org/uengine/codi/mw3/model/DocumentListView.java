package org.uengine.codi.mw3.model;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.Hidden;
import org.metaworks.annotation.Id;
import org.metaworks.annotation.ServiceMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.processmanager.ProcessManagerRemote;

public class DocumentListView {
	//문서분류별 목록을 출력해주는 클래스

	final static int LIST_CNT = 5;
	
		
	String parentId;
		@Id
		@Hidden
		public String getParentId() {
			return parentId;
		}
		public void setParentId(String parentId) {
			this.parentId = parentId;
		}
		
	IDocumentNode documentNode;
		public IDocumentNode getDocumentNode() {
			return documentNode;
		}
		public void setDocumentNode(IDocumentNode documentNode) {
			this.documentNode = documentNode;
		}

	boolean more;
		public boolean isMore() {
			return more;
		}
		public void setMore(boolean more) {
			this.more = more;
		}		
		
	InstanceTooltip instanceAction;
		public InstanceTooltip getInstanceAction() {
			return instanceAction;
		}
		public void setInstanceAction(InstanceTooltip instanceAction) {
			this.instanceAction = instanceAction;
		}	
		
	InstanceFollowers followers;
		public InstanceFollowers getFollowers() {
			return followers;
		}
		public void setFollowers(InstanceFollowers followers) {
			this.followers = followers;
		}
		
	public DocumentListView(){
		}
		
	public void load(String parentId) throws Exception{
		IDocumentNode documentNode =  DocumentNode.findFile(parentId);	
		setDocumentNode(documentNode);
	}
	
	@Autowired
	public ProcessManagerRemote processManager;	
}
