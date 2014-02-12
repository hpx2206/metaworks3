package org.uengine.codi.mw3.ide.menu;

import org.metaworks.MetaworksException;
import org.metaworks.ServiceMethodContext;
import org.metaworks.ToAppend;
import org.metaworks.annotation.AutowiredFromClient;
import org.metaworks.annotation.ServiceMethod;
import org.metaworks.component.MenuItem;
import org.metaworks.component.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.uengine.codi.mw3.ide.CloudWindow;
import org.uengine.codi.mw3.ide.ResourceNode;
import org.uengine.codi.mw3.ide.Workspace;
import org.uengine.codi.mw3.ide.editor.Editor;
import org.uengine.codi.mw3.ide.editor.java.JavaCodeEditor;
import org.uengine.codi.mw3.ide.editor.metadata.MetadataXmlEditor;
import org.uengine.codi.mw3.ide.editor.metadata.ProcessXmlEditor;
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
		this(null);
	}
	
	
	public OpenMenu(ResourceNode resourceNode){
		this.setResourceNode(resourceNode);
		
		this.setId("new");
		this.setName("$resource.menu.open");
		
		this.add(new MenuItem("openWithUI","UI"));
//		this.add(new MenuItem("openWithJava","OpenWithJava"));
//		this.add(new MenuItem("openWithXML","OpenWithXML"));
		this.add(new MenuItem("code","$resource.menu.code"));
		this.add(new MenuItem("openWithJnlp","javaProcessDesigner"));
		
	}
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openWithUI() throws Exception{
		Object clipboard = session.getClipboard();
		if(clipboard instanceof ResourceNode){
			ResourceNode node = (ResourceNode)clipboard;
			node.session = session;
			node.getMetaworksContext().setWhen("UI");
			
			return node.action();			
		}else{
			return null;
		}
	}
	
	
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object openWithJnlp() throws Exception{
		String type = this.getResourceNode().findNodeType(this.getResourceNode().getName());
		
		if( this.getProcessNode() != null && type.equals(TreeNode.TYPE_FILE_PROCESS)){
			String projectId = this.getProcessNode().getProjectId();
			String nadePath =  this.getProcessNode().getId();
			String defId =  nadePath.substring(projectId.length(), nadePath.length());
			processDesignerContentPanel.load(defId);
			return processDesignerContentPanel;
		}else{
			throw new MetaworksException("열수 없는 형식입니다.");
		}
	}
	@ServiceMethod(callByContent=true, target=ServiceMethodContext.TARGET_POPUP)
	public Object code() throws Exception{
		
		String type = this.getResourceNode().findNodeType(this.getResourceNode().getName());
		
		if(type.equals(TreeNode.TYPE_FILE_JAVA)){
			return new ToAppend(new CloudWindow("java"), new JavaCodeEditor(this.getResourceNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_PROCESS)){
			return new ToAppend(new CloudWindow("process"), new ProcessXmlEditor(this.getProcessNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_METADATA)){
			return new ToAppend(new CloudWindow("xml"), new MetadataXmlEditor(this.getResourceNode()));
		}else if(type.equals(TreeNode.TYPE_FILE_VALUECHAIN)){
			return new ToAppend(new CloudWindow("valuechain"), new MetadataXmlEditor(this.getResourceNode()));
		}else{
			throw new MetaworksException("열수 없는 형식입니다.");
		}
	}
	
}
