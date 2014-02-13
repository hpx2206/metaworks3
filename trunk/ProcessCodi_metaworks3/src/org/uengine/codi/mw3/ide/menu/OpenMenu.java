package org.uengine.codi.mw3.ide.menu;

import org.metaworks.ServiceMethodContext;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.libraries.ProcessNode;
import org.uengine.codi.mw3.model.ProcessDesignerContentPanel;

public class OpenMenu extends CloudMenu{

	@AutowiredFromClient
	public Workspace workspace;
	
	@AutowiredFromClient
	public ProcessNode processNode;
		public ProcessNode getProcessNode() {
			return processNode;
		}
		public void setProcessNode(ProcessNode processNode) {
			this.processNode = processNode;
		}
	@Autowired
	public ProcessDesignerContentPanel processDesignerContentPanel;	
	@Autowired
		public ProcessDesignerContentPanel getProcessDesignerContentPanel() {
			return processDesignerContentPanel;
		}
		public void setProcessDesignerContentPanel(ProcessDesignerContentPanel processDesignerContentPanel){
			this.processDesignerContentPanel = processDesignerContentPanel;
		}
		
	String type;
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}


	public OpenMenu(){
		
	}
	
	public OpenMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("new");
		this.setName("$resource.menu.open");
		
		if(ResourceNode.TYPE_FILE_JAVA.equals(this.getResourceNode().getType())){		
			this.add(new MenuItem("openDesigner","폼 디자이너"));
			this.add(new MenuItem("openCode","자바 편집기"));
		}else if(ResourceNode.TYPE_FILE_PROCESS.equals(this.getResourceNode().getType())){	
			this.add(new MenuItem("openDesigner","프로세스 디자이너"));
			this.add(new MenuItem("openCode","XML 편집기"));
		}else{
			this.add(new MenuItem("openCode","편집기"));
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openDesigner() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			node.session = session;
			node.getMetaworksContext().setWhen("designer");
			
			return node.action();			
		}else{
			return null;
		}
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openCode() throws Exception{

		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			node.session = session;
			node.getMetaworksContext().setWhen("code");
			
			return node.action();			
		}else{
			return null;
		}
	}
	
}
