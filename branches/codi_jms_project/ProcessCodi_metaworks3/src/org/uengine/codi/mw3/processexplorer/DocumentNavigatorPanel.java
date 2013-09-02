package org.uengine.codi.mw3.processexplorer;

import java.util.ArrayList;

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
	String parentid;
		public String getParentid() {
			return parentid;
		}
		public void setParentid(String parentid) {
			this.parentid = parentid;
		}	
	String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}		

	ArrayList<DocumentNode> documentList;
		public ArrayList<DocumentNode> getDocumentList() {
			return documentList;
		}
	
		public void setDocumentList(ArrayList<DocumentNode> documentList) {
			this.documentList = documentList;
		}

		
	public void load(){
		if(UEngineUtil.isNotEmpty(id) && UEngineUtil.isNotEmpty(parentid)){
			String id = this.id;
			String name = this.name;
			String[] documentArray = name.replace('.','@').split("@");
			
			DocumentNode node = new DocumentNode();
			node.setId(id);
			node.setName(documentArray[0]);
			
			if(documentList == null){
				documentList = new ArrayList<DocumentNode>();
			}
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
