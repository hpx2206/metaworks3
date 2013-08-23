package org.uengine.codi.mw3.processexplorer;

import java.util.ArrayList;

import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;

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

	
	
	public void load(){
		
	}
	
	@ServiceMethod(callByContent=true)
	public Object[] changeDocumentPanel() throws Exception{
		return null;
		
	}
	
	
}
