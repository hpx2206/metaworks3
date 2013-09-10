package org.uengine.codi.mw3.processexplorer;

import java.util.ArrayList;

import org.metaworks.MetaworksContext;
import org.metaworks.annotation.ServiceMethod;
import org.uengine.codi.mw3.model.DocumentNode;
import org.uengine.util.UEngineUtil;

public class DocumentNavigatorPanel {

	int index;
		public int getIndex() {
			return index;
		}
	
		public void setIndex(int index) {
			this.index = index;
		}
		
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
	
	public ArrayList<DocumentNode> documentList;
		public ArrayList<DocumentNode> getDocumentList() {
			return documentList;
		}
	
		public void setDocumentList(ArrayList<DocumentNode> documentList) {
			this.documentList = documentList;
		}
		
	DocumentNode node;
		public DocumentNode getNode() {
			return node;
		}
	
		public void setNode(DocumentNode node) {
			this.node = node;
		}

	public DocumentNavigatorPanel(){
	}
	
	public void load(){
		if(UEngineUtil.isNotEmpty(id)){
			String id = this.getId();
			String name = this.getName();
			
			DocumentNode node = new DocumentNode();
			node.setId(id);
			node.setName(name);
			
			if(documentList == null){
				documentList = new ArrayList<DocumentNode>();
			}
			node.setMetaworksContext(new MetaworksContext());
			node.getMetaworksContext().setHow("Navigator");
			documentList.add(node);
			
		}
	}
	
	public void add(DocumentNode node){
		documentList.add(node);
	}
	@ServiceMethod(callByContent=true)
	public Object[] changeDocumentPanel() throws Exception{
		return null;
		
	}
	
	
}
